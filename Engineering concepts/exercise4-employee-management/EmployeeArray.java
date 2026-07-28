import java.util.Arrays;

/**
 * Fixed-capacity array representation of employee records.
 */
public class EmployeeArray {
    private Employee[] array;
    private int size;

    public EmployeeArray(int capacity) {
        array = new Employee[capacity];
        size = 0;
    }

    // O(1) amortized - simply places at the next free index
    public void add(Employee employee) {
        if (size >= array.length) {
            throw new IllegalStateException("Employee array is full");
        }
        array[size] = employee;
        size++;
    }

    // O(n) - linear scan since the array is unsorted by id
    public Employee search(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmployeeId() == employeeId) {
                return array[i];
            }
        }
        return null;
    }

    // O(n) - visits every element
    public Employee[] traverse() {
        return Arrays.copyOf(array, size);
    }

    // O(n) - find the element, then shift subsequent elements left
    public void delete(int employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("Employee " + employeeId + " not found");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    public static void main(String[] args) {
        EmployeeArray store = new EmployeeArray(100);
        store.add(new Employee(1, "Ravi", "Analyst", 45000));
        store.add(new Employee(2, "Sakshi", "Developer", 60000));

        System.out.println("Traverse: " + Arrays.toString(store.traverse()));
        System.out.println("Search 2: " + store.search(2));

        store.delete(1);
        System.out.println("After delete: " + Arrays.toString(store.traverse()));
    }
}
