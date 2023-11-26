package Week3;
import java.util.Scanner;
import java.time.Month;

public class Exercise03_11 {
    public static void main () {
        Scanner input = new Scanner(System.in);

        int month;
        System.out.println("Enter a month in the year (e.g., 1 for Jan):");
        while (true) {
            month = input.nextInt();
            if (month > 0 && month <= 12)
                break;
            else
                System.out.println("Enter a value 1-12");
        }

        String monthName = Month.of(month).name();
        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();

        int year;
        System.out.println("Enter a year: ");
        while (true) {
            year = input.nextInt();
            if (year > 0)
                break;
            else
                System.out.println("Enter a positive int");
        }
    
        boolean leap = (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        int daysInInputMonth = days_in_month(month, leap);
        System.out.println(monthName + " " + year + " has " + daysInInputMonth + " days");
        input.close();
    }

    public static int days_in_month (int month, boolean leap) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) 
            return 31;
        else if (month == 4 || month == 6 || month == 9 || month == 11) 
            return 30;
        else if (month == 2 && leap) 
            return 29;
        else
            return 28;    
    }
}