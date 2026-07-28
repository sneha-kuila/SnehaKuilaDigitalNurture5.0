# Exercise 20: Configuring Authorization Servers and Resource Servers

One Spring Boot 3 app (`oauth2-resource-server-app`, port 8091) that acts as an
**OAuth2 Resource Server**: it does not log anyone in itself — it just validates
JWT access/ID tokens issued by an external Authorization Server (here, Google,
reusing the setup from Exercise 19) and protects endpoints based on the token
being valid.

Built with the modern `SecurityFilterChain` bean (Spring Security 6 / Spring Boot 3 style).

## Steps

### 1. Move & extract

```
move C:\Users\KIIT0001\Downloads\exercise20-resource-server.zip C:\Users\KIIT0001\COGNIZANT\
cd C:\Users\KIIT0001\COGNIZANT
tar -xf exercise20-resource-server.zip
dir exercise20-resource-server
```

### 2. Build

```
cd exercise20-resource-server\oauth2-resource-server-app
mvn clean package
```

### 3. Run

```
mvn spring-boot:run
```

Wait for:
```
Started ResourceServerApplication in X.XXX seconds
```

### 4. Test the public endpoint (no token needed)

Open in browser:
```
http://localhost:8091/public
```
You should see a plain success message.

### 5. Test the secured endpoint (token required)

Going to `http://localhost:8091/secure` directly in a browser will fail with
`401 Unauthorized` — browsers don't send an `Authorization: Bearer <token>`
header. You need a REST client (Postman, curl, or PowerShell's `Invoke-RestMethod`).

**Get a token:** reuse the Google ID token from Exercise 19. Run the
`oauth2-client-app` from Exercise 19, log in with Google, then open
`http://localhost:8090/user` in the browser and copy the long
`idToken.tokenValue` string from the JSON response. That IS a valid JWT
issued by `https://accounts.google.com`, which is exactly what this resource
server trusts (see `application.yml`).

**Call the secured endpoint** (PowerShell example):
```powershell
$token = "PASTE_YOUR_ID_TOKEN_HERE"
Invoke-RestMethod -Uri "http://localhost:8091/secure" -Headers @{ Authorization = "Bearer $token" }
```

Or with curl (if installed):
```
curl -H "Authorization: Bearer PASTE_YOUR_ID_TOKEN_HERE" http://localhost:8091/secure
```

If the token is valid and not expired, you'll get:
```
This is a secure endpoint. You reached it with a valid JWT!
```

If you call it with no token, or an expired one, or a garbage string, you get
`401 Unauthorized` — that's Spring Security's resource-server filter doing its job.

### 6. See the parsed claims

```
curl -H "Authorization: Bearer PASTE_YOUR_ID_TOKEN_HERE" http://localhost:8091/secure/claims
```
This returns the actual decoded JWT claims (email, name, issuer, expiry, etc.)
that Spring Security extracted after verifying the token's signature against
Google's public keys — proving the app really validated it, not just accepted
any string.

⚠️ **Important — token expiry:** Google ID tokens expire about 1 hour after
issue. If `/secure` starts returning 401 unexpectedly, just log in again via
the Exercise 19 app to get a fresh token.

### 7. Push — from repo root

```
cd C:\Users\KIIT0001\COGNIZANT
git add exercise20-resource-server/
git status
git commit -m "Add OAuth2 Resource Server validating JWTs with Spring Security 6"
git pull origin main
git push origin main
del exercise20-resource-server.zip
```

No secrets live in this project's `application.yml` (it only references a
public `issuer-uri`, no client ID/secret), so there's nothing to blank out
before committing this time. The `.gitignore` already excludes `target/` so
you won't hit the GitHub push-protection issue from Exercise 19 again.

## What this demonstrates vs. Exercise 19

- **Exercise 19** = *Authorization Server flow*: your app redirects the user
  to Google to log in, then Google hands back an ID token.
- **Exercise 20** = *Resource Server flow*: a completely separate app doesn't
  care how you got the token — it just checks "is this a valid, unexpired
  token from an issuer I trust?" and either lets the request through or
  rejects it with 401. This is the pattern real microservices use to protect
  APIs behind a shared identity provider.
