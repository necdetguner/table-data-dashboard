package com.necdetguner.tabledatadashboard.controller;

import com.necdetguner.tabledatadashboard.entity.Stock;
import com.necdetguner.tabledatadashboard.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    private final DataService dataService;

    public MainController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<String> exchangeList = dataService.getAllExchangeNames();
        List<String> dateList = dataService.getFolderList();

        model.addAttribute("exchangeNameList", exchangeList.stream().sorted());
        model.addAttribute("dateList", dateList.stream().sorted());

        return "index.html";
    }

    @GetMapping("/getdata")
    @ResponseBody
    public List<Stock> getData(@RequestParam String date, @RequestParam String exchangeName) {
        return dataService.getDataFromJson(date, exchangeName);
    }
}
