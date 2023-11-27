package Week7;
import java.util.Scanner;

public class Exercise07_03 {
    static int[] db = new int[101];

    public static void main () {
        System.out.println("Enter the integers between 1 and 100: ");
        Scanner input = new Scanner(System.in);
        int x;
        while (true) {
            x = input.nextInt();
            if (x == 0) {
                break;
            }
            increaseCount(x);
        }
        printCount(db);
        input.close();
    }

    public static void increaseCount (int x) {
        db[x]++;
    }

    public static void printCount (int[] db) {
        for (int i = 0; i < db.length; i++) {
            if (db[i] == 0) {
                continue;
            }
            else if (db[i] > 1) {
                System.out.println(i + " occurs " + db[i] + " times");
            }
            else {
                System.out.println(i + " occurs 1 time");
            }

        }
    }
    
}
