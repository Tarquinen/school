import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.input.MouseButton;
import java.util.ArrayList;

public class GiftWrapping extends Application {
    static ArrayList<Integer[]> dotCoords = new ArrayList<Integer[]>(); // store all points
    static ArrayList<Integer[]> H = new ArrayList<Integer[]>(); // store perimeter points
    static Pane pane = new Pane();
    static final int INITIAL_DOT_COUNT = 20;

    public void start(Stage primaryStage) {
        // create scene
        Scene scene = new Scene(pane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //add initial dots
        for (int i = 0; i < INITIAL_DOT_COUNT; i++) {
            int x = (int)(Math.random() * 800);
            int y = (int)(Math.random() * 400);
            
            // only add dot if it does not overlap with another dot
            if (isDuplicateDot(x, y)) {
                i--;
                continue;
            }
            placeDot(x, y, Color.BLACK);
        }
        
        giftWrap(); // wrap the initial dots
        
        // add a new dot on left click
        pane.setOnMouseClicked(e -> {
            // coordinates of click
            int x = (int)e.getX();
            int y = (int)e.getY();
            
            if (e.getButton() == MouseButton.PRIMARY && !isDuplicateDot(x, y)) {
                placeDot(x, y, Color.BLACK);
                giftWrap();
            }
        });
    }
    
    // wrap the dots with a gift wrapping algorithm
    public static void giftWrap() {
        // remove all lines on pane from previous gift wrapping
        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i) instanceof Line) {
                pane.getChildren().remove(i);
                i--;
            }
        }
        
        if (dotCoords.size() < 3) return; // not enough points to wrap (need at least 3 points)

        // reset list of perimeter points
        H.clear();
        H.add(rightMostLowest());

        // find the perimeter points
        while (true) {
            Integer[] HLast = H.get(H.size() - 1);
            Integer[] t0 = dotCoords.get(0);
            if (HLast.equals(t0)) {
                t0 = dotCoords.get(1);
            }
            for (Integer[] t1 : dotCoords) {
                if (t1.equals(HLast) || t1.equals(t0))
                    continue;
                if (rightOfLine(HLast, t0, t1)) { // t1 is right of line HLast > t0
                    t0 = t1;
                }
            }
            lineBetweenDots(HLast, t0, Color.RED);

            // check if the perimeter is complete by checking if the last point is the starting point
            if (t0.equals(H.get(0)))
                break;

            H.add(t0);
        }
    }

    // draw a line between two points
    public static void lineBetweenDots(Integer[] t0, Integer[] t1, Color color) {
        Line line = new Line();
        line.setStartX(t0[0]);
        line.setStartY(t0[1]); 
        line.setEndX(t1[0]);
        line.setEndY(t1[1]);
        line.setStroke(color);
        pane.getChildren().add(line);
        line.toBack(); // move line to back so dots are easier to click
    }

    // place a dot on the pane
    public static void placeDot(int x, int y, Color color) {
        dotCoords.add(new Integer[]{x, y});
        Circle dot = new Circle(5, color);
        dot.setCenterX(x);
        dot.setCenterY(y);
        pane.getChildren().add(dot);
        dot.setOnMouseClicked(f -> { // remove dot on right click
            if (f.getButton() == MouseButton.SECONDARY) {
                pane.getChildren().remove(dot);
                removeDot(x, y);
                giftWrap();
            }
        });
    }

    // determine if point p is right of line t0 > t1
    public static boolean rightOfLine(Integer[] t0, Integer[] t1, Integer[] p) {
        double tSlope; // slop of line t0 > t1
        double tYIntercept; //y intercept of line t0 > t1
        double perpendicularSlope; // slope of line perpendicular to line t0 > t1
        double pYIntercept; // y intercept of point p with slope perpendicularSlope
        double ptIntersectXCoord; // x coordinate of the two line intersections

        // line t0 > t1 is vertical
        if (t0[0].equals(t1[0])) {
            if (t0[1] > t1[1]) { // t0 is below t1 (y increases downwards)
                return p[0] > t0[0]; 
            } else {
                return p[0] < t0[0];
            }
        }

        //line t0 > t1 is horizontal
        else if (t0[1].equals(t1[1])) {
            if (t0[0] < t1[0]) { // t0 is to the left of t1 (x increases to the right)
                return p[1] > t0[1]; 
            } else {
                return p[1] < t0[1];
            }
        }

        else {
            tSlope =  (double)(t1[1] - t0[1]) / (t1[0] - t0[0]);
            tYIntercept = t0[1] - tSlope * t0[0];
            perpendicularSlope = -1/tSlope;
            pYIntercept = p[1] - perpendicularSlope * p[0];
            ptIntersectXCoord = (pYIntercept - tYIntercept) / (tSlope - perpendicularSlope);
        }

        // evalute if p is right of line t0 > t1
        if (p[0] > ptIntersectXCoord) {
            return t0[1] > t1[1];
        }
        else {
            return t0[1] < t1[1];
        }
    }
    
    // find the rightmost lowest point which will be the starting point of the perimeter
    public static Integer[] rightMostLowest () {
        Integer[] lowest = dotCoords.get(0);
        for (Integer[] p : dotCoords) {
            if (p[1] > lowest[1]) 
                lowest = p;
            else if (p[1] == lowest[1]) {
                if (p[0] > lowest[0]) 
                    lowest = p;
            }
        }
        return lowest;
    }

    // check if a dot already exists at the given coordinates
    public static boolean isDuplicateDot(int x, int y) {
        for (int i = 0; i < dotCoords.size(); i++) {
            if (Math.abs(dotCoords.get(i)[0] - x) <= 8 && Math.abs(dotCoords.get(i)[1] - y) <= 8) {
                return true;
            }
        }
        return false;
    }

    // remove a dot from the list of dots
    public static void removeDot(int x, int y) {
        for (int i = 0; i < dotCoords.size(); i++) {
            if (dotCoords.get(i)[0] == x && dotCoords.get(i)[1] == y) {
                dotCoords.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}