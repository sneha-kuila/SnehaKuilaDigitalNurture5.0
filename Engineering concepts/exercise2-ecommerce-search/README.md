# Exercise 2: E-commerce Platform Search Function

## Scenario
Optimizing the search functionality of an e-commerce platform for fast
performance.

## Big O Notation
Big O describes how an algorithm's running time (or space) grows as the
input size `n` grows, focusing on the worst-case upper bound. It lets us
compare algorithms independent of hardware or implementation details —
e.g. O(1) constant, O(log n) logarithmic, O(n) linear, O(n log n),
O(n²), etc.

### Best / Average / Worst case for search
- **Best case**: target is the first element checked (or middle element
  for binary search) → O(1)
- **Average case**: target is somewhere in the middle of the search
  space → O(n) linear / O(log n) binary
- **Worst case**: target is absent or at the very end → O(n) linear /
  O(log n) binary

## Implementation
- `Product.java` — `productId`, `productName`, `category`
- `SearchDemo.java` — `linearSearch` over an unsorted array,
  `binarySearch` over a sorted array

## Complexity Analysis
| Algorithm      | Best  | Average | Worst   | Requires sorted data? |
|-----------------|-------|---------|---------|-------------------------|
| Linear Search   | O(1)  | O(n)    | O(n)    | No |
| Binary Search   | O(1)  | O(log n)| O(log n)| Yes |

## Which is more suitable?
**Binary search** is far more suitable for a search-heavy e-commerce
platform once the catalog is large, since O(log n) scales dramatically
better than O(n) — e.g. searching 1,000,000 products takes ~20
comparisons vs up to 1,000,000. The tradeoff is that the data must be
kept sorted (or indexed), which adds cost on insert — in practice this
is handled by a database index (e.g. B-tree) rather than re-sorting an
array on every insert.

## Run
```
javac Product.java SearchDemo.java
java SearchDemo
```
