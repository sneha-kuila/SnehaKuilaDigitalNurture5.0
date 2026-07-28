package com.example.testing.junit;

import com.example.testing.misc.PerformanceTester;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

/** Doc 7 - Exercise 5: Timeout and Performance Testing */
public class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    void performTask_shouldCompleteWithinTimeLimit() {
        assertTimeout(Duration.ofMillis(500), () -> performanceTester.performTask());
    }
}
