package com.cognizant.inventory.controller;

import com.cognizant.inventory.model.InventoryItem;
import com.cognizant.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Pulled from config-server (inventory-service.properties)
    @Value("${inventory.reorder.threshold:5}")
    private int reorderThreshold;

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryItem> getStock(@PathVariable Long productId) {
        return inventoryRepository.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{productId}/needs-reorder")
    public ResponseEntity<Boolean> needsReorder(@PathVariable Long productId) {
        return inventoryRepository.findByProductId(productId)
                .map(item -> ResponseEntity.ok(item.getStockLevel() < reorderThreshold))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    public InventoryItem updateStock(@PathVariable Long productId, @RequestParam int stockLevel) {
        return inventoryRepository.save(new InventoryItem(productId, stockLevel));
    }
}
