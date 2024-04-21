import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class DirectionalLine extends Path {
   private static final double DEFAULT_SIZE = 8.0;
   private static final int STROKE_WIDTH = 3;
   private static final Color DEFAULT_COLOR = Color.RED;

   public DirectionalLine(double startX, double startY, double endX, double endY,
                        double arrowHeadSize, double strokeWidth, Color color) {
      super();
      strokeProperty().bind(fillProperty());
      setFill(color);
      setStrokeWidth(strokeWidth);

      // amount of arrow heads
      double length = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
      int numArrowHeads = length < 40 ? 0 : (length < 150 ? 1 : (int) length / 150);

      // Line
      getElements().add(new MoveTo(startX, startY));
      getElements().add(new LineTo(endX, endY));

      for (int i = 0; i < numArrowHeads; i++) {
         // Calculate arrow head coordinates
         double ratio = (double) (i + 1) / (numArrowHeads + 1);
         double x = startX + (endX - startX) * ratio;
         double y = startY + (endY - startY) * ratio;

         // ArrowHead
         double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
         double sin = Math.sin(angle);
         double cos = Math.cos(angle);

         // point1
         double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + x;
         double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + y;
         // point2
         double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + x;
         double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + y;

         getElements().add(new MoveTo(x, y));
         getElements().add(new LineTo(x1, y1));
         getElements().add(new LineTo(x2, y2));
         getElements().add(new LineTo(x, y));
      }
   }

   public DirectionalLine(double startX, double startY, double endX, double endY, Color color) {
      this(startX, startY, endX, endY, DEFAULT_SIZE, STROKE_WIDTH, color);
   }

   public DirectionalLine(double startX, double startY, double endX, double endY) {
      this(startX, startY, endX, endY, DEFAULT_SIZE, STROKE_WIDTH, DEFAULT_COLOR);
   }

}