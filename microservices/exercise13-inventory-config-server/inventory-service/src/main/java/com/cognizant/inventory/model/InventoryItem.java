package com.cognizant.inventory.model;

public class InventoryItem {

    private Long productId;
    private int stockLevel;

    public InventoryItem() {
    }

    public InventoryItem(Long productId, int stockLevel) {
        this.productId = productId;
        this.stockLevel = stockLevel;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
