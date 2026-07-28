# Exercise 11: Spring Cloud API Gateway with Global Logging Filter

Creates a `greet-service` microservice and routes to it through a Spring Cloud
API Gateway, with a global filter that logs every incoming request.

## Prerequisite
Start the Eureka discovery server from **exercise10-eureka-discovery-server** first
(`http://localhost:8761`), since both greet-service and api-gateway register with it.

## Services

### greet-service
- Port: `8080`
- Endpoint: `GET /greet` → returns `Hello World!!`
- Registers with Eureka as `greet-service`

### api-gateway
- Port: `9090`
- Auto-routes to any registered Eureka service using
  `spring.cloud.gateway.discovery.locator.enabled=true`
- `lower-case-service-id=true` so routes use lowercase service names
- `LogFilter` (GlobalFilter) logs the URI of every request passing through the gateway

## How to run (in order)

```bash
# 1. Eureka server (from exercise10)
cd ../exercise10-eureka-discovery-server
mvn spring-boot:run

# 2. Greet service
cd ../exercise11-api-gateway-greet-service/greet-service
mvn clean package
mvn spring-boot:run

# 3. API Gateway
cd ../api-gateway
mvn clean package
mvn spring-boot:run
```

## Test

Direct call to greet-service:
```
http://localhost:8080/greet
```

Through the gateway (uses Eureka service discovery, lower-case service id):
```
http://localhost:9090/greet-service/greet
```

Check the api-gateway console log — you should see a line like:
```
====>Request URL http://localhost:9090/greet-service/greet
```
