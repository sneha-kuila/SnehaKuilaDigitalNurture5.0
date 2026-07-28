package com.example.testing.junit;

import com.example.testing.misc.EvenChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Doc 7 - Exercise 1: Parameterized Tests */
public class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    void isEven_shouldReturnTrue_forEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    void isEven_shouldReturnFalse_forOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number));
    }
}
