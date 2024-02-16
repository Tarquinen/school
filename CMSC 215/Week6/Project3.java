import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class Project3 extends Application {
    
    private TextField tfDistance = new TextField("1000");
    private TextField tfGasCost = new TextField("3.95");
    private TextField tfGasMileage = new TextField("31");
    private TextField tfNumDays = new TextField("2");
    private TextField tfHotelCost = new TextField("150");
    private TextField tfFoodCost = new TextField("125");
    private TextField tfAttractions = new TextField("78");
    private TextField tfTotalTripCost = new TextField();
    private Button btCalculate = new Button("Calculate");
    private ComboBox<String> cbDistanceMeasurement = new ComboBox<>();
    private ComboBox<String> cbGasCostPerVol = new ComboBox<>();
    private ComboBox<String> cbDistancePerVol = new ComboBox<>();

    public void start(Stage primaryStage) {   
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Place nodes
        gridPane.add(new Label("Distance:"), 0, 0);
        gridPane.add(tfDistance, 1, 0);
        gridPane.add(cbDistanceMeasurement, 2, 0);
        gridPane.add(new Label("Gasoline Cost:"), 0, 1);
        gridPane.add(tfGasCost, 1, 1);
        gridPane.add(cbGasCostPerVol, 2, 1);
        gridPane.add(new Label("Gas Mileage:"), 0, 2);
        gridPane.add(tfGasMileage, 1, 2);
        gridPane.add(cbDistancePerVol, 2, 2);
        gridPane.add(new Label("Number Of Days:"), 0, 3);
        gridPane.add(tfNumDays, 1, 3);
        gridPane.add(new Label("Hotel Cost:"), 0, 4);
        gridPane.add(tfHotelCost, 1, 4);
        gridPane.add(new Label("Food Cost:"), 0, 5);
        gridPane.add(tfFoodCost, 1, 5);
        gridPane.add(new Label("Attractions:"), 0, 6);
        gridPane.add(tfAttractions, 1, 6);
        gridPane.add(btCalculate, 1, 7);
        gridPane.add(new Label("Total Trip Cost"), 0, 8);
        gridPane.add(tfTotalTripCost, 1, 8);

        // Set combo boxes
        cbDistanceMeasurement.getItems().addAll("Miles", "Kilometers");
        cbDistanceMeasurement.getSelectionModel().select(0);
        cbGasCostPerVol.getItems().addAll("Dollars/Gallon", "Dollars/Liter");
        cbGasCostPerVol.getSelectionModel().select(0);
        cbDistancePerVol.getItems().addAll("Miles/Gallon", "Kilometers/Liter");
        cbDistancePerVol.getSelectionModel().select(0);
        
        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(cbDistanceMeasurement, HPos.RIGHT);
        GridPane.setHalignment(cbGasCostPerVol, HPos.RIGHT);
        GridPane.setHalignment(cbDistancePerVol, HPos.RIGHT);
        btCalculate.setAlignment(Pos.CENTER);
        btCalculate.prefWidthProperty().bind(tfDistance.widthProperty());
        cbDistanceMeasurement.setPrefWidth(130);
        cbGasCostPerVol.setPrefWidth(130);
        cbDistancePerVol.setPrefWidth(130);
        tfTotalTripCost.setEditable(false);
        tfDistance.setAlignment(Pos.BOTTOM_RIGHT);
        tfGasCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfGasMileage.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumDays.setAlignment(Pos.BOTTOM_RIGHT);
        tfHotelCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfFoodCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfAttractions.setAlignment(Pos.BOTTOM_RIGHT);
        tfTotalTripCost.setAlignment(Pos.BOTTOM_RIGHT);

        // Process events
        btCalculate.setOnAction(e -> calculateTripCost());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 300);
        gridPane.requestFocus();
        primaryStage.setTitle("Trip Cost Estimator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        private void calculateTripCost() {
            // Get values from text fields
            double distance = Double.parseDouble(tfDistance.getText());
            double gasCost = Double.parseDouble(tfGasCost.getText());
            double gasMileage = Double.parseDouble(tfGasMileage.getText());
            double numDays = Double.parseDouble(tfNumDays.getText());
            double hotelCost = Double.parseDouble(tfHotelCost.getText());
            double foodCost = Double.parseDouble(tfFoodCost.getText());
            double attractions = Double.parseDouble(tfAttractions.getText());
            String setting1 = cbDistanceMeasurement.getValue();
            String setting2 = cbGasCostPerVol.getValue();
            String setting3 = cbDistancePerVol.getValue();

            // Create TripCost object
            TripCost tripCost = new TripCost(distance, gasCost, gasMileage, numDays, hotelCost, foodCost, 
            attractions, setting1, setting2, setting3);

            // Display total trip cost
            tfTotalTripCost.setText(String.format("$%.2f", tripCost.calculateTripCost()));
        }

        private class TripCost {
            private double distance;
            private double gasCost;
            private double gasMileage;
            private double numDays;
            private double hotelCost;
            private double foodCost;
            private double attractions;
            private String setting1;
            private String setting2;
            private String setting3;

            TripCost (double distance, double gasCost, double gasMileage, double numDays, double hotelCost, double foodCost, 
                double attractions, String setting1, String setting2, String setting3) {
                this.distance = distance;
                this.gasCost = gasCost;
                this.gasMileage = gasMileage;
                this.numDays = numDays;
                this.hotelCost = hotelCost;
                this.foodCost = foodCost;
                this.attractions = attractions;
                this.setting1 = setting1;
                this.setting2 = setting2;
                this.setting3 = setting3;
            }
            
            public double calculateTripCost() {
                double totalTripCost = 0;
                if (setting1.equals("Kilometers")) {
                    distance = distance * 0.621371;
                }
                if (setting2.equals("Dollars/Liter")) {
                    gasCost = gasCost * 3.78541;
                }
                if (setting3.equals("Kilometers/Liter")) {
                    gasMileage = gasMileage * 0.425144;
                }
                totalTripCost = (distance / gasMileage) * gasCost + (hotelCost + foodCost) * numDays + attractions;
                return totalTripCost;
            }
        }
        
    public static void main(String[] args) {
        launch(args);
    }
}
