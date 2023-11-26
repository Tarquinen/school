package Week3;
import java.util.Scanner;

public class GradeConverter {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for a numerical grade
        System.out.print("Enter your numerical grade: ");
        int numericalGrade = scanner.nextInt();
        
        // Using a nested if statement
        if (numericalGrade >= 90) {
            System.out.println("You got an A!");
        } else {
            if (numericalGrade >= 80) {
                System.out.println("You got a B!");
            } else {
                if (numericalGrade >= 70) {
                    System.out.println("You got a C!");
                } else {
                    if (numericalGrade >= 60) {
                        System.out.println("You got a D!");
                    } else {
                        System.out.println("You got an F!");
                    }
                }
            }
        }
        
        // Using a multi-way if statement
        if (numericalGrade >= 90) {
            System.out.println("You got an A!");
        } else if (numericalGrade >= 80) {
            System.out.println("You got a B!");
        } else if (numericalGrade >= 70) {
            System.out.println("You got a C!");
        } else if (numericalGrade >= 60) {
            System.out.println("You got a D!");
        } else {
            System.out.println("You got an F!");
        }
        
        // Using a switch statement
        switch (numericalGrade / 10) {
            case 10:
            case 9:
                System.out.println("You got an A!");
                break;
            case 8:
                System.out.println("You got a B!");
                break;
            case 7:
                System.out.println("You got a C!");
                break;
            case 6:
                System.out.println("You got a D!");
                break;
            default:
                System.out.println("You got an F!");
        }
        
        scanner.close();
    }
}
