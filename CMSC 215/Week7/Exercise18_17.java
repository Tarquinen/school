import java.util.Scanner;




public class Exercise18_17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String in = input.nextLine();
        System.out.println("Enter a character: ");
        String inChar = input.nextLine();
        char[] chars = in.toCharArray();
        char ch = inChar.charAt(0);
        input.close();
        System.out.println(count(chars, ch));
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