package Week5;
import java.util.Scanner;

public class Exercise05_47 {
    public static void main () {
        Scanner input = new Scanner(System.in);
            String isbn;
            boolean invalid = false;
            System.out.println("Enter the first 12 digits of an ISBN-13 as a string: ");
            isbn = input.nextLine();
            int d[] = new int[12];        

            if (isbn.length() == 12) {
                for (int i = 0; i < 12; i++) {
                    if (!Character.isDigit(isbn.charAt(i))) {
                        System.out.println(isbn + " is an invalid input");
                        invalid = true;
                        break;
                    }
                    d[i] = Integer.parseInt(isbn.substring(i, i + 1)); 
                }
            }
            else {
                System.out.println(isbn + " is an invalid input");
                invalid = true;
            }

            if (!invalid) {
                int isbn13 = 10 - (d[0] + 3*d[1] + d[2] + 3*d[3] + d[4] + 3*d[5] + d[6] + 3*d[7] + d[8] + 3*d[9] + d[10] + 3*d[11]) % 10; 
                if (isbn13 == 10) {
                    isbn13 = 0;
                }
                String fullIsbn = isbn + isbn13;
                System.out.println("The ISBN-13 number is " + fullIsbn); 
            }
        input.close();
    }
}
