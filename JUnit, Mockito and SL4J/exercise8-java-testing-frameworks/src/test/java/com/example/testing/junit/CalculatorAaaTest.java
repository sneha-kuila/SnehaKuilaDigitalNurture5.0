package com.example.testing.junit;

import com.example.testing.calculator.CalculatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Doc 8 - Exercise 4: Arrange-Act-Assert pattern with setup/teardown (JUnit 5 style) */
public class CalculatorAaaTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        // Arrange (shared fixture)
        calculatorService = new CalculatorService();
    }

    @AfterEach
    void tearDown() {
        calculatorService = null;
    }

    @Test
    void testAdd() {
        // Arrange
        int a = 4, b = 6;

        // Act
        int result = calculatorService.add(a, b);

        // Assert
        assertEquals(10, result);
    }
}
