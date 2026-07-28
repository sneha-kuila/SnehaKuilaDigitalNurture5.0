package com.example.testing.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Doc 1 - Exercise 1: Logging error/warning levels */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
    }
}
