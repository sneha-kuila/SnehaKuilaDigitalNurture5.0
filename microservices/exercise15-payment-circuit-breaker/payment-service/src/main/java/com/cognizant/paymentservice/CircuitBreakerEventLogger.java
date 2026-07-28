package com.cognizant.paymentservice;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Registers listeners on the "paymentService" circuit breaker so that every
 * state transition (CLOSED -> OPEN -> HALF_OPEN etc.) and every failure event
 * gets logged. Useful for monitoring/alerting on fallback events.
 */
@Component
public class CircuitBreakerEventLogger {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerEventLogger.class);

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerEventLogger(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @PostConstruct
    public void registerListeners() {
        circuitBreakerRegistry.circuitBreaker("paymentService")
                .getEventPublisher()
                .onStateTransition(event ->
                        logger.warn("Circuit Breaker [{}] state changed: {} -> {}",
                                event.getCircuitBreakerName(),
                                event.getStateTransition().getFromState(),
                                event.getStateTransition().getToState()))
                .onError(event ->
                        logger.error("Circuit Breaker [{}] recorded an error: {}",
                                event.getCircuitBreakerName(),
                                event.getThrowable().getMessage()))
                .onCallNotPermitted(event ->
                        logger.warn("Circuit Breaker [{}] blocked a call - circuit is OPEN",
                                event.getCircuitBreakerName()));
    }

}
