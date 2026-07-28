package com.cognizant.product.repository;

import com.cognizant.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ProductRepository() {
        products.add(new Product(idCounter.getAndIncrement(), "Laptop", 55000.0, 25));
        products.add(new Product(idCounter.getAndIncrement(), "Mouse", 500.0, 100));
        products.add(new Product(idCounter.getAndIncrement(), "Keyboard", 1200.0, 60));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idCounter.getAndIncrement());
            products.add(product);
        } else {
            products.removeIf(p -> p.getId().equals(product.getId()));
            products.add(product);
        }
        return product;
    }

    public boolean updateStock(Long id, int newStock) {
        return findById(id).map(p -> {
            p.setStock(newStock);
            return true;
        }).orElse(false);
    }
}
