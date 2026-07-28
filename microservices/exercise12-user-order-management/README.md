# Exercise 12: User and Order Management System

Two microservices demonstrating REST APIs and inter-service communication using
**OpenFeign**. `order-service` calls `user-service` to validate a user exists
before placing an order.

## user-service
- Port: `8082`
- Storage: H2 in-memory DB (swap for MySQL/PostgreSQL by changing
  `spring.datasource.*` properties and adding the relevant JDBC driver dependency)
- Endpoints:
  - `GET /users` — list all users
  - `GET /users/{id}` — get user by id
  - `POST /users` — create user, body: `{"name": "Alice", "email": "alice@test.com"}`
  - `PUT /users/{id}` — update user
  - `DELETE /users/{id}` — delete user

## order-service
- Port: `8083`
- Storage: H2 in-memory DB
- Uses a Feign client (`UserClient`) to call `user-service` at
  `http://localhost:8082` before accepting an order
- Endpoints:
  - `GET /orders` — list all orders
  - `GET /orders/{id}` — get order by id
  - `POST /orders` — place order, body: `{"userId": 1, "product": "Laptop", "quantity": 1}`
    - Calls `user-service` internally; rejects the order with 400 if the user doesn't exist
  - `DELETE /orders/{id}` — cancel order

## How to run

```bash
# 1. Start user-service first
cd user-service
mvn clean package
mvn spring-boot:run

# 2. Start order-service
cd ../order-service
mvn clean package
mvn spring-boot:run
```

## Test flow

```bash
# Create a user
curl -X POST http://localhost:8082/users -H "Content-Type: application/json" -d "{\"name\":\"Alice\",\"email\":\"alice@test.com\"}"

# Place an order for that user (assuming id=1)
curl -X POST http://localhost:8083/orders -H "Content-Type: application/json" -d "{\"userId\":1,\"product\":\"Laptop\",\"quantity\":1}"

# Try placing an order for a non-existent user -> expect 400 Bad Request
curl -X POST http://localhost:8083/orders -H "Content-Type: application/json" -d "{\"userId\":999,\"product\":\"Phone\",\"quantity\":1}"
```

## Switching to MySQL/PostgreSQL

Replace the H2 dependency and `spring.datasource.*` properties in each
`application.properties`, e.g. for MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=root
spring.datasource.password=yourpassword
```
and add `mysql-connector-j` as a dependency in the matching `pom.xml`.
