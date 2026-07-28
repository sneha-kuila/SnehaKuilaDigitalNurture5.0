import java.util.Arrays;
import java.util.Comparator;

public class SearchDemo {

    // O(n) - scans every element until a match is found
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equals(targetName)) {
                return p;
            }
        }
        return null;
    }

    // O(log n) - requires the array to be pre-sorted by productName
    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        int low = 0, high = sortedProducts.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedProducts[mid].getProductName().compareTo(targetName);
            if (cmp == 0) {
                return sortedProducts[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Shoes", "Footwear"),
                new Product(2, "Backpack", "Bags"),
                new Product(3, "Watch", "Accessories"),
                new Product(4, "Jacket", "Clothing"),
        };

        System.out.println("Linear search 'Watch': " + linearSearch(products, "Watch"));

        Product[] sorted = products.clone();
        Arrays.sort(sorted, Comparator.comparing(Product::getProductName));
        System.out.println("Binary search 'Watch': " + binarySearch(sorted, "Watch"));
    }
}
