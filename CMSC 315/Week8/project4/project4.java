/**
 * Daniel Smolsky
 * Programming Project 4 - Graphs
 * April 21, 2024
 * The project4 class is the main entry point for the Graph Visualization application.
 * It sets up the JavaFX user interface, including the header with controls for adding edges,
 * checking connectivity and cycles, and performing depth-first and breadth-first searches.
 * The main graph visualization pane is also created and added to the BorderPane layout.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class project4 extends Application {
   private Button btAddEdge = new Button("Add Edge");
   private Label lbVertex1 = new Label("Vertex 1");
   private TextField tfVertex1 = new TextField(); 
   private Label lbVertex2 = new Label("Vertex 2");
   private TextField tfVertex2 = new TextField();
   private Button btIsConnected = new Button("Is Connected?");
   private Button btHasCycles = new Button("Has Cycles?");
   private Button btPreviousCycle = new Button("<");
   private Button btNextCycle = new Button(">");
   private Button btDepthFirstSearch = new Button("Depth First Search");
   private Button btBreadthFirstSearch = new Button("Breadth First Search");
   private TextField tfResult = new TextField();
   private Graph graph = new Graph();

   public void start(Stage primaryStage) {
      BorderPane pane = new BorderPane(); 

      // header elements and properties
      HBox header = new HBox(btAddEdge, lbVertex1, tfVertex1, lbVertex2, tfVertex2);
      header.setSpacing(10);
      header.setAlignment(Pos.CENTER);
      header.setPrefHeight(50);
      tfVertex1.setPrefWidth(30);
      tfVertex2.setPrefWidth(30);

      GraphView graphPane = new GraphView(graph);

      //footer elements and properties
      HBox buttonBox = new HBox(btIsConnected, btHasCycles, btDepthFirstSearch, btBreadthFirstSearch);
      buttonBox.setSpacing(10);
      buttonBox.setAlignment(Pos.CENTER);
      tfResult.setEditable(false);
      tfResult.setAlignment(Pos.CENTER);
      tfResult.setMaxWidth(435);
      tfResult.setAlignment(Pos.CENTER_LEFT);
      HBox cycleBox = new HBox(btPreviousCycle, btNextCycle);
      cycleBox.setAlignment(Pos.CENTER);
      cycleBox.setSpacing(10);

      VBox footer = new VBox(cycleBox, buttonBox, tfResult);
      VBox.setMargin(cycleBox, new Insets(0, 0, 0, -155));
      footer.setAlignment(Pos.CENTER);
      footer.setSpacing(10);
      footer.setPrefHeight(120);

      pane.setTop(header);
      pane.setCenter(graphPane);
      pane.setBottom(footer);

      btAddEdge.setOnAction(e -> {
         // get the vertex names from the text fields
         String tfU = tfVertex1.getText().toUpperCase();
         String tfV = tfVertex2.getText().toUpperCase();
         
         if (tfU.equals(tfV)) {
            tfResult.setText("Cannot add an edge to itself");
            return;
         }
         
         // check if the vertices exist in the graph
         boolean foundVertex1 = false;
         boolean foundVertex2 = false;
         for (String name : graph.getVertexNames()) {
            if (tfU.equals(name)) foundVertex1 = true;
            if (tfV.equals(name)) foundVertex2 = true;
            if (foundVertex1 && foundVertex2) break;
         }
         
         if (!foundVertex1 && !foundVertex2) {
            tfResult.setText("Neither vertex found in the graph");
            return;
         }
         else if (!foundVertex1) {
            tfResult.setText("Vertex 1 not found in the graph");
            return;
         }
         else if (!foundVertex2) {
            tfResult.setText("Vertex 2 not found in the graph");
            return;
         }

         // convert vertex names to integers (A=0, B=1, ..., Z=25, AA=26, AB=27, ...)
         int u = stringValue(tfU);
         int v = stringValue(tfV);
         
         // add the edge to the graph and draw it on the pane
         if (graph.addEdge(u, v)) {
            graphPane.eraseDirectionalLines();
            tfResult.clear();
            graphPane.drawEdge(u, v);
         }
         else {
            tfResult.setText("Edge already exists");
         }
      });
      
      // check if the graph is connected
      btIsConnected.setOnAction(e -> {
         if (graph.getCurrentSize() < 2) {
            tfResult.setText("Graph must have at least 2 vertices");
            return;
         }
         graphPane.eraseDirectionalLines();

         if (graph.isConnected()) {
            tfResult.setText("Graph is connected");
         }
         else {
            tfResult.setText("Graph is not connected");
         }
      });
      
      // check if the graph has cycles
      btHasCycles.setOnAction(e -> {
         if (graph.getCurrentSize() < 3) {
            tfResult.setText("Graph must have at least 3 vertices");
            return;
         }

         if (!graph.hasCycles()) {
            tfResult.setText("Graph does not have cycles");
            return;
         }
         else {
            // create the list of cycles in the graph
            graph.findCyclesDFS();

            // get the number of cycles in the graph
            final int cycleCount = graph.getCycleCount();

            // index of the current cycle being displayed, has to be final to be used in lambda and array to be modifiable
            final int[] currentCycleIndex = {0};

            // display the cycle count
            if (cycleCount == 1) tfResult.setText("Graph has 1 cycle");
            else tfResult.setText("Graph has " + cycleCount + " cycles");

            // display the first cycle
            graphPane.displayCycle();

            /**
             * cycle navigation buttons 
             * NOTE: these buttons will not recalculate the cycles, only display the next or previous cycle
             * in the list of cycles. To recalculate the cycles, the user must click the "Has Cycles?" button.
             * This is because recalculating the cycles is an expensive operation. This is easily changed by 
             * calling graph.findCyclesDFS() in the lambda functions below and updating the cycleCount.
            */
            btNextCycle.setOnAction(f -> {
               if (graphPane.displayCycle(++currentCycleIndex[0])) // returns true and displays cycle if next cycle exists
                  tfResult.setText("Cycle " + (currentCycleIndex[0] + 1) + " of " + cycleCount);
               else
                  currentCycleIndex[0]--;
            });

            btPreviousCycle.setOnAction(f -> {
               if (graphPane.displayCycle(--currentCycleIndex[0])) // returns true and displays cycle if previous cycle exists
                  tfResult.setText("Cycle " + (currentCycleIndex[0] + 1) + " of " + cycleCount);
               else 
                  currentCycleIndex[0]++;
            });
         }
      });
      
      // depth-first search
      btDepthFirstSearch.setOnAction(e -> {
         if (graph.getCurrentSize() < 2) {
            tfResult.setText("Graph must have at least 2 vertices");
            return;
         }
         graphPane.displayDfs();
         tfResult.setText(graph.getDfsString());
      });
      
      // breadth-first search
      btBreadthFirstSearch.setOnAction(e -> {
         if (graph.getCurrentSize() < 2) {
            tfResult.setText("Graph must have at least 2 vertices");
            return;
         }
         graphPane.displayBfs();
         tfResult.setText(graph.getBfsString());
      });

      Scene scene = new Scene(pane, 600, 500);
      primaryStage.setTitle("Project 4");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   // convert vertex names to integers (A=0, B=1, ..., Z=25, AA=26, AB=27, ...)
   public static int stringValue(String s) {
      if (s.length() == 1) {
         return s.charAt(0) - 'A';
      }
      else {
         return (s.charAt(0) - 'A' + 1) * 26 + s.charAt(1) - 'A';
      }
   }

   public static void main(String[] args) {
      launch(args);
   }
}
