package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Exercise 3: Logs method execution time for all methods in com.library.service.
 * Exercise 8: Adds simple "before" and "after" advice as well, to demonstrate
 *             separating cross-cutting concerns (logging) from business logic.
 */
@Aspect
public class LoggingAspect {

    // Exercise 8: Before advice
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[BEFORE] Executing: " + joinPoint.getSignature().getName());
    }

    // Exercise 8: After advice
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AFTER] Completed: " + joinPoint.getSignature().getName());
    }

    // Exercise 3: Around advice to measure execution time
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("[TIMING] " + joinPoint.getSignature().getName()
                + " executed in " + duration + "ms");
        return result;
    }
}
