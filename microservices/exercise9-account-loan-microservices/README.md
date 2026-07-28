# Exercise 9: Account and Loan Microservices

Two independent Spring Boot microservices for a bank, each with its own `pom.xml`,
demonstrating the split from a monolithic service into microservices.

## account-service
- Endpoint: `GET /accounts/{number}`
- Port: `8080`
- Sample response:
```json
{ "number": "00987987973432", "type": "savings", "balance": 234343 }
```

## loan-service
- Endpoint: `GET /loans/{number}`
- Port: `8081`
- Sample response:
```json
{ "number": "H00987987972342", "type": "car", "loan": 400000, "emi": 3258, "tenure": 18 }
```

## How to run

Each service is built and run independently.

```bash
cd account-service
mvn clean package
mvn spring-boot:run
```

In a separate terminal:

```bash
cd loan-service
mvn clean package
mvn spring-boot:run
```

Test in browser or with curl:

```bash
curl http://localhost:8080/accounts/00987987973432
curl http://localhost:8081/loans/H00987987972342
```

## Why two separate ports?

Both services default to port 8080. Since account-service is already running on 8080,
loan-service is configured with `server.port=8081` in its `application.properties`
to avoid a bind-address-in-use conflict.
