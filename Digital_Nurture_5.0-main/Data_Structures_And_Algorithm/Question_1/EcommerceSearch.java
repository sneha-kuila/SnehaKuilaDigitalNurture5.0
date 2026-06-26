package Week_1.Data_Structures_And_Algorithm.Question_1;
import java.util.*;
public class EcommerceSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of products:- ");
        int n = sc.nextInt();
        Product[]products=new Product[n];
        for(int i=0;i<n;i++){
            System.out.println("\nEnter the details of Product "+(i+1));
            System.out.println("Enter product Id:- ");
            int productId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the product Name:- ");
            String productName = sc.nextLine();
            System.out.println("Enter the category:- ");
            String category = sc.nextLine();
            products[i]=new Product(productId,productName,category);
        }
        System.out.println("Enter the targetId:- ");
        int targetId = sc.nextInt();
        Product result1 = LinearSearch.searchProducts(products,targetId);
        System.out.println("\nLinear Search Result:- ");
        if(result1!=null){
            result1.display();
        }else{
            System.out.println("Product not found");
        }
        Product result2 = BinarySearch.searchProducts(products,targetId);
        System.out.println("\nBinary Search Result:- ");
        if(result2!=null){
            result2.display();
        }else{
            System.out.println("Product not Found");
        }
        sc.close();
    }
}
