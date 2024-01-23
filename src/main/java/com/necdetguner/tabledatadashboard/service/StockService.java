package com.necdetguner.tabledatadashboard.service;

import com.necdetguner.tabledatadashboard.entity.Stock;
import com.necdetguner.tabledatadashboard.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocksByExchange(String exchange) {
        return stockRepository.findAllByExchange(exchange);
    }
}
