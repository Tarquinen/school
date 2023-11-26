package Week5;
import java.util.Scanner;

public class Exercise05_01 {
	public static void main() {
		Scanner input = new Scanner(System.in);
		
		// Initialize variables
		double counter = 0; // to keep track of the sum of the numbers
		int counterNeg = 0; // to count the number of negative numbers
		int counterPos = 0; // to count the number of positive numbers
		double average = 0; // to store the average of the numbers
		double num = 0; // to store the input number
		
		while (true) {
			System.out.print("Enter an integer, the input ends if it is 0: ");
			num = input.nextDouble();
			counter += num; // add the input number to the sum

			if (num == 0 && (counterNeg + counterPos == 0)) {
				System.out.println("No numbers are entered except 0");
				break;
			}
			else if (num == 0) { 
				average = counter / (counterNeg + counterPos); // calculate the average
				System.out.println("The number of positives is " + counterPos);
				System.out.println("The number of negatives is " + counterNeg);
				System.out.println("The total is " + counter);
				System.out.println("The average is " + average);
				break;
			}
			else if (num < 0) {
				counterNeg++; // increment the counter for negative numbers
			}
			else  {
				counterPos++; // increment the counter for positive numbers
			}

		}		
		input.close();

	}
}


