package com.cognizant.paymentservice;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Simulates a slow, unreliable third-party payment gateway.
 * Randomly throws an exception or sleeps too long, to trigger the circuit breaker.
 */
@Component
public class ThirdPartyPaymentClient {

    private final Random random = new Random();

    public String charge(String orderId) throws InterruptedException {
        // Simulate network latency
        Thread.sleep(500);

        // Simulate ~60% failure rate from the third-party API
        if (random.nextInt(10) < 6) {
            throw new RuntimeException("Third-party payment gateway is unavailable for order: " + orderId);
        }

        return "Payment successful for order: " + orderId;
    }

}
