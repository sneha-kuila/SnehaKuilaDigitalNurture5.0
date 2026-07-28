# Spring Core & Maven Exercises — Library Management

Two Maven projects covering all 9 exercises from the Spring Core & Maven document.

## `LibraryManagement/` — Exercises 1–8 (classic Spring + XML config)

| Exercise | Where to look |
|---|---|
| 1. Configuring a Basic Spring Application | `pom.xml`, `applicationContext.xml`, `BookService`, `BookRepository` |
| 2. Implementing Dependency Injection | `BookService.setBookRepository(...)`, `applicationContext.xml` |
| 3. Logging with Spring AOP | `LoggingAspect.logExecutionTime(...)`, `aop:aspectj-autoproxy` in XML |
| 4. Creating/Configuring the Maven Project | `pom.xml` (Spring Context, AOP, WebMVC deps + compiler plugin) |
| 5. Configuring the Spring IoC Container | `applicationContext.xml` |
| 6. Configuring Beans with Annotations | `@Service`, `@Repository`, `<context:component-scan>` |
| 7. Constructor and Setter Injection | `BookService` constructor + setter |
| 8. Basic AOP (before/after advice) | `LoggingAspect.logBefore(...)` / `logAfter(...)` |

Run it with:
```bash
cd LibraryManagement
mvn compile
mvn exec:java -Dexec.mainClass="com.library.LibraryManagementApplication"
```

## `LibraryManagementBoot/` — Exercise 9 (Spring Boot)

Spring Web + Spring Data JPA + H2, with a `Book` entity, `BookRepository`, and
a full CRUD `BookController` under `/api/books`.

Run it with:
```bash
cd LibraryManagementBoot
mvn spring-boot:run
```
Then hit `http://localhost:8080/api/books` (GET/POST/PUT/DELETE) or the H2
console at `http://localhost:8080/h2-console`.

## Note on `applicationContext.xml`

The XML config uses `<context:component-scan>` + `@Service`/`@Repository`
annotations (Exercise 6 style). A commented-out pure-XML `<bean>` block is
included in the same file if you'd rather demonstrate Exercises 1, 2, 5, and 7
without annotations — just comment out the component-scan and uncomment that
block instead.
