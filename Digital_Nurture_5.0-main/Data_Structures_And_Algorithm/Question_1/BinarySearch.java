package Week_1.Data_Structures_And_Algorithm.Question_1;

public class BinarySearch {
    public static Product searchProducts(Product[]products,int targetId){
        int n = products.length;
        int start = 0,end=n-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(products[mid].productId==targetId){
                return products[mid];
            }else if(products[mid].productId>targetId){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return null;
    }
}