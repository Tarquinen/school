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
   private int vertex1 = -1;
   private int vertex2 = -1;
   Line draggingLine = new Line(); 

   public GraphView() {
      this.graph = new Graph();
      initializeMouseEventHandlers();
   }
   
   public GraphView(Graph graph) {
      this.graph = graph;
      initializeMouseEventHandlers();
   }

   private void initializeMouseEventHandlers() {
      this.setOnMousePressed(e -> {
         vertex1 = getVertexIndex(new Point2D(e.getX(), e.getY())); // returns an index if clicking a vertex, -1 otherwise
         if (vertex1 != -1 && e.getButton() == MouseButton.PRIMARY) {
            draggingLine.setStartX(graph.getVertex(vertex1).getX());
            draggingLine.setStartY(graph.getVertex(vertex1).getY());
            draggingLine.setEndX(e.getX());
            draggingLine.setEndY(e.getY());
            this.getChildren().add(draggingLine);
         }
      });

      this.setOnMouseReleased(e -> {
         vertex2 = getVertexIndex(new Point2D(e.getX(), e.getY())); // reprents the vertex where the mouse is released, -1 if not on a vertex
         // if the click starts and ends on a vertex, add an edge between the two vertices
         if (vertex1 != -1 && vertex2 != -1 && e.getButton() == MouseButton.PRIMARY) {
            graph.addEdge(vertex1, vertex2);
            drawEdge(vertex1, vertex2);
            this.getChildren().remove(draggingLine);
         }
         // if the click doesn't start on a vertex and doesn't end on a vertex, add a new vertex
         else if (vertex1 == -1 && vertex2 == -1 && e.getButton() == MouseButton.PRIMARY) {
            this.getChildren().remove(draggingLine);
            addVertex(e.getX(), e.getY());
         }
         // if the click starts on a vertex and ends on a blank space, remove the dragging line
         else {
            this.getChildren().remove(draggingLine);
         }
      });

      this.setOnMouseDragged(e -> {
         if (vertex1 != -1 && e.getButton() == MouseButton.PRIMARY) {
            draggingLine.setEndX(e.getX());
            draggingLine.setEndY(e.getY());
         }
      });
   }

   // adds a vertex to the pane and the graph
   private void addVertex(double x, double y) {
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

   // erases all edges of a vertex from the pane
   public void eraseEdgesOfVertex(Vertex v) {
      int vertex = graph.getIndex(v);
      // get the neighbors of the vertex
      List<Integer> neighbors = graph.getNeighbors(vertex);
      for (Integer neighbor: neighbors) {
         // erase all edges between the vertex and its neighbors 
         eraseEdge(vertex, neighbor);
      }
   }

   // erases an edge from the pane
   public void eraseEdge(int u, int v) {
      // create a key for the line map
      String key = createKey(u, v);
      
      // if the key exists in the map, remove the line from the pane and the map
      if (lineMap.containsKey(key)) {
         this.getChildren().remove(lineMap.get(key));
         lineMap.remove(key);
      }
   }

   // draws an edge between two vertices
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
      line.setStrokeWidth(3);
      this.getChildren().add(line);
      line.toBack();

      // if the right mouse button is clicked on a line, remove the line
      line.setOnMouseClicked(e -> {
         if (e.getButton() == MouseButton.SECONDARY) {
            this.getChildren().remove(line);
            lineMap.remove(key);
            graph.removeEdge(u, v);
         }
      });
      
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

   private int getVertexIndex(Point2D p) {
      for (Node circle: this.getChildren()) {
         if (circle instanceof Circle && circle.contains(p)) {
            double x = ((Circle)circle).getCenterX();
            double y = ((Circle)circle).getCenterY();
            int index = graph.getIndex(x, y);
            return index;
         }
      }
      return -1;
   }
}