package com.cognizant.flakyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FlakyController {

    private final Random random = new Random();

    @GetMapping("/slow-endpoint")
    public String slowEndpoint() throws InterruptedException {
        // Simulate a slow response ~half the time (2.5s delay), which will trip
        // the gateway's TimeLimiter, and fail outright the other half of the time.
        int roll = random.nextInt(10);

        if (roll < 3) {
            // Immediate failure
            throw new RuntimeException("Simulated downstream failure");
        } else if (roll < 6) {
            // Slow response - long enough to trigger the gateway's time limiter
            Thread.sleep(2500);
        }

        return "Response from flaky-service at " + System.currentTimeMillis();
    }

}
