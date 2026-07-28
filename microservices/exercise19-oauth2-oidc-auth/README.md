# Exercise 19: Centralized Authentication with OAuth 2.1 / OIDC

A Spring Boot 3 app that delegates login to an external Identity Provider
(Google, in this example) using OAuth 2.1 / OpenID Connect, with a
`SecurityFilterChain` bean (the current Spring Security 6 way — the old
`WebSecurityConfigurerAdapter` is deprecated/removed).

## What's included
- `SecurityConfig` — configures `oauth2Login()` and requires authentication
  for every page except `/`
- `HomeController` — serves a Thymeleaf page (`index.html`) that shows a
  "Login with Google" link when logged out, and a welcome message + logout
  button when logged in
- `UserController` — exposes `GET /user`, returning the raw OIDC claims
  (or OAuth2 attributes) as JSON so you can see exactly what the provider sent back
- `application.yml` — has placeholder Google OAuth2 client registration;
  you need to fill in real credentials (see below) before this will work

## Prerequisites
- Java 17
- Maven
- A Google Cloud project with OAuth 2.0 credentials (free, takes ~5 minutes)

## Getting Google OAuth2 credentials
1. Go to https://console.cloud.google.com/apis/credentials
2. Create a new project (or use an existing one)
3. Click **Create Credentials** -> **OAuth client ID**
4. Application type: **Web application**
5. Under **Authorized redirect URIs**, add:
   ```
   http://localhost:8090/login/oauth2/code/google
   ```
6. Click **Create** - you'll get a **Client ID** and **Client Secret**
7. Open `src/main/resources/application.yml` in this project and replace:
   ```yaml
   client-id: YOUR_GOOGLE_CLIENT_ID
   client-secret: YOUR_GOOGLE_CLIENT_SECRET
   ```
   with the real values.

## How to run
```
cd oauth2-client-app
mvn spring-boot:run
```

## Test
1. Open http://localhost:8090 in your browser
2. Click "Login with Google"
3. Sign in with your Google account and grant consent
4. You'll be redirected back, now showing "Welcome, <your name>!"
5. Click "View raw user claims (JSON)" (or go directly to
   http://localhost:8090/user) to see the OIDC claims returned by Google
   (sub, email, name, picture, etc.)
6. Click Logout to end the session and return to the anonymous view
