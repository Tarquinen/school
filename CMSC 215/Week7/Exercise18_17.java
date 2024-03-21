import java.util.Scanner;

public class Exercise18_17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String inString = input.nextLine();
        System.out.print("Enter a character: ");
        String inChar = input.nextLine();
        char[] chars = inString.toCharArray();
        char ch = inChar.charAt(0);
        input.close();
        System.out.println(ch + " appears " + count(chars, ch) + " times");
    }

    public static int count(char[] chars, char ch) {
        return count(chars, ch, chars.length - 1);
    }

    public static int count(char[] chars, char ch, int high) {
        if (high < 0) {
            return 0;
        }
        else if (chars[high] == ch) {
            return count(chars, ch, high - 1) + 1;
        }
        else
            return count(chars, ch, high - 1);
    }
}    