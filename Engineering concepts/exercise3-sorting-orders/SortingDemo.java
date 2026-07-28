import java.util.Arrays;

public class SortingDemo {

    // O(n^2) - repeatedly swaps adjacent out-of-order elements
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    // O(n log n) average - divide and conquer using a pivot
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] ordersForBubble = {
                new Order(1, "Alice", 2500),
                new Order(2, "Bob", 800),
                new Order(3, "Charlie", 5200),
                new Order(4, "Dana", 150),
        };
        bubbleSort(ordersForBubble);
        System.out.println("Bubble sorted: " + Arrays.toString(ordersForBubble));

        Order[] ordersForQuick = {
                new Order(1, "Alice", 2500),
                new Order(2, "Bob", 800),
                new Order(3, "Charlie", 5200),
                new Order(4, "Dana", 150),
        };
        quickSort(ordersForQuick, 0, ordersForQuick.length - 1);
        System.out.println("Quick sorted: " + Arrays.toString(ordersForQuick));
    }
}
