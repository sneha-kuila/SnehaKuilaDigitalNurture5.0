# Exercise 15: Resilient Microservices with Circuit Breaker

A `payment-service` that calls a simulated slow/unreliable third-party API,
protected by a **Resilience4j Circuit Breaker** with a fallback method.
Fallback events and circuit state transitions are logged for monitoring.

## What's included
- `ThirdPartyPaymentClient` — simulates a flaky third-party payment gateway
  (500ms delay, ~60% random failure rate)
- `PaymentService` — wraps the call with `@CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")`
- `PaymentController` — exposes `GET /pay/{orderId}`
- `CircuitBreakerEventLogger` — logs every state transition (CLOSED → OPEN → HALF_OPEN),
  every recorded error, and every blocked call while the circuit is OPEN
- Actuator endpoints exposed for monitoring: `/actuator/health`,
  `/actuator/circuitbreakers`, `/actuator/circuitbreakerevents`

## Prerequisites
- Java 17
- Maven

## How to run
```
cd payment-service
mvn spring-boot:run
```

## Test
Hit the endpoint repeatedly (it fails ~60% of the time by design):
```
http://localhost:8084/pay/ORD1001
```

Because `minimumNumberOfCalls: 5` and `failureRateThreshold: 50`, after ~5+ calls
with over 50% failures, the circuit breaker will flip to **OPEN** and every
subsequent call will immediately return the fallback message
("Payment service temporarily unavailable...") instead of calling the
third-party client at all — check the console logs to see the
state transition and fallback events being logged.

To watch the circuit breaker state live:
```
http://localhost:8084/actuator/circuitbreakers
http://localhost:8084/actuator/circuitbreakerevents
```

After `waitDurationInOpenState: 10s`, the breaker automatically moves to
HALF_OPEN and allows a few trial calls through to decide whether to close
again or re-open.
