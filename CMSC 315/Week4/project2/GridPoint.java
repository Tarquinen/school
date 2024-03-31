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
