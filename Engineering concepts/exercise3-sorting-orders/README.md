# Exercise 3: Sorting Customer Orders

## Scenario
Sorting customer orders by `totalPrice` to prioritize high-value orders
on an e-commerce platform.

## Sorting algorithms overview
- **Bubble Sort**: repeatedly steps through the list, swapping adjacent
  elements that are out of order. Simple but inefficient.
- **Insertion Sort**: builds the sorted list one element at a time,
  inserting each new element into its correct position.
- **Quick Sort**: picks a pivot, partitions elements smaller/larger than
  it, and recursively sorts each partition. Very fast in practice.
- **Merge Sort**: recursively splits the list in half, sorts each half,
  then merges them back together in order. Stable and predictable.

## Implementation
- `Order.java` — `orderId`, `customerName`, `totalPrice`
- `SortingDemo.java` — `bubbleSort` (O(n²)), `quickSort` (O(n log n) average, in-place with partitioning)

## Complexity Analysis
| Algorithm   | Best     | Average   | Worst   | Space     | Stable? |
|--------------|----------|-----------|---------|-----------|---------|
| Bubble Sort  | O(n)     | O(n²)     | O(n²)   | O(1)      | Yes |
| Quick Sort   | O(n log n)| O(n log n)| O(n²)  | O(log n)  | No |

## Why Quick Sort is generally preferred
For large order volumes, Bubble Sort's O(n²) behavior becomes
impractically slow — sorting 10,000 orders could mean ~100 million
comparisons. Quick Sort's O(n log n) average case scales far better
(roughly 130,000 comparisons for the same input), making it the
practical choice for production systems. Quick Sort's worst case
(O(n²)) occurs on already-sorted or adversarial input with a poor pivot
choice, which is mitigated by using randomized or median-of-three pivot
selection.

## Run
```
javac Order.java SortingDemo.java
java SortingDemo
```
