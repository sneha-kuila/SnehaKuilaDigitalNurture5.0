package com.cognizant.jwtauth.controller;

import com.cognizant.jwtauth.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public record LoginRequest(String username, String password) {}

    /**
     * Demo-only login: hardcoded credentials, no database.
     * A real app would check against a user store with hashed passwords.
     */
    @PostMapping("/auth/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.username()) && "password123".equals(request.password())) {
            String token = jwtTokenProvider.createToken(request.username());
            return Map.of("token", token);
        }
        throw new RuntimeException("Invalid username or password");
    }
}
