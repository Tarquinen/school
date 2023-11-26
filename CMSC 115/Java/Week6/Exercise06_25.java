package Week6;
import java.util.Scanner;

public class Exercise06_25 {
    public static void main () {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter time in milliseconds: ");
        long millis = input.nextLong();
        System.out.println(convertMillis(millis));
        input.close();
    }

    public static String convertMillis (long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long currSeconds = seconds % 60;
        long currMinutes = minutes % 60;
        return hours + ":" + currMinutes + ":" + currSeconds;
    }
}
