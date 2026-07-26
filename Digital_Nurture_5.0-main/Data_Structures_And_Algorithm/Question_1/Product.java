package Week_1.Data_Structures_And_Algorithm.Question_1;

public class Product {
    int productId;
    String productName;
    String category;
    public Product(int productId,String productName,String category){
        this.productId=productId;
        this.productName=productName;
        this.category=category;
    }
    void display(){
        System.out.println("ID:- "+productId+" "+"Name:- "+productName+" "+"Category:- "+category);
    }
}