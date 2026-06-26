package Week_1.Data_Structures_And_Algorithm.Question_2;

import java.util.Scanner;

public class FinancialForecast {
    public static double forecast(double currentValue,double growthRate,int years){
        if(years==0){
            return currentValue;
        }
        return forecast(currentValue,growthRate,years-1)*(1+growthRate);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter current value:- ");
        double currentValue = sc.nextDouble();
        System.out.println("Enter Annual Growth Rate(in %):- ");
        double growthPercent = sc.nextDouble();
        System.out.println("Enter the number of years:- ");
        int years=sc.nextInt();
        double growthRate = growthPercent/100;
        double futureValue = forecast(currentValue,growthRate,years);
        System.out.printf("\nFuture value after %d years = %.2f:- ",years,futureValue);
        sc.close();
    }
}
