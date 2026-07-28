# Exercise 4: Employee Management System

## Scenario
Developing an employee management system for a company where efficiently
managing employee records is crucial.

## Array representation in memory
An array stores elements in **contiguous memory locations**, so each
element can be accessed directly via `base_address + (index * element_size)`.

**Advantages:**
- O(1) direct access by index
- Cache-friendly due to memory locality (fast sequential access)
- Simple and low memory overhead compared to linked structures

## Implementation
- `Employee.java` — `employeeId`, `name`, `position`, `salary`
- `EmployeeArray.java` — fixed-capacity array with `add`, `search`,
  `traverse`, `delete`

## Complexity Analysis
| Operation | Complexity | Why |
|-----------|------------|-----|
| Add       | O(1) amortized | Places at next free index |
| Search    | O(n) | Array unsorted by id, requires linear scan |
| Traverse  | O(n) | Must visit every element |
| Delete    | O(n) | Find element, then shift subsequent elements left |

## Limitations of arrays
- **Fixed size** — capacity must be set upfront; resizing requires
  allocating a new array and copying all elements (O(n))
- **Costly insert/delete in the middle** — requires shifting elements
- Best suited when the number of records is **known/bounded** and
  **random access by index** matters more than frequent insert/delete

## When to use arrays vs. other structures
Use arrays when the dataset size is relatively stable and fast indexed
access is important. For a dynamically growing/shrinking dataset (e.g.
frequent employee onboarding/offboarding), a **LinkedList** or a
resizable structure (Java `ArrayList`) is often a better fit — see
Exercise 5 for a linked-list-based alternative.

## Run
```
javac Employee.java EmployeeArray.java
java EmployeeArray
```
