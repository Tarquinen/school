import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GraphView extends Pane {
   private Graph graph;
   private Map<String, Line> edgeLines = new HashMap<>();

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
                     eraseEdgesOfVertex(vertex);
                     graph.removeVertex(vertex);
                     // redrawEdges();
                  }
               });
            }
         }
      });
   }

   public void eraseEdgesOfVertex(Vertex v) {
      int index = graph.getIndex(v);
      List<Integer> neighbors = graph.getNeighbors(index);
      for (Integer neighbor: neighbors) {
         eraseEdge(index, (int)neighbor);
      }
   }

   public void drawEdge(int u, int v) {
      Vertex U = graph.getVertex(u);
      Vertex V = graph.getVertex(v);
      Line line = new Line(U.getX(), U.getY(), V.getX(), V.getY());
      this.getChildren().add(line);
      line.toBack();
      edgeLines.put(new String(u + " " + v), line);
      // print the map
      for (Map.Entry<String, Line> entry: edgeLines.entrySet()) {
         System.out.println(entry.getKey() + " " + entry.getValue());
      }
   }








   public void eraseEdge(int u, int v) {
      String key1 = new String(u + " " + v);
      String key2 = new String(v + " " + u);
      //removing line with key
      System.out.println("removing line with key: " + key1);
      Line line = edgeLines.get(key1);
      if (line != null) {
         System.out.println("removing line");
         this.getChildren().remove(line);
         edgeLines.remove(key1);
      }
      else {
         line = edgeLines.get(key2);
         if (line != null) {
            System.out.println("removing line");
            this.getChildren().remove(line);
            edgeLines.remove(key2);
         }
      }
   }















   // public void drawEdge(int u, int v) {
   //    Vertex U = graph.getVertex(u);
   //    Vertex V = graph.getVertex(v);
   //    Line line = new Line(U.getX(), U.getY(), V.getX(), V.getY());
   //    this.getChildren().add(line);
   //    line.toBack();
   // }
   
   
   // // removes the edge between vertice index u and v
   // public void eraseEdge(int u, int v) {
   //    double uX = graph.getVertices().get(u).getX();
   //    double uY = graph.getVertices().get(u).getY();
   //    double vX = graph.getVertices().get(v).getX();
   //    double vY = graph.getVertices().get(v).getY();
   
   //    List<Node> toRemove = new ArrayList<>();
   //    for (Node line: this.getChildren()) {
   //       if (line instanceof Line) {
   //          Line l = (Line)line;
   //          if (l.getStartX() == uX && l.getStartY() == uY && l.getEndX() == vX && l.getEndY() == vY ||
   //             l.getStartX() == vX && l.getStartY() == vY && l.getEndX() == uX && l.getEndY() == uY ) {
   //             toRemove.add(line);
   //             break;
   //          }
   //       }
   //    }
   //    this.getChildren().removeAll(toRemove);
   // }

   // removes all edges and redraws them
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
