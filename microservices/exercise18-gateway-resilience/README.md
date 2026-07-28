# Exercise 18: Resilience Patterns in an API Gateway

Demonstrates a Spring Cloud Gateway route protected by a **CircuitBreaker
gateway filter** (backed by Resilience4j), with a fallback endpoint the
gateway forwards to whenever the downstream service fails, times out, or
the circuit is OPEN.

## What's included
- `flaky-service` (port 8087) — a deliberately unreliable service:
  `GET /slow-endpoint` fails immediately ~30% of the time, responds slowly
  (2.5s) ~30% of the time (long enough to trip the gateway's 2s timeout),
  and succeeds instantly the rest of the time.
- `resilient-gateway` (port 9093) — routes `GET /api/slow-endpoint` to
  `flaky-service`, wrapped in a `CircuitBreaker` gateway filter
  (`flakyServiceCircuitBreaker`) with a 2-second timeout and a fallback to
  `/fallback/slow-endpoint` if the call fails, times out, or the circuit is OPEN.
- `FallbackController` — logs every fallback event and returns a friendly
  "service unavailable" message instead of propagating the error to the client.
- Actuator endpoints exposed for monitoring circuit breaker state.

## Prerequisites
- Java 17
- Maven

## How to run
Terminal 1:
```
cd flaky-service
mvn spring-boot:run
```

Terminal 2:
```
cd resilient-gateway
mvn spring-boot:run
```

## Test
Hit this endpoint repeatedly:
```
http://localhost:9093/api/slow-endpoint
```
You'll see a mix of:
- Successful responses: `Response from flaky-service at <timestamp>`
- Fallback responses: `flaky-service is currently unavailable. Please try again shortly.`
  (this happens whenever flaky-service fails outright, or takes longer
  than 2 seconds and gets timed out by the gateway)

After enough failures within the sliding window, the circuit breaker will
flip to OPEN and every request will get the fallback response immediately
without even calling flaky-service - check the resilient-gateway console
logs for the fallback warning, and check live state at:
```
http://localhost:9093/actuator/circuitbreakers
http://localhost:9093/actuator/circuitbreakerevents
```
