package com.example.testing.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Doc 4 - Exercise 1: Basic Unit Test for a Service Method */
public class CalculatorServiceTest {

    @Test
    void add_shouldReturnSumOfTwoNumbers() {
        CalculatorService calculatorService = new CalculatorService();

        int result = calculatorService.add(2, 3);

        assertEquals(5, result);
    }
}
