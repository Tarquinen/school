/**
 * Daniel Smolsky
 * Programming Project 3 - Binary Search Tree
 * March 15, 2024
 * Represents an exception that is thrown when the input string provided to the Tree class
 * is not a valid representation of a binary search tree. This can occur if the string is
 * missing parentheses or contains non-integer values or has an incorrect string representation.
 */

public class InvalidInputSyntax extends Exception {
   private String message;

   public InvalidInputSyntax() {
      this("Invalid syntax");
   }

   public InvalidInputSyntax(String message) {
      super(message);
      this.message = message;
   }

   @Override
   public String getMessage() {
      return message;
   }
}