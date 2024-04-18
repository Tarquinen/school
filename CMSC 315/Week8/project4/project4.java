import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class project4 extends Application{
   private Button btAddEdge = new Button("Add Edge");
   private Label lbVertex1 = new Label("Vertex 1");
   private TextField tfVertex1 = new TextField(); 
   private Label lbVertex2 = new Label("Vertex 2");
   private TextField tfVertex2 = new TextField();
   private Button btIsConnected = new Button("Is Connected?");
   private Button btHasCycles = new Button("Has Cycles?");
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

      VBox footer = new VBox(buttonBox, tfResult);
      footer.setAlignment(Pos.CENTER);
      footer.setSpacing(10);
      footer.setPrefHeight(100);

      pane.setTop(header);
      pane.setCenter(graphPane);
      pane.setBottom(footer);

      btAddEdge.setOnAction(e -> {
         String tfU = tfVertex1.getText().toUpperCase();
         String tfV = tfVertex2.getText().toUpperCase();
         int u = stringValue(tfU);
         int v = stringValue(tfV);
         if (u == v) {
            tfResult.setText("Cannot add an edge to itself");
            return;
         }

         boolean foundVertex1 = false;
         boolean foundVertex2 = false;
         for (String name : graph.getNames()) {
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

         if (graph.addEdge(u, v)) {
            tfResult.clear();
            graphPane.drawEdge(u, v);
         }
         else {
            tfResult.setText("Edge already exists");
         }
         // graphPane.drawEdge(u, v);
      });











      Scene scene = new Scene(pane, 500, 500);
      primaryStage.setTitle("Project 4");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

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
