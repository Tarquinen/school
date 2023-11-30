package Week7;
import java.util.Scanner;

public class Exercise08_13 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns of the array: ");

        int numRows = input.nextInt();
        int numCols = input.nextInt();

        System.out.println("Enter the array:");

        double[][] userInput = new double[numRows][numCols];
        for (int rows = 0; rows < numRows; rows++) {
            for (int cols = 0; cols < numCols; cols++) {
                userInput[rows][cols] = input.nextDouble();
            }
        }

        int[] largestLocation = locateLargest(userInput);
        System.out.println("The location of the largest element is at (" + largestLocation[0] + ", " + largestLocation[1] + ")");
        input.close();
    }

    //returns the index of the largest element in a 2D array
    public static int[] locateLargest(double[][] a) {
        int rowLength = a.length;
        int colLength = a[0].length;
        double largestElement = a[0][0];
        int[] largestElementIndex = new int[]{0, 0};

        for (int rows = 0; rows < rowLength; rows ++) {
            for (int cols = 0; cols < colLength; cols ++) {
                if (largestElement < a[rows][cols]) {
                    largestElement = a[rows][cols];
                    largestElementIndex[0] = rows;
                    largestElementIndex[1] = cols;
                }
            }
        }
        return largestElementIndex;
    }
}