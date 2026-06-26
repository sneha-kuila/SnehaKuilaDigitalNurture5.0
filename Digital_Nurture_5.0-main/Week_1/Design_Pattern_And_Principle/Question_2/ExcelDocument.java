package Week_1.Design_Pattern_And_Principle.Question_2;

public class ExcelDocument implements Document{
    @Override
    public void open() {
        System.out.println("Opening Excel Sheet");
    }
}
