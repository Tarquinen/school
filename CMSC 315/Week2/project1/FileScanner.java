import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {
   private File file;
   private Scanner inputScanner;
   private int charIndex = 0;
   private String currLine = null;
   private boolean withinChar = false;
   private boolean withinString = false;
   private boolean withinMultiLineComment = false;
   
   public FileScanner(String name) throws FileNotFoundException {
      file = new File(name);
      inputScanner = new Scanner(file);
   }

   public char nextChar() {
      //continue to next line when reached end of line
      if (currLine == null || charIndex == currLine.length()) {
         if (inputScanner.hasNextLine()) {
            currLine = inputScanner.nextLine();
            charIndex = 0;
            //recursive call when blank rows are encountered
            if (currLine.isEmpty()) {
               return nextChar();
            }
         }
         else {
            //return null character
            return '\0';
         }
      }
      return currLine.charAt(charIndex++);
   }

   public char parsedNextChar() {
      char nextChar = nextChar();

      //recursive call to skip character literals
      if (nextChar == '\'' && !withinChar) {
         withinChar = true;
      }
      else if (nextChar == '\'' && withinChar) {
         withinChar = false;
         nextChar = nextChar();
      }
      
      //recursive call to skip String literals
      if (nextChar == '\"' && !withinString) {
         withinString = true;
      }
      else if (nextChar == '\"' && withinString) {
         withinString = false;
         nextChar = nextChar();
      }

      //recursive call to skip multi-line comments
      if (nextChar == '/' && !withinMultiLineComment) {
         if (charIndex < currLine.length() && currLine.charAt(charIndex) == '*') {
            withinMultiLineComment = true;
         }
      }
      else if (nextChar == '*' && withinMultiLineComment) {
         if (charIndex < currLine.length() && currLine.charAt(charIndex) == '/') {
            withinMultiLineComment = false;
            nextChar(); //extra skipped character to skip the / in */
            nextChar = nextChar();
         }
      }

      //recursive call to skip single-line comments
      if (nextChar == '/' && !withinChar && !withinString) {
         if (charIndex < currLine.length() && currLine.charAt(charIndex) == '/') {
            if (inputScanner.hasNextLine()) {
               currLine = inputScanner.nextLine();
               charIndex = 0;
            }
            return parsedNextChar();
         }
      }


      //execute recursion when within String, char literals, or multi line comment
      if (withinString || withinChar || withinMultiLineComment) {
         return parsedNextChar();
      } 
      else {
         return nextChar;
      }
   }
}