package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Doc 5 - JWT hands-on: "Create authentication controller and configure it in SecurityConfig",
 * "Read Authorization header and decode the username and password",
 * "Generate token based on the user".
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    // NOTE: for real projects move this to application.properties / a secrets manager.
    private static final String SECRET_KEY = "secretkey";

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START");
        LOGGER.debug("authHeader={}", authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("END");
        return map;
    }

    private String getUser(String authHeader) {
        LOGGER.debug("Start getUser");
        String encodedCredentials = authHeader.replace("Basic ", "");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decoded = new String(decodedBytes);
        String user = decoded.substring(0, decoded.indexOf(":"));
        LOGGER.debug("Decoded user={}", user);
        return user;
    }

    private String generateJwt(String user) {
        LOGGER.debug("Start generateJwt");
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        // Token issue time
        builder.setIssuedAt(new Date());
        // Token expiry - 20 minutes from now
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        builder.signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        String token = builder.compact();
        LOGGER.debug("Generated token for user={}", user);
        return token;
    }
}
