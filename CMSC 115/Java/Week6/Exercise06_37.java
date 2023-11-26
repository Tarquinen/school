package Week6;
import java.util.Scanner;

public class Exercise06_37 {
    public static void main () {
        Scanner input = new Scanner(System.in);
        int number;
        int width;
        System.out.print("Enter an Integer: ");
        number = input.nextInt();
        System.out.print("Enter a width: ");
        width = input.nextInt();
        System.out.println("The formatted number is " + format(number, width));
        input.close();
    }

    public static String format(int number, int width) {
        return String.format("%0" + width + "d", number);
    }
}
