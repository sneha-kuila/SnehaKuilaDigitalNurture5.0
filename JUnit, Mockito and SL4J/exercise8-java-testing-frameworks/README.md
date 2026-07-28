# Java Testing Exercises Portfolio

Full, runnable solutions for the JUnit, Mockito, Spring Test, and SLF4J exercise
sheets. Built with **Java 11**, **Maven**, **Spring Boot 2.7**, **JUnit 5**, and
**Mockito**.

## How to run

```bash
mvn clean test        # run every test
mvn spring-boot:run   # run the Spring Boot app
```

## Exercise → File map

### 1. JUnit Basic Testing Exercises
| Exercise | File |
|---|---|
| 3. Assertions | `src/test/.../junit/AssertionsTest.java` |
| 4. AAA pattern + setup/teardown | `src/test/.../junit/CalculatorAaaTest.java` |

### 2. Advanced JUnit Testing Exercises
| Exercise | File |
|---|---|
| 1. Parameterized tests | `src/test/.../junit/EvenCheckerTest.java` |
| 2. Test suites | `src/test/.../junit/AllTests.java` |
| 3. Execution order | `src/test/.../junit/OrderedTests.java` |
| 4. Exception testing | `src/test/.../junit/ExceptionThrowerTest.java` |
| 5. Timeout/performance | `src/test/.../junit/PerformanceTesterTest.java` |

### 3. Mockito Hands-On Exercises
| Exercise | File |
|---|---|
| 1. Mocking & stubbing | `src/test/.../mockito/MyServiceTest.java` |
| 2. Verifying interactions | `src/test/.../mockito/VerifyInteractionTest.java` |
| 3. Argument matching | `src/test/.../mockito/ArgumentMatchingTest.java` |
| 4. Void methods | `src/test/.../mockito/VoidMethodTest.java` |
| 5. Multiple returns | `src/test/.../mockito/MultipleReturnsTest.java` |
| 6. Interaction order | `src/test/.../mockito/InteractionOrderTest.java` |
| 7. Void methods + exceptions | `src/test/.../mockito/VoidMethodExceptionTest.java` |

### 4. Advanced Mockito Hands-On Exercises
| Exercise | File |
|---|---|
| 1. Repositories | `src/test/.../mockito/RepositoryServiceTest.java` |
| 2. REST APIs | `src/test/.../mockito/ApiServiceTest.java` |
| 3. File I/O | `src/test/.../mockito/FileServiceTest.java` |
| 4. Network | `src/test/.../mockito/NetworkServiceTest.java` |
| 5. Multiple return values | `src/test/.../mockito/MultiReturnRepositoryTest.java` |

### 5. Mocking Dependencies in Spring Tests using Mockito
| Exercise | File |
|---|---|
| 1. Controller test | `src/test/.../user/UserControllerTest.java` |
| 2. Service test | `src/test/.../user/UserServiceTest.java` |
| 3. Integration test | `src/test/.../user/UserIntegrationTest.java` |

### 6. JUnit + Spring Test Exercises
| Exercise | File |
|---|---|
| 1. Basic unit test | `src/test/.../calculator/CalculatorServiceTest.java` |
| 2. Mock repository | `src/test/.../user/UserServiceTest.java` |
| 3. MockMvc controller | `src/test/.../user/UserControllerTest.java` |
| 4. Integration test | `src/test/.../user/UserIntegrationTest.java` |
| 5. POST endpoint | `src/test/.../user/UserControllerPostTest.java` |
| 6. Exception handling | `src/test/.../user/UserServiceExceptionTest.java` |
| 7. Custom repository query | `src/test/.../user/UserRepositoryTest.java` |
| 8. Controller exception handling | `src/test/.../user/GlobalExceptionHandlerTest.java` |
| 9. Parameterized test | `src/test/.../calculator/CalculatorServiceParameterizedTest.java` |

### 7. Logging using SLF4J
| Exercise | File |
|---|---|
| 1. Error/warning levels | `src/main/.../logging/LoggingExample.java` |
| 2. Parameterized logging | `src/main/.../logging/ParameterizedLoggingExample.java` |
| 3. Different appenders | `src/main/.../logging/AppenderExample.java` + `src/main/resources/logback.xml` |

## Project structure

```
src/main/java/com/example/testing/
  calculator/   CalculatorService
  user/         User, UserRepository, UserService, UserController, GlobalExceptionHandler
  external/     ExternalApi, MyService
  api/          RestClient, ApiService
  fileio/       SimpleFileReader, SimpleFileWriter, FileService
  network/      NetworkClient, NetworkService
  repo/         Repository, Service
  logging/      LoggingExample, ParameterizedLoggingExample, AppenderExample
  misc/         EvenChecker, ExceptionThrower, PerformanceTester

src/test/java/com/example/testing/
  calculator/   unit + parameterized tests
  user/         service/controller/integration/repository tests
  mockito/      all Mockito basic + advanced exercises
  junit/        all JUnit basic + advanced exercises
```
