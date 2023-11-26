package Week4;
import java.util.Scanner;
public class Exercise04_05 {
    public static void main () {
        Scanner input = new Scanner(System.in);

        int sides;
        double length;
        double area;

        System.out.println("Enter the number of sides: ");
        sides = input.nextInt();
        System.out.println("Enter the length of the side: ");
        length = input.nextDouble();
        area = sides * Math.pow(length, 2) / (4 * Math.tan(Math.PI / sides));
        System.out.println("The area of the polygon is " + area);

        input.close();
    }

}
