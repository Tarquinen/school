/* 
 * Daniel Smolsky
 * Programming Project 1 - Matching Delimiters
 * March 22, 2024
 * This class implements a program to check for matching delimiters in a Java source file.
 * It uses a stack to track opening delimiters and checks for corresponding closing delimiters.
 */

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
         filename = input.nextLine();
         try {
            // repeat create file object until successful
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
         System.out.print(c);
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
            
         else {
            // ensure no leftover open delimiters
            if (delimiterStack.size() != 0) {
               System.out.println("remaining delimiter " + delimiterStack.pop() + " not closed by end of file");
               break;
            }
            //reached end of file with no delimiter issues
            else {
               System.out.println("no delimiter errors");
               break;
            }
         }
      }
   }
}