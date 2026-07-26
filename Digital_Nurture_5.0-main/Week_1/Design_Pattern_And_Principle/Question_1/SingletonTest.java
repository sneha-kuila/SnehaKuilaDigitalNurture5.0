package Week_1.Design_Pattern_And_Principle.Question_1;

public class SingletonTest {
    public static void main(String[] args) {
        Logger l1 = Logger.getInstancee();
        Logger l2 = Logger.getInstancee();
        System.out.println(l1.hashCode());
        System.out.println(l2.hashCode());
        //Both will have the same hashCode=> If same then it means that only one object is being created
        if(l1==l2){
            System.out.println("Only one instance is created");
        }else {
            System.out.println("Multiple instances exists");
        }
    }
}
