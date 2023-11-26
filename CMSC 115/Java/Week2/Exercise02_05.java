package Week2;
import java.util.Scanner;

public class Exercise02_05 {
    public static void main () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the subtotal and a gratuity rate:");
        double subtotal = input.nextDouble();
        double gratuityRate = input.nextDouble();
        double gratuity = subtotal * gratuityRate * .01;
        double total = subtotal + gratuity;
        System.out.println("The gratuity is $" + gratuity + " and the total is $" + total);
        input.close();
    }
}
