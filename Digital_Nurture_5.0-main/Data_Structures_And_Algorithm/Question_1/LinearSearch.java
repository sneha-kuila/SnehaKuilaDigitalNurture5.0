package Week_1.Data_Structures_And_Algorithm.Question_1;

public class LinearSearch {
    public static Product searchProducts(Product[]products,int targetId){
        for(int i=0;i<products.length;i++){
            if(products[i].productId==targetId){
                return products[i];
            }
        }
        return null;
    }
}