package com.cognizant.resilientgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @GetMapping("/fallback/slow-endpoint")
    public String fallback() {
        logger.warn("Fallback triggered - flaky-service is failing, slow, or the circuit is OPEN");
        return "flaky-service is currently unavailable. Please try again shortly.";
    }

}
