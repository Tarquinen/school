import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {
   private Scanner file;
   private int charNum = 0;
   private int lineNum = 0;
   private String currLine = null;
   private boolean withinChar = false;
   private boolean withinString = false;
   private boolean withinMultiLineComment = false;
   
   public FileScanner(String name) throws FileNotFoundException {
      file = new Scanner(new File(name));
   }

   public String cursorLocation() {
      return "line number: " + lineNum + " character number: " + charNum;
   }

   public char nextChar() {
      //continue to next line when reached end of line
      if (currLine == null || charNum == currLine.length()) {
         if (file.hasNextLine()) {
            lineNum += 1;
            currLine = file.nextLine();
            charNum = 0;

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
      return currLine.charAt(charNum++);
   }

   public char nextSignificantChar() {
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
         if (charNum < currLine.length() && currLine.charAt(charNum) == '*') {
            withinMultiLineComment = true;
         }
      }
      else if (nextChar == '*' && withinMultiLineComment) {
         if (charNum < currLine.length() && currLine.charAt(charNum) == '/') {
            withinMultiLineComment = false;
            nextChar(); //extra skipped character to skip the / in */
            nextChar = nextChar();
         }
      }

      //recursive call to skip single-line comments
      if (nextChar == '/' && !withinChar && !withinString) {
         if (charNum < currLine.length() && currLine.charAt(charNum) == '/') {
            if (file.hasNextLine()) {
               lineNum += 1;
               currLine = file.nextLine();
               charNum = 0;
            }
            return nextSignificantChar();
         }
      }

      //execute recursion when within String, char literals, or multi line comment
      if (withinString || withinChar || withinMultiLineComment) {
         return nextSignificantChar();
      } 
      else {
         return nextChar;
      }
   }
}