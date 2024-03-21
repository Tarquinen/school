import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {
   private File file;
   private Scanner inputScanner;
   int charIndex = 0;
   String currLine = null;
   boolean withinChar = false;
   boolean withinString = false;
   boolean withinlineComment = false;
   boolean withinMultiLineComment = false;
   
   public FileScanner(String name) throws FileNotFoundException {
      file = new File(name);
      inputScanner = new Scanner(file);
   }

   public char nextChar() {
      if (currLine == null || charIndex == currLine.length()) {
         if (inputScanner.hasNextLine()) {
            currLine = inputScanner.nextLine();
            charIndex = 0;
         }
      }
      return currLine.charAt(charIndex++);
   }

   public char parsedNextChar() {
      char nextChar = this.nextChar();

      if (nextChar == '\'' && !withinChar) {
         withinChar = true;
      }
      else if (nextChar == '\'' && withinChar) {
         withinChar = false;
         nextChar = this.nextChar();
      }

      if (nextChar == '\"' && !withinString) {
         withinString = true;
      }
      else if (nextChar == '\"' && withinString) {
         withinString = false;
         nextChar = this.nextChar();
      }

      if (nextChar == '/' && !withinChar && !withinString) {
         System.out.println("in here");
         // System.out.println("index: " + charIndex + " line length: " + currLine.length());
         // if (charIndex < currLine.length()) {
            if (charIndex < currLine.length() &&  currLine.charAt(charIndex) == '/') {
               if (inputScanner.hasNextLine()) {
                  currLine = inputScanner.nextLine();
                  charIndex = 0;

               }
               return parsedNextChar();
            }
         // }
      }


      
      if (withinString || withinChar) {
         return parsedNextChar();
      } 
      else {
         return nextChar;
      }
   }






}
