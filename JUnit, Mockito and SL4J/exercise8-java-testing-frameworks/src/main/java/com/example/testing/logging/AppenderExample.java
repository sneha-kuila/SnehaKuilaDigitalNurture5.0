package com.example.testing.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Doc 1 - Exercise 3: Using different appenders.
 * The console and file appenders themselves are configured in
 * src/main/resources/logback.xml — this class just logs at
 * different levels so both appenders receive output.
 */
public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - visible in console + app.log");
        logger.info("Info message - visible in console + app.log");
        logger.warn("Warning message - visible in console + app.log");
        logger.error("Error message - visible in console + app.log");
    }
}
