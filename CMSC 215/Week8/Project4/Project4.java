package Project4;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;

public class Project4 extends Application {
    // Create text fields and buttons and initizalize some values
    private Label lbStartTime = new Label("Start Time");
    private Label lbEndTime = new Label("End Time");
    private Label lbTimeInterval1 = new Label("Time Interval 1");
    private TextField tfStartTimeI1 = new TextField("10:30 AM");
    private TextField tfEndTimeI1 = new TextField("12:30 PM");
    private Label lbTimeInterval2 = new Label("Time Interval 2");
    private TextField tfStartTimeI2 = new TextField("11:05 AM");
    private TextField tfEndTimeI2 = new TextField("01:00 PM");
    private Button btCompareIntervals = new Button("Compare Intervals");
    private Label lbTimeToCheck = new Label("Time to Check");
    private TextField tfTimeToCheck = new TextField();
    private Button btCheckTime = new Button("Check Time");
    private TextField tfResult = new TextField();
    private Time i1StartTime;
    private Time i1EndTime;
    private Time i2StartTime;
    private Time i2EndTime;
    private Time timeToCheck;
    private Interval<Time> interval1;
    private Interval<Time> interval2;


    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Place nodes
        gridPane.add(lbStartTime, 1, 0);
        gridPane.add(lbEndTime, 2, 0);
        gridPane.add(lbTimeInterval1, 0, 1);
        gridPane.add(tfStartTimeI1, 1, 1);
        gridPane.add(tfEndTimeI1, 2, 1);
        gridPane.add(lbTimeInterval2, 0, 2);
        gridPane.add(tfStartTimeI2, 1, 2);
        gridPane.add(tfEndTimeI2, 2, 2);
        gridPane.add(btCompareIntervals, 0, 3);
        gridPane.add(lbTimeToCheck, 0, 4);
        gridPane.add(tfTimeToCheck, 1, 4);
        gridPane.add(btCheckTime, 0, 5);
        gridPane.add(tfResult, 0, 6);

        // Set properties for the UI
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(lbStartTime, HPos.CENTER);
        GridPane.setHalignment(lbEndTime, HPos.CENTER);
        GridPane.setColumnSpan(btCompareIntervals, 3);
        btCompareIntervals.setPrefWidth(400);
        GridPane.setColumnSpan(tfTimeToCheck, 2);
        tfTimeToCheck.setPrefWidth(400);
        GridPane.setColumnSpan(btCheckTime, 3);
        btCheckTime.setPrefWidth(400);
        GridPane.setColumnSpan(tfResult, 3);
        tfResult.setPrefWidth(400);
        tfResult.setEditable(false);

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                ((TextField)node).setAlignment(Pos.CENTER);
            }
        }

        // process events
        btCompareIntervals.setOnAction(e -> {
            try {
                compareIntervals();
            } catch (InvalidTime ex) {
                tfResult.setText(ex.getMessage());
            }    
        });    
        btCheckTime.setOnAction(e -> {;
            try {
                checkTime();
            } catch (InvalidTime ex) {
                tfResult.setText(ex.getMessage());
            }    
        });    

        // create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 410, 210);
        gridPane.requestFocus();
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
    // create time and interval objects, triggered by button clicks 
    private void createObjects() throws InvalidTime {
        i1StartTime = new Time(tfStartTimeI1.getText());
        i1EndTime = new Time(tfEndTimeI1.getText());
        i2StartTime = new Time(tfStartTimeI2.getText());
        i2EndTime = new Time(tfEndTimeI2.getText());
        interval1 = new Interval<>(i1StartTime, i1EndTime);
        interval2 = new Interval<>(i2StartTime, i2EndTime);
    }

    private void compareIntervals() throws InvalidTime {
        createObjects();
        if (interval2.subinterval(interval1)) {
            tfResult.setText("Interval 1 is a sub-interval of interval 2");
        }
        else if (interval1.subinterval(interval2)) {
            tfResult.setText("Interval 2 is a sub-interval of interval 1");
        }
        else if (interval1.overlaps(interval2)) {
            tfResult.setText("The intervals overlap");
        }
        else {
            tfResult.setText("the intervals are disjoint");
        }
    }

    private void checkTime() throws InvalidTime {
        createObjects();
        timeToCheck = new Time(tfTimeToCheck.getText());
        if (interval1.within(timeToCheck) && interval2.within(timeToCheck)) {
            tfResult.setText("Both intervals contain the time " + timeToCheck);
        }
        else if (interval1.within(timeToCheck)) {
            tfResult.setText("Only interval 1 contains the time " + timeToCheck);
        }
        else if (interval2.within(timeToCheck)) {
            tfResult.setText("Only interval 2 contains the time " + timeToCheck); 
        }
        else {
            tfResult.setText("Neither interval contains the time " + timeToCheck);
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
