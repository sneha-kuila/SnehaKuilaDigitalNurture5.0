# Exercise 14: API Gateway (Customer & Billing Service)

Spring Cloud Gateway routing to Customer Service and Billing Service, with
rate limiting, caching, and path rewriting.

## Projects in this folder
- `customer-service` (port 8082) — has response caching on `/customers/{id}`
- `billing-service` (port 8083) — plain REST endpoint `/billing/{id}`
- `api-gateway` (port 9090) — routes requests, rate-limits customer route, rewrites paths

## Prerequisites
- Java 17
- Maven (or use `mvnw` if you generate the wrapper via `mvn -N io.takari:maven:wrapper`)
- A running Eureka Discovery Server on `localhost:8761`
- Redis running on `localhost:6379` (required for rate limiting)

Start Redis via Docker:
```
docker run -p 6379:6379 redis
```

## How to run (in order)
1. Start your Eureka Discovery Server
2. Start Redis
3. Start `customer-service`:
   ```
   cd customer-service
   mvn spring-boot:run
   ```
4. Start `billing-service`:
   ```
   cd billing-service
   mvn spring-boot:run
   ```
5. Start `api-gateway`:
   ```
   cd api-gateway
   mvn spring-boot:run
   ```

## Test
- http://localhost:9090/api/customers/1
- http://localhost:9090/api/billing/1

Refresh the customers endpoint more than 5 times quickly to trigger
`429 Too Many Requests` from the rate limiter.
