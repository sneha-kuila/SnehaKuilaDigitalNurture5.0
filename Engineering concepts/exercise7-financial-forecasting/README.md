# Exercise 7: Financial Forecasting

## Scenario
Building a financial forecasting tool that predicts future values based
on past data.

## Recursion
Recursion is when a function calls itself to solve smaller instances of
the same problem, combining their results into the final answer. It
simplifies problems that have a natural self-similar / repeating
structure — like compound growth, where each year's value depends on
the previous year's value.

## Implementation
`FinancialForecasting.java`:
- `futureValueRecursive(presentValue, growthRate, years)` — naive
  recursive compound-growth calculation:
  `futureValue(P, r, n) = P * (1 + r)^n`
- `futureValueMemoized(...)` — caches intermediate results (year → value)
  to avoid recomputation

## Complexity Analysis
| Version | Time | Space |
|---------|------|-------|
| Naive recursion | O(n) | O(n) call stack |
| Memoized recursion | O(n) first call, O(1) for cached years | O(n) cache |

Since each year builds directly on the one before it (no branching),
this recursion is linear, not exponential — unlike, say, naive
recursive Fibonacci. Still, the naive version does redundant work if
you call it repeatedly for different year counts with the same rate
(e.g. forecasting years 1–10 one at a time recomputes shared subpaths).

## Optimizing the recursive solution
- **Memoization** (implemented here) caches previously computed
  year-value pairs, turning repeated calls into O(1) lookups.
- **Iterative conversion**: since this recursion is tail-recursive, it
  can be rewritten as a simple loop, eliminating call-stack growth
  entirely and avoiding `StackOverflowError` risk for very large `n`.
- For truly large `n`, use **fast exponentiation** (`(1+r)^n` via
  exponentiation by squaring) to compute the result in O(log n) instead
  of O(n).

## Run
```
javac FinancialForecasting.java
java FinancialForecasting
```
