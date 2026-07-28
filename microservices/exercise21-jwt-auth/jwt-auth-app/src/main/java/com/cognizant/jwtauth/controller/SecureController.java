package com.cognizant.jwtauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String secure(Authentication authentication) {
        return "Hello, " + authentication.getName() + "! You accessed this with a self-issued JWT.";
    }
}
