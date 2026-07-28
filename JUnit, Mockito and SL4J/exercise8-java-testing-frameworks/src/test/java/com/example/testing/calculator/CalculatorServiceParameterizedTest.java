package com.example.testing.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Doc 4 - Exercise 9: Parameterized Test with JUnit */
public class CalculatorServiceParameterizedTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 200, 300"
    })
    void add_shouldReturnCorrectSum(int a, int b, int expectedSum) {
        assertEquals(expectedSum, calculatorService.add(a, b));
    }
}
