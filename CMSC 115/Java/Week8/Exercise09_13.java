package Week8;
import java.util.Scanner;

public class Exercise09_13 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows and columns in the array: ");
        int rows = input.nextInt();
        int cols = input.nextInt();
        System.out.println("Enter the array:");

        //fill array
        double[][] array = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = input.nextDouble();
            }
        }
    
        Location myLocation = locateLargest(array);
        System.out.println("The largest element is " + myLocation.maxValue + ", located at ("
        + myLocation.row + ", " + myLocation.column + ")");
        
        input.close();
    }

    public static Location locateLargest(double[][] a) {
        Location myLocation = new Location();
        int rowLength = a.length;
        int colLength = a[0].length;
        myLocation.setMax(a[0][0]);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (myLocation.maxValue < a[i][j]) {
                    myLocation.setMax(a[i][j]);
                    myLocation.setRow(i);
                    myLocation.setColumn(j);
                }
            }
        }
        return myLocation; 
    }
}

class Location {
    int row;
    int column;
    double maxValue;

    Location() {
        row = 0;
        column = 0;
    }

    void setRow(int row) {
        this.row = row;
    }

    void setColumn(int column) {
        this.column = column;
    }

    void setMax(double maxValue) {
        this.maxValue = maxValue;
    }
}