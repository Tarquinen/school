package Week7;
import java.util.Scanner;

public class Exercise08_21 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int numCities;

        System.out.println("Enter the number of cities: ");
        numCities = input.nextInt();

        double[][] cityCoordinates = new double[numCities][2];
        System.out.println("Enter the coordinates of the cities: ");
        for (int i = 0; i < numCities; i++) {
            cityCoordinates[i][0] = input.nextDouble();
            cityCoordinates[i][1] = input.nextDouble();
        }

        double[] distancesOfEachCity = distancesToAllCities(cityCoordinates, numCities);
        double[] centralCity = minArrayValue(distancesOfEachCity);
        double centralCityTotalDistance = centralCity[0];
        int centralCityIndex = (int)centralCity[1];

        System.out.println("The central city is at (" + cityCoordinates[centralCityIndex][0] + ", " + cityCoordinates[centralCityIndex][1] + ")");
        System.out.println("The total distance to all other cities is " + centralCityTotalDistance);

        input.close();
    }

    public static double[] distancesToAllCities (double[][] cityCoordinates, int numCities) {
        double[] allCitySums = new double[numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i == j)
                    continue;
                allCitySums[i] += distanceToCity(cityCoordinates, i, j);
            }
        }
        return allCitySums;
    }

    public static double distanceToCity (double[][] compareCities, int city1, int city2) {
        double xDiff = Math.abs(compareCities[city1][0] - compareCities[city2][0]);
        double yDiff = Math.abs(compareCities[city1][1] - compareCities[city2][1]);

        //distance apart = sqrt(x^2 + y^2)
        double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        return distance;
    }

    public static double[] minArrayValue (double[] distances) {
        double minValue = distances[0];
        int minValueIndex = 0;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < minValue) {
                minValue = distances[i];
                minValueIndex = i;
            }
        }
        return new double[]{minValue, minValueIndex};
    }
}