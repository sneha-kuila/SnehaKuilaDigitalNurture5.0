# Exercise 21: Using JSON Web Tokens (JWT) for Secure Communication

One Spring Boot 3 app (`jwt-auth-app`, port 8092) that issues and validates
**its own** JWTs — no external identity provider (unlike Exercise 19/20).
A `/auth/login` endpoint checks a hardcoded demo username/password and, if
correct, signs and returns a JWT. A custom filter then checks every other
request for a valid `Authorization: Bearer <token>` header before letting it
through to `/secure`.

Built with the modern `SecurityFilterChain` bean and `jjwt` 0.11.x (not the
deprecated `WebSecurityConfigurerAdapter` / `SignatureAlgorithm.HS256` string
API shown in older sample docs).

## Steps

### 1. Move & extract

```
move C:\Users\KIIT0001\Downloads\exercise21-jwt-auth.zip C:\Users\KIIT0001\COGNIZANT\
cd C:\Users\KIIT0001\COGNIZANT
tar -xf exercise21-jwt-auth.zip
dir exercise21-jwt-auth
```

### 2. Build & run

```
cd exercise21-jwt-auth\jwt-auth-app
mvn clean package
mvn spring-boot:run
```

Wait for `Started JwtAuthAppApplication`.

### 3. Log in to get a token

Browsers can't easily send a POST body, so use PowerShell:

```powershell
$body = @{ username = "admin"; password = "password123" } | ConvertTo-Json
$response = Invoke-RestMethod -Uri "http://localhost:8092/auth/login" -Method Post -Body $body -ContentType "application/json"
$token = $response.token
$token
```

You should see a JWT printed (three dot-separated parts).

### 4. Call the secured endpoint

```powershell
Invoke-RestMethod -Uri "http://localhost:8092/secure" -Headers @{ Authorization = "Bearer $token" }
```

Expected result:
```
Hello, admin! You accessed this with a self-issued JWT.
```

### 5. Confirm it actually rejects bad requests

No token:
```powershell
Invoke-RestMethod -Uri "http://localhost:8092/secure"
```
→ should fail with `401 Unauthorized`.

Wrong password at login:
```powershell
$body = @{ username = "admin"; password = "wrong" } | ConvertTo-Json
Invoke-RestMethod -Uri "http://localhost:8092/auth/login" -Method Post -Body $body -ContentType "application/json"
```
→ should fail (500/400 with an error message, since this demo throws a plain
exception rather than a proper 401 — that's a common next-step improvement,
not required for the exercise).

### 6. Push — from repo root

```
cd C:\Users\KIIT0001\COGNIZANT
git add exercise21-jwt-auth/
git status
git commit -m "Add self-issued JWT authentication with Spring Security 6"
git pull origin main
git push origin main
del exercise21-jwt-auth.zip
```

The JWT signing secret in `application.yml` is a demo-only placeholder string,
not a real third-party credential like Exercise 19/20 — so there's no need to
blank it out before committing. Still, in a real project this value should
never be hardcoded in a committed file; it belongs in an environment variable
or secrets manager.

## What this demonstrates vs. Exercises 19 & 20

- **Exercise 19** = external Authorization Server flow (Google logs the user in).
- **Exercise 20** = Resource Server flow (trust tokens issued by Google).
- **Exercise 21** = your own app is *both* the Authorization Server and the
  Resource Server — it issues its own tokens and validates them itself. This
  is the simplest self-contained JWT pattern, often used for small internal
  services or as a stepping stone before adopting a full OAuth2/OIDC provider.
