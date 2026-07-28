package com.cognizant.order.controller;

import com.cognizant.order.client.UserClient;
import com.cognizant.order.dto.UserDto;
import com.cognizant.order.model.Order;
import com.cognizant.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Placing an order calls user-service via Feign to confirm the user exists
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        UserDto user;
        try {
            user = userClient.getUserById(order.getUserId());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User with id " + order.getUserId() + " not found. Cannot place order.");
        }

        Order saved = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order placed for user " + user.getName() + " (id=" + user.getId() + "): " + saved.getProduct());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
