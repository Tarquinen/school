import java.util.Scanner;
import java.io.FileNotFoundException;

public class project1 {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      FileScanner file;
      String filename;
      while (true) {
         System.out.print("Enter a Java source file: ");
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

      for (int i = 0; i < 500; i++) {
         char c = file.parsedNextChar();
         if (c != '\0') {
            System.out.print(c);
         }
         else {
            break;
         }
      }
   }
}