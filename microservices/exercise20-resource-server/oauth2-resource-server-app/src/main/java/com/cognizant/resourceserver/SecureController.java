package com.cognizant.resourceserver;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecureController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This endpoint is open to everyone, no token needed.";
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint. You reached it with a valid JWT!";
    }

    /**
     * Shows the claims Spring Security extracted from the validated JWT,
     * so you can confirm the token was actually parsed and verified.
     */
    @GetMapping("/secure/claims")
    public Map<String, Object> claims(JwtAuthenticationToken authentication) {
        Jwt jwt = authentication.getToken();
        return jwt.getClaims();
    }
}
