import java.util.HashMap;
import java.util.Collection;
import java.util.Map;

/**
 * Uses a HashMap keyed by productId for O(1) average-case
 * add, update, and delete operations.
 */
public class InventoryManager {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void updateProduct(int productId, Integer quantity, Double price) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product " + productId + " not found");
        }
        if (quantity != null) product.setQuantity(quantity);
        if (price != null) product.setPrice(price);
    }

    public void deleteProduct(int productId) {
        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Product " + productId + " not found");
        }
        products.remove(productId);
    }

    public Product getProduct(int productId) {
        return products.get(productId);
    }

    public Collection<Product> listProducts() {
        return products.values();
    }

    public static void main(String[] args) {
        InventoryManager inv = new InventoryManager();
        inv.addProduct(new Product(1, "Laptop", 10, 55000));
        inv.addProduct(new Product(2, "Mouse", 100, 500));

        System.out.println("All products: " + inv.listProducts());

        inv.updateProduct(1, 8, null);
        System.out.println("After update: " + inv.getProduct(1));

        inv.deleteProduct(2);
        System.out.println("After delete: " + inv.listProducts());
    }
}
