package com.necdetguner.tabledatadashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TableDataDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableDataDashboardApplication.class, args);
    }
}