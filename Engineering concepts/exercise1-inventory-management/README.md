# Exercise 1: Inventory Management System

## Scenario
Developing an inventory management system for a warehouse where efficient
data storage and retrieval are crucial.

## Why data structures and algorithms matter here
A warehouse may hold thousands of SKUs, with frequent lookups, stock
updates, and removals. Choosing the right data structure directly affects
how fast the system responds as inventory scales — a poor choice (e.g. a
plain unsorted list scanned linearly) turns every operation into an O(n)
search, which becomes a bottleneck at scale.

## Data structure chosen
A **HashMap** (Python `dict`), keyed by `productId`.
- Average-case O(1) for add, lookup, update, and delete.
- Alternative considered: `ArrayList` — simpler, but search/update/delete
  are O(n) since it requires scanning for the matching ID.

## Implementation
- `Product.java` — `productId`, `productName`, `quantity`, `price`
- `InventoryManager.java` — `HashMap<Integer, Product>` with
  `addProduct`, `updateProduct`, `deleteProduct`, `getProduct`,
  `listProducts`

## Complexity Analysis
| Operation | HashMap (dict) | ArrayList |
|-----------|-----------------|-----------|
| Add       | O(1) avg        | O(1) amortized (append) |
| Update    | O(1) avg         | O(n) (must find first) |
| Delete    | O(1) avg         | O(n) |
| Search    | O(1) avg         | O(n) |

## Optimization notes
- Hash collisions can degrade worst-case to O(n); Python's dict handles
  this internally via open addressing, so it's not a practical concern
  at typical warehouse scale.
- If range queries (e.g. "all products under ₹500") are needed, a
  secondary sorted structure (like a balanced BST or sorted index) could
  be added alongside the HashMap.

## Run
```
javac Product.java InventoryManager.java
java InventoryManager
```
