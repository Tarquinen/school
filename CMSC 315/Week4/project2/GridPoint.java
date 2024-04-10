/**
 * Daniel Smolsky
 * Programming Project 2 - Maximal Points
 * March 31, 2024
 * Represents a point on a 2D grid with x and y coordinates. This class provides methods to access the point's coordinates,
 * compare it with other points based on the x-coordinate, and determine if it is below and to the left of another point.
 */

public class GridPoint implements Comparable<GridPoint> {
   private double x; // x-coordinate
   private double y; // y-coordinate

   public GridPoint(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   public boolean belowAndLeft(GridPoint other) {
      return other.getX() < this.x && other.getY() > this.y;
   }

   @Override
   public int compareTo (GridPoint other) {
      return Double.compare(this.x, other.getX());
   }
}
