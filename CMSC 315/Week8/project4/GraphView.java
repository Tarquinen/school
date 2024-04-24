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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GraphView extends Pane {
   private Graph graph;
   private Map<String, Line> lineMap = new HashMap<>();
   private int vIndex = -1;
   private int uIndex = -1;
   private Line draggingLine = new Line();
   private List<DirectionalLine> directionalLines = new ArrayList<>();

   public GraphView() {
      this.graph = new Graph();
      initializeMouseEventHandlers();
   }
   
   public GraphView(Graph graph) {
      this.graph = graph;
      initializeMouseEventHandlers();
   }

   private void initializeMouseEventHandlers() {
      // if the mouse is pressed, check if the click is on a vertex
      this.setOnMousePressed(e -> {
         vIndex = getVertexIndex(new Point2D(e.getX(), e.getY())); // returns an index if clicking a vertex, -1 otherwise
         if (vIndex != -1 && e.getButton() == MouseButton.PRIMARY) {
            draggingLine.setStartX(graph.getVertex(vIndex).getX());
            draggingLine.setStartY(graph.getVertex(vIndex).getY());
            draggingLine.setEndX(e.getX());
            draggingLine.setEndY(e.getY());
            this.getChildren().add(draggingLine);
         }
      });

      // if the mouse is released, check if the click starts and ends on a vertex
      this.setOnMouseReleased(e -> {
         uIndex = getVertexIndex(new Point2D(e.getX(), e.getY())); // reprents the vertex where the mouse is released, -1 if not on a vertex
         // if the click starts and ends on a vertex, add an edge between the two vertices
         if (vIndex != -1 && uIndex != -1 && e.getButton() == MouseButton.PRIMARY) {
            graph.addEdge(vIndex, uIndex);
            drawEdge(vIndex, uIndex);
            this.getChildren().remove(draggingLine);
            eraseDirectionalLines();
         }
         // if the click doesn't start on a vertex and doesn't end on a vertex, add a new vertex
         else if (vIndex == -1 && uIndex == -1 && e.getButton() == MouseButton.PRIMARY) {
            this.getChildren().remove(draggingLine);
            addVertex(e.getX(), e.getY());
         }
         // if the click starts on a vertex and ends on a blank space, remove the dragging line
         else {
            this.getChildren().remove(draggingLine);
         }
      });

      // if the mouse is dragged from a vertex, update the end of the dragging line
      this.setOnMouseDragged(e -> {
         if (vIndex != -1 && e.getButton() == MouseButton.PRIMARY) {
            draggingLine.setEndX(e.getX());
            draggingLine.setEndY(e.getY());
         }
      });
   }

   // adds a vertex to the pane and the graph
   private void addVertex(double x, double y) {
      // create a new vertex and add it to the graph
      Vertex vertex = new Vertex(x, y);
      graph.addVertex(vertex);

      // draw the vertex on the pane
      Circle circle = new Circle(x, y, 6);
      this.getChildren().add(circle);

      // add the vertex name to the pane above the vertex
      Text text = new Text(x - 5, y - 14, vertex.getName());
      this.getChildren().add(text);
      
      // make the text visible when it overlaps with lines
      text.setFill(Color.RED);
      text.setFont(Font.font("System", FontWeight.BOLD, 14));

      // if the right mouse button is clicked on a vertex, remove the vertex and its edges
      circle.setOnMouseClicked(f -> {
         if (f.getButton() == MouseButton.SECONDARY) {
            this.getChildren().remove(circle);
            this.getChildren().remove(text);
            eraseEdgesOfVertex(vertex); // erase all edges of the vertex from the pane
            graph.removeVertex(vertex); // remove the vertex from the graph and its edges
            eraseDirectionalLines();
         }
      });
   }

   // erases all edges of a vertex from the pane
   private void eraseEdgesOfVertex(Vertex v) {
      int vertex = graph.getIndex(v);

      // get the neighbors of the vertex
      List<Integer> neighbors = graph.getNeighbors(vertex);
      for (Integer neighbor: neighbors) 
         // erase all edges between the vertex and its neighbors 
         eraseEdge(vertex, neighbor);
   }

   // erases an edge from the pane NOTE: this does not remove the edge from the graph
   private void eraseEdge(int u, int v) {
      // create a key for the line map
      String key = createKey(u, v);
      
      // if the key exists in the map, remove the line from the pane and the map
      if (lineMap.containsKey(key)) {
         this.getChildren().remove(lineMap.get(key));
         lineMap.remove(key);
      }
   }

   // draws an edge between two vertices indexed by u and v
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
      line.setStrokeWidth(4);
      this.getChildren().add(line);
      line.toBack();

      // add the line to the map
      lineMap.put(key, line);

      // if the right mouse button is clicked on a line, remove the line
      line.setOnMouseClicked(e -> {
         if (e.getButton() == MouseButton.SECONDARY) {
            graph.removeEdge(u, v);
            eraseEdge(u, v);
         }
      });
      
      return true; // return true if the line was added
   }

   // creates a key for the line map that will be consistent regardless of the order of u and v
   private String createKey(int u, int v) {
      int smaller = Math.min(u, v);
      int larger = Math.max(u, v);
      return smaller + " " + larger;
   }   

   // draws a directional line from vertex index u to vertex index v
   public void drawDirectionalLine(int u, int v) {
      Vertex U = graph.getVertex(u);
      Vertex V = graph.getVertex(v);

      DirectionalLine line = new DirectionalLine(U.getX(), U.getY(), V.getX(), V.getY());
      directionalLines.add(line);
      this.getChildren().add(line);
      line.toBack();
      eraseEdge(u, v);
   } 

   // erases all directional lines from the pane
   public void eraseDirectionalLines() {
      for (DirectionalLine line: directionalLines) {
         this.getChildren().remove(line);
      }
      redrawEdges();
   }
   
   // displays the first cycle found in the graph
   public void displayCycle() {
      if (graph.hasCycles()) displayCycle(0);
   }

   // displays the cycle at the specified index
   public boolean displayCycle(int index) {
      List<Integer> cycle = graph.getCycle(index);
      if (cycle == null) return false;

      eraseDirectionalLines();
      for (int i = 0; i < cycle.size() - 1; i++) {
         int u = cycle.get(i);
         int v = cycle.get((i + 1));
         drawDirectionalLine(u, v);
      }
      // draw the return line from the last vertex to the first vertex
      drawDirectionalLine(cycle.get(cycle.size() - 1), cycle.get(0));
      return true;
   }

   // display the DFS traversal of the graph
   public void displayDfs() {
      eraseDirectionalLines();
      int[] traversal = graph.dfs();
      for (int i = 0; i < traversal.length; i++) {
         if (traversal[i] != -1) // if the vertex does not have a parent it will be -1
            // draw the directional line from the parent to the current vertex
            drawDirectionalLine(traversal[i], i);
      }
   }

   // display the BFS traversal of the graph
   public void displayBfs() {
      eraseDirectionalLines();
      int[] traversal = graph.bfs();
      for (int i = 0; i < traversal.length; i++) {
         if (traversal[i] != -1) // if the vertex does not have a parent it will be -1
            // draw the directional line from the parent to the current vertex
            drawDirectionalLine(traversal[i], i);
      }
   }

   // redraws all edges currently missing from the pane
   private void redrawEdges() {
      for (int i = 0; i < graph.getVertices().size(); i++) {
         for (Integer neighbor: graph.getNeighbors(i)) {
            drawEdge(i, neighbor);
         }
      }
   }

   // returns the index of the vertex at the specified point p
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