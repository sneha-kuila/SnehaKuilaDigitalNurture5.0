# Exercise 16: Edge Services for Routing and Filtering

Implements an edge service (API gateway) that routes requests to a
downstream service and logs every incoming request via a custom
`GlobalFilter`.

## What's included
- `example-service` (port 8085) — simple backend with `GET /example/hello`
- `edge-service` (port 9091) — Spring Cloud Gateway that:
  - routes any request matching `/example/**` to `example-service`
  - logs every request URI to the console via `LoggingFilter` (a `GlobalFilter`)

## Prerequisites
- Java 17
- Maven

## How to run
Terminal 1:
```
cd example-service
mvn spring-boot:run
```

Terminal 2:
```
cd edge-service
mvn spring-boot:run
```

## Test
```
http://localhost:9091/example/hello
```
Expected response:
```
Hello from example-service! You reached me through the edge service.
```

Check the **edge-service console** — you should see a log line like:
```
Request: http://localhost:9091/example/hello
```
printed by `LoggingFilter` for every request that passes through the gateway.
