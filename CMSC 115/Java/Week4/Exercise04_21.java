package Week4;
import java.util.Scanner;

public class Exercise04_21 {
    public static void main () {
        Scanner input = new Scanner(System.in);
            String ssn = "";
            System.out.println("Enter a SSN: ");
            ssn = input.next();
            boolean ssnStart;
            boolean ssnMid;
            boolean ssnEnd;
            boolean ssnDashes;

            /*
            if (ssn.length() >= 11) {
                ssnStart = Character.isDigit(ssn.charAt(0)) && Character.isDigit(ssn.charAt(1)) && Character.isDigit(ssn.charAt(2));
                ssnMid = Character.isDigit(ssn.charAt(4)) && Character.isDigit(ssn.charAt(5));
                ssnEnd = Character.isDigit(ssn.charAt(7)) && Character.isDigit(ssn.charAt(8)) && Character.isDigit(ssn.charAt(9)) && Character.isDigit(ssn.charAt(10));
                ssnDashes = ssn.charAt(3) == '-' && ssn.charAt(6) == '-';
                if (ssnStart && ssnMid && ssnEnd & ssnDashes) {
                    System.out.println(ssn + "  is a valid social security number");
                }
                else {
                    System.out.println(ssn + " is an invalid social security number");
                }
            }
            else {
                System.out.println(ssn + " is an invalid social security number");
            }
            */

            if (ssn.length() >= 11) {
                ssnStart = isBetterDigit(ssn, 0, 2);
                ssnMid = isBetterDigit(ssn, 4, 5);
                ssnEnd = isBetterDigit(ssn, 7, 10);
                ssnDashes = ssn.charAt(3) == '-' && ssn.charAt(6) == '-';
                if (ssnStart && ssnMid && ssnEnd & ssnDashes) {
                    System.out.println(ssn + "  is a valid social security number");
                }
                else {
                    System.out.println(ssn + " is an invalid social security number");
                }
            }
            else {
                System.out.println(ssn + " is an invalid social security number");
            }
        input.close();
        }

    public static boolean isBetterDigit (String ssn, int charStart, int charEnd) {
        for (int i = 0; i < (ssn.substring(charStart, charEnd + 1).length()); i++) {
            if (Character.isDigit(ssn.charAt(i + charStart))) {
                //System.out.println("digit " + (charStart + i));
                continue;
            }
            else {
                //System.out.println("not digit");
                return false;
            }
        }
        return true;

    }
}
