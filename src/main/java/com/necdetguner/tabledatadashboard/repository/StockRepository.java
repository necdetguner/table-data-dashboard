package com.necdetguner.tabledatadashboard.repository;

import com.necdetguner.tabledatadashboard.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByExchange(String exchange);
}
