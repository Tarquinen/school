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
import java.util.HashMap;

public class project1 {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      CodeScanner file;
      while (true) {
         System.out.println("Enter a Java source file: ");
         String filename = input.nextLine();
         try {
            // repeat create file object until successful
            file = new CodeScanner(filename);
            break;
         }
         catch (FileNotFoundException e) {
            System.out.println("File not found, try again");
         }
      }
      input.close();
      
      //create map object to store delimiter pairs
      HashMap<Character, Character> delimiterPairs = new HashMap<>();
      delimiterPairs.put('(', ')');
      delimiterPairs.put('{', '}');
      delimiterPairs.put('[', ']');

      // create stack object to store delimiters
      Stack<Character> delimiterStack = new Stack<>();

      // enable the following print if you want to see the output of CodeScanner to verify correct parsing
      // file.print();

      while (true) {
         char c = file.nextSignificantChar();
         if (c != '\0') { //continue until reaching null character
            // add opening delimiters to stack
            if (delimiterPairs.containsKey(c)) {
               delimiterStack.add(c);
            }

            // check for closing delimiters
            else if (delimiterPairs.containsValue(c)) {
            // Delimiter error if stack is empty when adding closing delimiter
               if (delimiterStack.isEmpty()) {
                  System.out.println("Incorrect closing delimiter " + c + " detected at " + file.cursorLocation());
                  break;
               }

               // Check if top of stack doesn't match closing delimiter
               char last = delimiterStack.pop();
               if (delimiterPairs.get(last) != c) {
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
               file.close();
               break;
            }
         }
      }
   }
}