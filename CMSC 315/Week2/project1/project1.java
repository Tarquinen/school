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

      for (int i = 0; i < 110; i++) {
         System.out.print(file.parsedNextChar());
      }
   }
}