package Week5;
import java.util.Scanner;

public class Week5Discussion {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("These loops generate the first n fibonacci numbers. Enter n:");
        int n = input.nextInt();
        fibonacci_pretest(n);
        fibonacci_posttest(n);
        input.close();

    }
    public static void fibonacci_pretest(int n) {
        int fib[] = new int[n];
        fib[0] = 0;
        fib[1] = 1;
        
        // this loop generates the first n fibonacci numbers using a for loop which is a pretest loop
        // this loop doesn't always execute at least once, it checks the condition first
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        // this loop prints the first n fibonacci numbers
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.println(fib[i] + " generated with a pretest loop.");
            } else {
                System.out.print(fib[i] + ", ");
            }
        }
    }

    public static void fibonacci_posttest(int n) {
        int fib[] = new int[n];
        fib[0] = 0;
        fib[1] = 1;
        int i = 2;
        int j = 0;

        // this loop generates the first n fibonacci numbers using a do while loop which is a posttest loop
        // the difference between a pretest and posttest loop is that a posttest loop will always execute at least once and then check the condition
        do {
            fib[i] = fib[i-1] + fib[i-2]; 
            i++;
        }
        while (i < n);

        // this loop prints the first n fibonacci numbers
        do {
            if (j == n - 1) {
                System.out.println(fib[j] + " generated with a posttest loop.");
            } else {
                System.out.print(fib[j] + ", ");
            }
            j++;
        }
        while (j < n);
    }
}