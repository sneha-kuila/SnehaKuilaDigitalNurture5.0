package com.cognizant.orm_learn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // 1. Facebook stocks in September 2019
    List<Stock> findByCodeAndDateBetween(
            String code,
            LocalDate startDate,
            LocalDate endDate);

    // 2. Google stocks with closing price greater than a value
    List<Stock> findByCodeAndCloseGreaterThan(
            String code,
            java.math.BigDecimal close);

    // 3. Top 3 stocks by highest volume
    List<Stock> findTop3ByOrderByVolumeDesc();

    // 4. Lowest 3 Netflix stocks by closing price
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}