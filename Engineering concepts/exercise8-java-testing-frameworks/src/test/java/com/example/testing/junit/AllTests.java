package com.example.testing.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/** Doc 7 - Exercise 2: Test Suites and Categories */
@Suite
@SelectClasses({
        AssertionsTest.class,
        EvenCheckerTest.class,
        OrderedTests.class,
        ExceptionThrowerTest.class,
        PerformanceTesterTest.class
})
public class AllTests {
    // Running this class runs every test class listed above
}
