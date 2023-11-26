package Week2;
// Pseudocode for calculating future investment value
// 1. Prompt the user to enter the investment amount
// 2. Read the investment amount from the user
// 3. Prompt the user to enter the annual interest rate in percentage
// 4. Read the annual interest rate from the user
// 5. Calculate the monthly interest rate by dividing the annual interest rate by 12
// 6. Convert the monthly interest rate to a decimal by dividing it by 100
// 7. Prompt the user to enter the number of years
// 8. Read the number of years from the user
// 9. Calculate the future investment value using the formula:
//    futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfYears * 12)
// 10. Display the future investment value to the user

import java.util.Scanner;

public class Exercise02_21 {
    public static void main () {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter investment amount: ");
        double investmentAmount = input.nextDouble();
        
        System.out.println("Enter annual interest rate in percentage: ");
        double annualInterestRate = input.nextDouble();
        double monthlyInterestRate = annualInterestRate / 12;
        monthlyInterestRate /= 100;
        
        System.out.println("Enter number of years: ");
        double numberOfYears = input.nextDouble();
        
        double futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfYears * 12);
        
        System.out.println("Future value is $" + futureInvestmentValue);
        input.close();
    }
}
