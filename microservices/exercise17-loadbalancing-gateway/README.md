# Exercise 17: Load Balancing in an API Gateway

Demonstrates client-side load balancing across multiple instances of the
same service, using Spring Cloud Gateway + Spring Cloud LoadBalancer + Eureka,
with a custom **Random** load-balancing strategy (instead of the default
Round Robin) scoped specifically to `example-service`.

## What's included
- `eureka-discovery-server` (port 8761) — service registry
- `example-service-instance1` (port 8085) — registers as `example-service`
- `example-service-instance2` (port 8086) — registers as `example-service` too
  (same application name, different port -> two instances of the same logical service)
- `lb-gateway` (port 9092) — routes `/loadbalanced/**` to `lb://example-service`,
  load balancing between whichever instances are registered, using a custom
  `RandomLoadBalancer` wired via `@LoadBalancerClient`

Each instance's `/example/hello` response includes its own port number, so
you can see the gateway alternating (randomly) between the two instances.

## Prerequisites
- Java 17
- Maven

## How to run (in order, 4 terminals)
```
# Terminal 1 - Eureka
cd eureka-discovery-server
mvn spring-boot:run

# Terminal 2 - instance 1
cd example-service-instance1
mvn spring-boot:run

# Terminal 3 - instance 2
cd example-service-instance2
mvn spring-boot:run

# Terminal 4 - gateway
cd lb-gateway
mvn spring-boot:run
```

Wait for each to fully start before starting the next, and check
`http://localhost:8761` to confirm both `EXAMPLE-SERVICE` instances show up
registered (Availability Zone column will show 2 entries under the same
application name).

## Test
Hit this URL repeatedly:
```
http://localhost:9092/loadbalanced/example/hello
```
You should see the response alternate between:
```
Hello from example-service instance running on port: 8085
Hello from example-service instance running on port: 8086
```
confirming requests are being load balanced across both instances.
