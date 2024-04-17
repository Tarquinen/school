import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import java.util.List;
import java.util.ArrayList;

public class GraphView extends Pane {
   private Graph graph;

   public GraphView() {
      paneClicked();
      this.graph = new Graph();
   }
   
   public GraphView(Graph graph) {
      this();
      this.graph = graph;

   }
   
   private void repaintGraph() {
      // group.getChildren().clear();
   }

   private void paneClicked() {
      this.setOnMouseClicked(e -> {
         if (e.getButton() == MouseButton.PRIMARY) {
            double x = e.getX();
            double y = e.getY();
            if (!isInsideCircle(new Point2D(x, y))) {
               Vertex vertex = new Vertex(x, y);
               graph.addVertex(vertex);
               Circle circle = new Circle(x, y, 5);
               Text text = new Text(x - 5, y - 10, vertex.getName());
               this.getChildren().add(circle);
               this.getChildren().add(text);
               text.toBack();
               circle.setOnMouseClicked(f -> {
                  if (f.getButton() == MouseButton.SECONDARY) {
                     this.getChildren().remove(circle);
                     this.getChildren().remove(text);
                     graph.removeVertex(vertex);
                     redrawEdges();
                  }
               });
            }
         }
      });
   }

   public void drawEdge(int u, int v) {
      Vertex U = graph.getVertex(u);
      Vertex V = graph.getVertex(v);
      Line line = new Line(U.getX(), U.getY(), V.getX(), V.getY());
      this.getChildren().add(line);
      line.toBack();
   }
   
   private void redrawEdges() {
      List<Node> toRemove = new ArrayList<>();
      for (Node line: this.getChildren()) {
         if (line instanceof Line) {
            toRemove.add(line);
         }
      }
      this.getChildren().removeAll(toRemove);
      for (int i = 0; i < graph.getVertices().size(); i++) {
         for (Integer neighbor: graph.getNeighbors(i)) {
            drawEdge(i, neighbor);
         }
      }
   }

   private boolean isInsideCircle(Point2D p) {
      for (Node circle: this.getChildren()) {
         if (circle.contains(p)) {
            System.out.println("existing circle clicked");
            return true;
         }
      }
      return false;
   }
}
