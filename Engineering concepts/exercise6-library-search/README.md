# Exercise 6: Library Management System

## Scenario
Building a library management system where users can search for books
by title or author.

## Search Algorithms
- **Linear Search**: checks each element one by one until a match is
  found or the list ends. Works on unsorted data.
- **Binary Search**: repeatedly halves the search space by comparing
  the target to the middle element. Requires the data to be sorted.

## Implementation
- `Book.java` — `bookId`, `title`, `author`
- `LibrarySearch.java` — `linearSearchByTitle` (unsorted),
  `binarySearchByTitle` (sorted)

## Complexity Analysis
| Algorithm | Time Complexity | Requires Sorted Data |
|-----------|------------------|------------------------|
| Linear Search | O(n) | No |
| Binary Search | O(log n) | Yes |

## When to use each
- **Small catalog or frequently-changing/unsorted data** (e.g. newly
  added books not yet re-indexed): linear search is simpler and avoids
  the cost of maintaining sorted order.
- **Large catalog with relatively stable data** (e.g. a public library
  with thousands of titles, searched far more often than updated):
  binary search is dramatically faster — O(log n) vs O(n) — and the
  one-time cost of sorting/indexing pays off quickly.
- In practice, a real library system would use a **database index**
  (B-tree) on title/author, which behaves like an always-sorted
  structure without manual re-sorting.

## Run
```
javac Book.java LibrarySearch.java
java LibrarySearch
```
