package adventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class day10 {
    public static void main (String[] args) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(
            "C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\CMSC 115\\Java\\adventOfCode\\day10.txt"));
        part1(file);
        //part2(file);
    }

    public static void part1 (BufferedReader f) throws Exception {
        ArrayList<String> fList = new ArrayList<String>();
        String line;
        while ((line = f.readLine()) != null)
            fList.add(line);
        int[] sLoc = new int[2]; //mouse starting location {row #, col #}
        for (int rows = 0; rows < fList.size(); rows++) {
            String row = fList.get(rows);
            int rowLen = row.length();
            for (int cols = 0; cols < rowLen; cols ++) {
                if (row.charAt(cols) == 'S') {
                    sLoc[0] = rows;
                    sLoc[1] = cols;
                }
            }
        }
        boolean startingPoint = false;
        boolean canGoLeft = true;
        boolean canGoRight = true;
        boolean canGoDown = true;
        boolean CanGoUp = true;
        char lastMovedDir = ' ';
        int rowLen = fList.get(0).length();
        int colLen = fList.size();
        int[] searchStart = Arrays.copyOf(sLoc, 2); //{row #, Col #}
        int steps = 0;
        while (!startingPoint) {
            steps ++;
            // System.out.println("search start: " + Arrays.toString(searchStart) + " last movement: " + lastMovedDir);
            if (searchStart[1] == 0) {
                canGoLeft = false;
            }
            if (searchStart[1] == rowLen - 1) {
                canGoRight = false;
            }
            if (searchStart[0] == 0) {
                CanGoUp = false;
            }
            if (searchStart[0] == colLen - 1) {
                canGoDown = false;
            }
            if (canGoLeft && lastMovedDir != 'R' && legalPipe(searchStart, 'L', fList)) {
                searchStart[1] --;
                // System.out.println("next coord left, char: " + fList.get(searchStart[0]).charAt(searchStart[1]));
                lastMovedDir = 'L';
            }
            else if (canGoRight && lastMovedDir != 'L' && legalPipe(searchStart, 'R', fList)) {
                searchStart[1] ++;
                // System.out.println("next coord right, char: " + fList.get(searchStart[0]).charAt(searchStart[1]));
                lastMovedDir = 'R';
            }
            else if (CanGoUp && lastMovedDir != 'D' && legalPipe(searchStart, 'U', fList)) {
                searchStart[0] --;
                // System.out.println("next coord up, char: " + fList.get(searchStart[0]).charAt(searchStart[1]));
                lastMovedDir = 'U';
            }
            else if (canGoDown && lastMovedDir != 'U' && legalPipe(searchStart, 'D', fList)) {
                searchStart[0] ++;
                // System.out.println("next coord down, char: " + fList.get(searchStart[0]).charAt(searchStart[1]));
                lastMovedDir = 'D';
            }
            else {
                startingPoint = true;
            }
            // System.out.println("search moved to: " + Arrays.toString(searchStart) + " steps taken: " + steps + "\n");
            System.out.println("Step #: " + steps + ", checked pipe in the " + lastMovedDir + " direction, current pipe ("
                + searchStart[0] + ", " + searchStart[1] + "): " + fList.get(searchStart[0]).charAt(searchStart[1]));
            // System.out.println("S start location: " + Arrays.toString(sLoc));
            // if (searchStart == sLoc || steps == 20) {
            //     startingPoint = true;
            // }
            canGoLeft = true;
            canGoRight = true;
            canGoDown = true;
            CanGoUp = true;
        }
        System.out.println("The furthest point a mouse can be is " + steps/2 + " steps away.");
    }

    public static boolean legalPipe (int[] coord, char direction, ArrayList<String> f) {
        if (direction == 'L' && ((f.get(coord[0]).charAt(coord[1]) == 'J') || (f.get(coord[0]).charAt(coord[1]) == '7') || (f.get(coord[0]).charAt(coord[1]) == '-') || (f.get(coord[0]).charAt(coord[1]) == 'S'))) {
            if ((f.get(coord[0]).charAt(coord[1] - 1) == 'L') || (f.get(coord[0]).charAt(coord[1] - 1) == 'F') || (f.get(coord[0]).charAt(coord[1] - 1) == '-')) {
            return true;
            }
        }
        else if (direction == 'R' && ((f.get(coord[0]).charAt(coord[1]) == 'F') || (f.get(coord[0]).charAt(coord[1]) == 'L') || (f.get(coord[0]).charAt(coord[1]) == '-') || (f.get(coord[0]).charAt(coord[1]) == 'S'))) {
            if ((f.get(coord[0]).charAt(coord[1] + 1) == 'J') || (f.get(coord[0]).charAt(coord[1] + 1) == '7') || (f.get(coord[0]).charAt(coord[1] + 1) == '-')) {
            return true;
            }
        }
        else if (direction == 'U' && ((f.get(coord[0]).charAt(coord[1]) == 'J') || (f.get(coord[0]).charAt(coord[1]) == 'L') || (f.get(coord[0]).charAt(coord[1]) == '|') || (f.get(coord[0]).charAt(coord[1]) == 'S'))) {
            if ((f.get(coord[0] - 1).charAt(coord[1]) == 'F') || (f.get(coord[0] - 1).charAt(coord[1]) == '7') || (f.get(coord[0] - 1).charAt(coord[1]) == '|')) {
            return true;
            }
        }
        else if (direction == 'D' && ((f.get(coord[0]).charAt(coord[1]) == 'F') || (f.get(coord[0]).charAt(coord[1]) == '7') || (f.get(coord[0]).charAt(coord[1]) == '|') || (f.get(coord[0]).charAt(coord[1]) == 'S'))) {
            if ((f.get(coord[0] + 1).charAt(coord[1]) == 'J') || (f.get(coord[0] + 1).charAt(coord[1]) == 'L') || (f.get(coord[0] + 1).charAt(coord[1]) == '|')) {
            return true;
            }
        }
        return false;
    }
}
