import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Stack;

public class project1 {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      FileScanner file;
      String filename;
      while (true) {
         System.out.println("Enter a Java source file: ");
         // filename = input.nextLine();
         filename = "testfile.java";
         try {
            file = new FileScanner(filename);
            break;
         }
         catch (FileNotFoundException e) {
            System.out.println("File not found, try again");
         }
      }
      input.close();

      // create stack object to store delimiters
      Stack<Character> delimiterStack = new Stack<>();

      while (true) {
         char c = file.nextSignificantChar();
         // System.out.print(c);
         if (c != '\0') { //continue until reaching null character
            // add opening delimiters to stack
            if (c == '(' || c == '{' || c == '[') {
               delimiterStack.add(c);
            }

            // check for closing delimiters
            else if (c == ')' || c == '}' || c == ']') {
               // delimiter error if stack is empty when adding closing delimiter
               if (delimiterStack.size() == 0) {
                  System.out.println("incorrect delimiter " + c + " detected at " + file.cursorLocation());
                  break;
               }

               // check if top of stack doesn't match closing delimiter
               char last = delimiterStack.pop();
               if ((c == ')' && last != '(') || (c == '}' && last != '{') || (c == ']' && last != '[')) {
                  System.out.println("Incorrect closing delimiter " + c + " detected at " + file.cursorLocation());
                  break;
               }
            }
         }
            
         //reached end of file with no delimiter issues
         else {
            if (delimiterStack.size() != 0) {
               System.out.println("remaining delimiter " + delimiterStack.pop() + " not closed by end of file");
               break;
            }
            else {
               System.out.println("no delimiter errors");
               break;
            }
         }
      }
   }
}