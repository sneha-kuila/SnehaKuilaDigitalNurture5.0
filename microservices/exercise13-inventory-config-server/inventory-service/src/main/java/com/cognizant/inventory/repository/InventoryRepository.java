package com.cognizant.inventory.repository;

import com.cognizant.inventory.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InventoryRepository {

    private final Map<Long, InventoryItem> stock = new HashMap<>();

    public InventoryRepository() {
        stock.put(1L, new InventoryItem(1L, 25));
        stock.put(2L, new InventoryItem(2L, 100));
        stock.put(3L, new InventoryItem(3L, 60));
    }

    public Optional<InventoryItem> findByProductId(Long productId) {
        return Optional.ofNullable(stock.get(productId));
    }

    public InventoryItem save(InventoryItem item) {
        stock.put(item.getProductId(), item);
        return item;
    }
}
