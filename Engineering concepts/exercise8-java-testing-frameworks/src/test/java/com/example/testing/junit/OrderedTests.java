package com.example.testing.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Doc 7 - Exercise 3: Test Execution Order */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static final List<Integer> executionOrder = new ArrayList<>();

    @Test
    @Order(1)
    void firstTest() {
        executionOrder.add(1);
        assertEquals(List.of(1), executionOrder);
    }

    @Test
    @Order(2)
    void secondTest() {
        executionOrder.add(2);
        assertEquals(List.of(1, 2), executionOrder);
    }

    @Test
    @Order(3)
    void thirdTest() {
        executionOrder.add(3);
        assertEquals(List.of(1, 2, 3), executionOrder);
    }
}
