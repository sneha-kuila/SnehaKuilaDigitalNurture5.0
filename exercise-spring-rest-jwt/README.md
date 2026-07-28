# spring-learn

Complete implementation of all 5 Cognizant hands-on documents:

1. `1_spring-rest-handson.docx` — Spring Core basics (bean loading, logging, scopes)
2. `2_spring-rest-handson.docx` — First REST services + MockMVC tests
3. `3_spring-rest-handson.docx` — Employee & Department REST services
4. `4_spring-rest-handson.docx` — POST/PUT/DELETE + validation + global exception handling
5. `5_JWT-handson.docx` — Spring Security + JWT authentication/authorization

## Build & run

```bash
mvn clean package -Dhttp.proxyHost=proxy.cognizant.com -Dhttp.proxyPort=6050 -Dhttps.proxyHost=proxy.cognizant.com -Dhttps.proxyPort=6050
mvn spring-boot:run
```

App runs on **http://localhost:8090** (see `application.properties`).

## Endpoints

| Method | URL | Notes |
|---|---|---|
| GET | `/hello` | Hello World, no auth |
| GET | `/authenticate` | Basic auth (user/admin, pwd `pwd`) → returns JWT |
| GET | `/countries` | Requires `Authorization: Bearer <token>` |
| GET | `/countries/{code}` | Case-insensitive country code lookup |
| POST | `/countries` | Body: `{"code":"IN","name":"India"}`, validated |
| GET | `/employees` | List all employees |
| PUT | `/employees` | Update an employee (full payload incl. department & skills) |
| DELETE | `/employees/{id}` | Delete an employee |
| GET | `/departments` | List all departments |

## Try it (curl)

```bash
# 1. Get a JWT
curl -s -u user:pwd http://localhost:8090/authenticate

# 2. Call a protected endpoint
curl -s -H "Authorization: Bearer <TOKEN_FROM_STEP_1>" http://localhost:8090/countries

# 3. Create a country
curl -i -H 'Content-Type: application/json' -X POST -s \
  -d '{"code":"IN","name":"India"}' \
  -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8090/countries
```

## Tests

```bash
mvn clean test
```

## Notes

- Built against Spring Boot 2.3.4.RELEASE / Java 8, matching the versions referenced in the hands-on docs.
- `SecurityConfig` represents the **final** state after all Doc 5 steps (JWT filter protecting all endpoints
  except `/authenticate`); the intermediate "common generated password" and "Basic-auth-only /countries"
  steps described early in Doc 5 are superseded by this final filter-based version — see inline comments.
- A full task-by-task checklist (every single step from all 5 docs) is in
  `Spring_REST_JWT_Handson_Task_Checklist.pdf` if you want to verify nothing was missed.
