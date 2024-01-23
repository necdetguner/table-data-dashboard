package com.necdetguner.tabledatadashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.necdetguner.tabledatadashboard.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class DataService {
    private final String dataFolderPath;
    private final String[] exchanges;
    private final StockService stockService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataService(@Value("${data.folder.path}") String dataFolderPath, @Value("${exchange.list}") String[] exhanges, StockService stockService) {
        this.dataFolderPath = dataFolderPath;
        this.exchanges = exhanges;
        this.stockService = stockService;
    }

    @Scheduled(cron = "0 30 23 * * *", zone = "GMT+3")
    public void runTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = LocalDateTime.now().format(formatter);

        log.info("Task started at: " + currentDateTime);

        String folderPath = createFolderAndReturnPath(currentDateTime);

        for (String exchange : exchanges) {
            List<Stock> data = stockService.getAllStocksByExchange(exchange);

            String fileName = String.format("%s.json", exchange);
            String filePath = Paths.get(folderPath, fileName).toString();

            try {
                objectMapper.writeValue(new File(filePath), data);
                log.info("JSON file created successfully at: " + filePath);
            } catch (IOException e) {
                log.error("Error while creating JSON file: " + filePath);
            }
        }
    }

    private String createFolderAndReturnPath(String currentDateTime) {
        String folderPath = Paths.get(dataFolderPath, currentDateTime).toString();

        try {
            Files.createDirectories(Paths.get(folderPath));
        } catch (IOException e) {
            log.error("Error while creating folder: " + folderPath);
        }

        return folderPath;
    }

    public List<String> getFolderList() {
        return Stream.of(Objects.requireNonNull(new File(dataFolderPath).listFiles()))
                .filter(File::isDirectory)
                .map(File::getName)
                .toList();
    }

    public List<Stock> getDataFromJson(String date, String exchangeName) {
        String folderPath = dataFolderPath + File.separator + date;

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            return new ArrayList<>();
        }

        String filePath = folderPath + File.separator + exchangeName + ".json";
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            return new ArrayList<>();
        }

        try {
            return List.of(objectMapper.readValue(file, Stock[].class));
        } catch (IOException e) {
            log.error("Error while reading JSON file: " + filePath);
            return new ArrayList<>();
        }
    }

    public List<String> getAllExchangeNames() {
        return List.of(exchanges);
    }
}
