import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

public class PointPane extends Pane {
   private ArrayList<GridPoint> points;
   private ArrayList<GridPoint> maximalPoints;

   // default constructor
   public PointPane() {
      this.points = new ArrayList<GridPoint>();
      paneClicked();
   }

   // constructor with initial points
   public PointPane(ArrayList<GridPoint> initialPoints) {
      this.points = initialPoints;
      for (GridPoint point : points) {
         placePoint(point);
      }
      paneClicked();
      makeMaximalList();
   }

   // place a point on the pane
   private void placePoint(GridPoint g) {
      Circle point = new Circle(5, Color.BLACK);
      point.setCenterX(g.getX());
      point.setCenterY(g.getY());
      this.getChildren().add(point);
      point.setOnMouseClicked(f -> { // remove point on right click
         if (f.getButton() == MouseButton.SECONDARY) {
            this.getChildren().remove(point);
            points.remove(g);
            makeMaximalList();
         }
      });
   }

   // add a point to the pane when clicked
   private void paneClicked() {
      this.setOnMouseClicked(e -> {
         if (e.getButton() == MouseButton.PRIMARY) {
            double x = e.getX();
            double y = e.getY();
            GridPoint newPoint = new GridPoint(x, y);
            points.add(newPoint);
            placePoint(newPoint);
            makeMaximalList();
         }
      });
   }
   
   // populate maximalPoints with the maximal list of points
   private void makeMaximalList() {
      if (points.size() == 0) { // if there are no points, clear the pane
         this.getChildren().clear();
         return;
      }

      // sort the points in reverse x-axis order for easier accessibility
      Collections.sort(points, Collections.reverseOrder());
      
      // create list to store maximal points
      maximalPoints = new ArrayList<GridPoint>();
      int maximalPointsIndex = 0;
      
      // initial maximal point is the point with the highest x coordinate
      maximalPoints.add(points.get(0));
      
      for (GridPoint point : points) { // this will iterate through the points in reverse x value order
         GridPoint prevMaximalPoint = maximalPoints.get(maximalPointsIndex); // get the previous maximal point
         
         // check if the next point is on the same x coordinate and higher y, if so replace
         if (point.getX() == prevMaximalPoint.getX() && point.getY() < prevMaximalPoint.getY()) { // < because y decreases as points go up
            maximalPoints.set(maximalPointsIndex, point);
         }
         // otherwise, if its above the previous maximal point, add to the list
         else if (point.getY() < prevMaximalPoint.getY()) {
            // add a "fake" point to assist line drawing
            maximalPoints.add(new GridPoint(point.getX(), prevMaximalPoint.getY())); 
            // add the actual maximal point
            maximalPoints.add(point);
            maximalPointsIndex += 2;
         }
      }
      
      // add a "fake" point along x and y axis to assist line drawing
      maximalPoints.add(0, new GridPoint(maximalPoints.get(0).getX(), 500)); // this will break if the window size changes
      maximalPoints.add(new GridPoint(0, maximalPoints.get(++maximalPointsIndex).getY()));
      
      lineBetweenPoints(maximalPoints); // draw lines between points
   }

   // draw lines between maximal and "fake" points 
   private void lineBetweenPoints(ArrayList<GridPoint> maximalPoints) {
      // clear the pane of any previous lines
      for (int i = 0; i < this.getChildren().size(); i++) {
         if (this.getChildren().get(i) instanceof Line) {
            this.getChildren().remove(i);
            i--;
         }
      }
      // draw line between every maximal point
      for (int i = 0; i < maximalPoints.size() - 1; i++) {
         GridPoint point1 = maximalPoints.get(i);
         GridPoint point2 = maximalPoints.get(i + 1);
         Line line = new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY());
         this.getChildren().add(line);
         line.toBack(); // makes clicking the points easier
      }
   }
}
