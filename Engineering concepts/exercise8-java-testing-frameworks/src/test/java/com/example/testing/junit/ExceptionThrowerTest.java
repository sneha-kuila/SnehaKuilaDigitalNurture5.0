package com.example.testing.junit;

import com.example.testing.misc.ExceptionThrower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Doc 7 - Exercise 4: Exception Testing */
public class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void throwException_shouldThrowIllegalStateException() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                exceptionThrower::throwException
        );
        assertEquals("Something went wrong", exception.getMessage());
    }
}
