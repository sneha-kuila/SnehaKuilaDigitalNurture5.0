package com.example.testing.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Doc 1 - Exercise 2: Parameterized logging */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String user = "Alice";
        int loginCount = 5;

        // Single parameter
        logger.info("User {} logged in", user);

        // Multiple parameters
        logger.info("User {} has logged in {} times", user, loginCount);

        // Exception logging with parameters
        try {
            throw new RuntimeException("Simulated failure");
        } catch (RuntimeException e) {
            logger.error("Login failed for user {}", user, e);
        }
    }
}
