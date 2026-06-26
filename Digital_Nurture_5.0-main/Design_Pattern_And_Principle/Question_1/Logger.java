package Week_1.Design_Pattern_And_Principle.Question_1;

public class Logger {
    private static Logger instance;
    private Logger(){

    }
    public static Logger getInstancee(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }
    public void log(String message){
        System.out.println("LOG"+message);
    }
}
