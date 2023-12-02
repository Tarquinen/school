package playground;
//trash file
/*
public class FourVertexPolygon {

    public static void main (String[] args) {
        double[][] allPoints = new double[4][2];

        allPoints[0] = randomCoords(0, 10);
        allPoints[1] = randomCoords(0, 10);
        allPoints[2] = randomCoords(0, 10);
        allPoints[3] = randomCoords(0, 10);

        //allPoints[point #][x or y]
        System.out.println(allPoints[0][0] + ", " + allPoints[0][1]); 
        System.out.println(allPoints[1][0] + ", " + allPoints[1][1]);
        System.out.println(allPoints[2][0] + ", " + allPoints[2][1]);
        System.out.println(allPoints[3][0] + ", " + allPoints[3][1]);
    }

    public static int[][] sortIndex(double[][] allPoints) {
        int[] sortX = new int[allPoints.length];
        int[] sortY = new int[allPoints[0].length];
        int tempX;
        int tempY;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3 - i; j++) {
                if (allPoints[j][0] > allPoints[j + 1][0]) { // sort in increasing order
                    tempX = allPoints[j][0];
                    tempY = allPoints[j][1];
                    allPoints[j][0] = allPoints[j + 1][0];
                    allPoints[j][1] = allPoints[j + 1][0];
                    allPoints[j + 1][0] = tempX;
                    allPoints[j + 1][1] = tempY;
                }
            }
        }
        //return new int[][]{xIndex, yIndex};
    }

    public static double[] randomCoords (double min, double max) { 
        double rand1 = Math.random() * (max - min) + min;
        double rand2 = Math.random() * (max - min) + min;
        return new double[]{rand1, rand2};
    }
}
*/