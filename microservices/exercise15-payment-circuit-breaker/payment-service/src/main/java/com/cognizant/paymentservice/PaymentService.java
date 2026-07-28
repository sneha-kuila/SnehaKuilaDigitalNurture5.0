package com.cognizant.paymentservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final ThirdPartyPaymentClient thirdPartyPaymentClient;

    public PaymentService(ThirdPartyPaymentClient thirdPartyPaymentClient) {
        this.thirdPartyPaymentClient = thirdPartyPaymentClient;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    public String processPayment(String orderId) throws InterruptedException {
        return thirdPartyPaymentClient.charge(orderId);
    }

    // Fallback method - signature must match + take the Throwable as the last argument
    public String fallbackPayment(String orderId, Throwable throwable) {
        logger.warn("Fallback triggered for order [{}]. Reason: {}", orderId, throwable.getMessage());
        return "Payment service temporarily unavailable for order " + orderId + ". Please try again later.";
    }

}
