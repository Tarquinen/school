import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise11_17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer m: ");
        int m = input.nextInt();
        int tempm = m;
        ArrayList<Integer> factors = new ArrayList<>();

        //create a list of all the smallest factors
        for (int i = 2; i <= tempm; i++) {
            while (tempm % i == 0) {
                factors.add(i);
                tempm /= i;
            }
        }
        
        //sort the factors
        Collections.sort(factors);

        int product = 1;
        //find the products that appear an odd amount of times
        for (int i = 0; i < factors.size(); i++) {
            //check unique only 
            if (i > 0 && factors.get(i - 1) == factors.get(i)) {
                continue;
            }

            int counter = 1;
            for (int j = i + 1; j < factors.size(); j++) {
                if (factors.get(j) == factors.get(i)) {
                    counter ++;
                }
                else        
                    break;
            }
            if (counter % 2 != 0) {
                product *= factors.get(i);
            }
        }        

        System.out.println("The smallest number n for m x n to be a perfect square is " + product);
        System.out.println("m x n is " + m * product);
        input.close();
    }
}