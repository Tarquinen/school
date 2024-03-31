import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class project2 extends Application {
   static ArrayList<GridPoint> points = new ArrayList<GridPoint>();
   
   public void start(Stage primaryStage) {
      PointPane pane = new PointPane(points);
      Scene scene = new Scene(pane, 500, 500);
      primaryStage.setTitle("Project 2");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("points.txt"));
      
      // read in the points from the file and add them to the points ArrayList
      while (input.hasNextLine()) {
         String line = input.nextLine();
         String[] parts = line.split(" ");
         double x = Double.parseDouble(parts[0]);
         double y = Double.parseDouble(parts[1]);
         GridPoint newPoint = new GridPoint(x, y);
         points.add(newPoint);
      }

      input.close();
      launch(args);
   }
}