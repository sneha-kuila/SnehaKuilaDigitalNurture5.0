package com.cognizant.orm_learn.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getFacebookStocks() {
        return stockRepository.findByCodeAndDateBetween(
                "FB",
                LocalDate.of(2019, 9, 1),
                LocalDate.of(2019, 9, 30));
    }

    public List<Stock> getGoogleStocksAbove1250() {
        return stockRepository.findByCodeAndCloseGreaterThan(
                "GOOGL",
                new BigDecimal("1250"));
    }

    public List<Stock> getTop3VolumeStocks() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    public List<Stock> getLowest3NetflixStocks() {
        return stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
    }
}