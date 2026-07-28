# Exercise 5: Task Management System

## Scenario
Building a task management system where tasks need to be added, deleted,
and traversed efficiently.

## Linked Lists
- **Singly Linked List**: each node holds data + a pointer to the next
  node. Traversal is one-directional (head → tail).
- **Doubly Linked List**: each node holds data + pointers to both the
  next and previous nodes, allowing traversal in both directions at the
  cost of extra memory per node.

## Implementation
- `Task.java` — `taskId`, `taskName`, `status`
- `TaskLinkedList.java` — singly linked list with `add`, `search`,
  `traverse`, `delete`

## Complexity Analysis
| Operation | Complexity | Why |
|-----------|------------|-----|
| Add (at head) | O(1) | Just relink the head pointer |
| Add (at tail, as implemented) | O(n) | Must walk to the end without a tracked tail pointer |
| Search | O(n) | Must traverse from head, no direct indexing |
| Traverse | O(n) | Visits every node |
| Delete | O(n) | Must find the node, then relink pointers |

*(Note: `add` could be made O(1) by keeping a `tail` reference — a
common optimization.)*

## Advantages of linked lists over arrays for dynamic data
- **No fixed capacity** — grows/shrinks one node at a time, no
  reallocation needed
- **O(1) insert/delete at the head** (or anywhere, given a reference to
  the node) — no shifting of elements like an array requires
- Tradeoff: no O(1) random access by index (must traverse), and each
  node has memory overhead for the pointer

This makes a linked list a good fit here since tasks are frequently
added and removed as they're completed, unlike Exercise 4's more
static employee records.

## Run
```
javac Task.java TaskLinkedList.java
java TaskLinkedList
```
