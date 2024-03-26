// package CMSC315.Week3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;


public class GiftWrapping extends Application {
    static int[][] dotCoords;
    public void start(Stage primaryStage) {
        int dotCount = 20;
        dotCoords = new int[20][2]; // [index][0:x, 1:y]
        Pane pane = new Pane();
        for (int i = 0; i < dotCount; i++) {
            Circle dot = new Circle(5, Color.RED);
            int x = (int)(Math.random() * 400);
            int y = (int)(Math.random() * 400);
            dotCoords[i][0] = x;
            dotCoords[i][1] = y;

            dot.setCenterX(x);
            dot.setCenterY(y);
            pane.getChildren().add(dot);

            dot.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    System.out.println("Dot clicked at " + x + " " + y);
                }
                else {
                    System.out.println("Dot right clicked at " + x + " " + y);
                    pane.getChildren().remove(dot);
                    dotCoords[i][0] = -1;
                    // dotCoords[i][1] = -1;
                }
            });
        }

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        for (int i = 0; i < dotCoords.length; i++) {
            System.out.println(dotCoords[i][0] + " " + dotCoords[i][1]);
        }
    }
    
}
