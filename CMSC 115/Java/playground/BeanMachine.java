package playground;
import java.util.Scanner;

public class BeanMachine {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int balls;
        int slots;
        System.out.println("Enter the number of balls to drop: ");
        balls = input.nextInt();
        System.out.println("Enter the number of slots: ");
        slots = input.nextInt();
        int[] positions = new int[slots];
        //System.out.println(dropBall(slots));

        String dropPath;
        int ballPos;
        for (int i = 0; i < balls; i++) {
            dropPath = dropBall(slots);
            //System.out.println("drop path " + dropPath);
            ballPos = pathToInt(dropPath);
            //System.out.println("ball position " + ballPos);
            positions[ballPos - 1] ++;
        }
        System.out.println(printPositions2(positions, balls));
        //System.out.println("| | | | | | | | | | |");
        input.close();
    }

    public static String dropBall (int slots) {
        int drops = slots - 1;
        String dropPath = "";
            for (int i = 0; i < drops; i++) {
                dropPath += randDirection();
            }
            return dropPath;
    }

    public static int pathToInt (String dropPath) {
        int move = 1;
        for (int i = 0; i < dropPath.length(); i++) {
            if (dropPath.charAt(i) == 'R') {
                move++;
            }
            else {
                continue;
            }
        }
        return move;
    }

    public static String printPositions2(int[] positions, int balls) {
        StringBuilder output = new StringBuilder();
        int max = getMaxValue(positions);
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] >= i) {
                    output.append("O ");
                } else {
                    output.append("  "); // Add a space to represent an empty slot
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
    
    public static int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static String printPositions (int[] positions, int balls) {
        //System.out.println(positions.length);
        for (int i = 0; i < positions.length; i++) {
            System.out.println(positions[i]);
        }

        int max = positions[0];
        int prevMax = 0;
        int maxIndex = 0;
        int prevMaxIndex = 0;
        String output = "";
        for (int i = 0; i < balls; i++) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] > max) {
                    max = positions[j];
                    maxIndex = j;
                }
            }
            //System.out.println("max " + max + " index " + maxIndex);
            positions[maxIndex]--;
            if (prevMax != max) {
                output += "\n";
                prevMaxIndex = 0;
            }
            for (int j = 0; j < maxIndex - prevMaxIndex - 1; j++) {
                output += " ";
            }
            output += "O";
            prevMax = max;
            prevMaxIndex = maxIndex;
            max = 0;
        }
        return output;
    }

    public static String randDirection() {
        if ((int)(Math.random() + .5) == 0) {
            return "L";
        } 
        else {
            return "R";
        }
    }
}

