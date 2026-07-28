# Exercise 10: Eureka Discovery Server

A standalone Spring Cloud Netflix Eureka Server that holds a registry of all
microservices available for consumption.

## Configuration
- Port: `8761`
- `eureka.client.register-with-eureka=false` and `eureka.client.fetch-registry=false`
  because this instance is the registry itself, not a client.

## How to run

```bash
mvn clean package
mvn spring-boot:run
```

Then open in browser:
```
http://localhost:8761
```

You should see the Eureka dashboard with "Instances currently registered with Eureka" empty.

## Registering account-service and loan-service

To register the services from exercise9 with this discovery server, each service needs:

1. Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
Plus the matching `spring-cloud.version` property and `dependencyManagement` block
(copy from this project's `pom.xml`).

2. Annotate the main application class with `@EnableDiscoveryClient`.

3. Ensure `spring.application.name` is set in `application.properties`
   (already done: `account-service`, `loan-service`).

4. Start this Eureka server first, then start account-service and loan-service.
   Refresh `http://localhost:8761` — both should appear under
   "Instances currently registered with Eureka".
