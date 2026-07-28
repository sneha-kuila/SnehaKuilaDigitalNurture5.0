# Exercise 13: Product/Inventory Services with Eureka + Config Server

Three components demonstrating centralized configuration and service discovery:

## config-server
- Port: `8888`
- Uses the **native** profile to serve config from a local classpath folder
  (`config-repo/`) instead of requiring a Git repo — simplest setup for this exercise.
- Serves:
  - `product-service.properties` → port `8084`, `product.stock.low-threshold=10`
  - `inventory-service.properties` → port `8085`, `inventory.reorder.threshold=5`

## product-service
- Registers with Eureka as `product-service`
- Pulls its port and properties from config-server via
  `spring.config.import=configserver:http://localhost:8888`
- Endpoints:
  - `GET /products` — list all products
  - `GET /products/{id}` — get product
  - `POST /products` — create product
  - `PUT /products/{id}/stock?stock=50` — update stock

## inventory-service
- Registers with Eureka as `inventory-service`
- Also pulls config from config-server
- Endpoints:
  - `GET /inventory/{productId}` — get stock level for a product
  - `GET /inventory/{productId}/needs-reorder` — true/false based on
    `inventory.reorder.threshold` pulled from config-server
  - `PUT /inventory/{productId}?stockLevel=3` — update stock level

## How to run (order matters)

```bash
# 1. Eureka server (from exercise10)
cd ../exercise10-eureka-discovery-server
mvn spring-boot:run

# 2. Config server
cd ../exercise13-inventory-config-server/config-server
mvn clean package
mvn spring-boot:run

# 3. Product service
cd ../product-service
mvn clean package
mvn spring-boot:run

# 4. Inventory service
cd ../inventory-service
mvn clean package
mvn spring-boot:run
```

## Test

```bash
# Verify config-server is serving the right properties
curl http://localhost:8888/product-service/default
curl http://localhost:8888/inventory-service/default

# Product endpoints
curl http://localhost:8084/products

# Inventory endpoints
curl http://localhost:8085/inventory/3/needs-reorder
```

## Why config-server matters here

Instead of each service hardcoding its own port/thresholds in its local
`application.properties`, the actual values live centrally in `config-server`.
Changing `inventory.reorder.threshold` in one place (the config-repo file) and
restarting inventory-service is enough — no code change needed in the service itself.
