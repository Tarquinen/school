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
   private Map<String, Line> lineMap = new HashMap<>();

   public GraphView() {
      this.graph = new Graph();
      paneClicked();
   }
   
   public GraphView(Graph graph) {
      this.graph = graph;
      paneClicked();

   }
   
   private void repaintGraph() {
      // group.getChildren().clear();
   }

   private void paneClicked() {
      this.setOnMouseClicked(e -> {
         // if the left mouse button is clicked
         if (e.getButton() == MouseButton.PRIMARY) {
            double x = e.getX();
            double y = e.getY();
            // if the click is not inside an existing vertex, add a new vertex
            if (!isInsideCircle(new Point2D(x, y))) {
               Vertex vertex = new Vertex(x, y);
               graph.addVertex(vertex);
               Circle circle = new Circle(x, y, 5);
               Text text = new Text(x - 5, y - 10, vertex.getName());
               this.getChildren().add(circle);
               this.getChildren().add(text);
               text.toBack(); 
               circle.setOnMouseClicked(f -> {
                  // if the right mouse button is clicked on a vertex, remove the vertex and its edges
                  if (f.getButton() == MouseButton.SECONDARY) {
                     this.getChildren().remove(circle);
                     this.getChildren().remove(text);
                     eraseEdgesOfVertex(vertex); // erase all edges of the vertex from the pane
                     graph.removeVertex(vertex); // remove the vertex from the graph and its edges
                  }
               });
            }
         }
      });
   }

   public void eraseEdgesOfVertex(Vertex v) {
      int vertex = graph.getIndex(v);
      // get the neighbors of the vertex
      List<Integer> neighbors = graph.getNeighbors(vertex);
      for (Integer neighbor: neighbors) {
         // erase all edges between the vertex and its neighbors 
         eraseEdge(vertex, neighbor);
      }
   }

   public void eraseEdge(int u, int v) {
      // create a key for the line map
      String key = createKey(u, v);
      
      // if the key exists in the map, remove the line from the pane and the map
      if (lineMap.containsKey(key)) {
         this.getChildren().remove(lineMap.get(key));
         lineMap.remove(key);
      }
   }

   public boolean drawEdge(int u, int v) {
      // create a key for the line map
      String key = createKey(u, v);

      // if the line already exists in the map, don't add the line again 
      if (lineMap.containsKey(key)) return false;

      // get the vertices between which the line is drawn
      Vertex U = graph.getVertex(u);
      Vertex V = graph.getVertex(v);
      
      // draw the line and add it to the pane
      Line line = new Line(U.getX(), U.getY(), V.getX(), V.getY());
      this.getChildren().add(line);
      line.toBack();
      
      // add the line to the map
      lineMap.put(key, line);
      return true; // return true if the line was added
   }

   // creates a key for the edge map that will be consistent regardless of the order of u and v
   private String createKey(int u, int v) {
      int smaller = Math.min(u, v);
      int larger = Math.max(u, v);
      return smaller + " " + larger;
   }

   // removes all edges and redraws them (not used keep for reference)
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