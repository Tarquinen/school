import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseButton;
import java.util.ArrayList;

public class GiftWrapping extends Application {
    static ArrayList<Integer[]> dotCoords = new ArrayList<Integer[]>();
    static ArrayList<Integer[]> H = new ArrayList<Integer[]>();
    public void start(Stage primaryStage) {
        int initialDotCount = 5;
        Pane pane = new Pane();
        for (int i = 0; i < initialDotCount; i++) {
            Circle dot = new Circle(5, Color.RED);
            int x = (int)(Math.random() * 400);
            int y = (int)(Math.random() * 400);
            dotCoords.add(new Integer[]{x, y});

            dot.setCenterX(x);
            dot.setCenterY(y);
            pane.getChildren().add(dot);

            dot.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().remove(dot);
                    removeDot(x, y);
                }
            });
        }
        System.out.println(rightMostLowest()[0] + ", " + rightMostLowest()[1]);
        H.add(rightMostLowest());
    
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && !duplicateDot((int)e.getX(), (int)e.getY())){
                Circle dot = new Circle(5, Color.RED);
                int x = (int)e.getX();
                int y = (int)e.getY();

                System.out.println("Adding dot at " + x + " " + y);
                System.out.println("Array after addition");
                dotCoords.add(new Integer[]{x, y});

                //print array
                for (int j = 0; j < dotCoords.size(); j++) {
                    System.out.println(dotCoords.get(j)[0] + " " + dotCoords.get(j)[1]);
                }

                dot.setCenterX(x);
                dot.setCenterY(y);
                pane.getChildren().add(dot);

                dot.setOnMouseClicked(f -> {
                    if (f.getButton() == MouseButton.SECONDARY) {
                        pane.getChildren().remove(dot);
                        removeDot(x, y);
                    }
                });
            }
        });

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean duplicateDot(int x, int y) {
        for (int i = 0; i < dotCoords.size(); i++) {
            if (dotCoords.get(i)[0] == x && dotCoords.get(i)[1] == y) {
                return true;
            }
        }
        return false;
    }

    public static void removeDot(int x, int y) {
        for (int i = 0; i < dotCoords.size(); i++) {
            if (dotCoords.get(i)[0] == x && dotCoords.get(i)[1] == y) {
                dotCoords.remove(i);
                System.out.println("Removing dot at " + x + " " + y);
                //print array
                System.out.println("Array after removal");
                for (int j = 0; j < dotCoords.size(); j++) {
                    System.out.println(dotCoords.get(j)[0] + " " + dotCoords.get(j)[1]);
                }
            }
        }
    }
    public static void sortPoints() {

    }

    public static Integer[] rightMostLowest () {
        Integer[] lowest = dotCoords.get(0);
        for (Integer[] p : dotCoords) {
            if (p[1] > lowest[1]) {
                lowest = p;
            }
            else if (p[1] == lowest[1]) {
                if (p[0] > lowest[0]) lowest = p;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        launch(args);
    }
}