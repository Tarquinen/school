package playground;

import java.util.ArrayList;


/*********************************************************************************
* (Game: Eight Queens) The classic Eight Queens puzzle is to place eight queens  *
* on a chessboard such that no two queens can attack each other (i.e., no two    *
* queens are on the same row, same column, or same diagonal). There are many     *
* possible solutions. Write a program that displays one such solution.           *
*********************************************************************************/

public class EightQueens {
    
    static int boardSize = 8; // change this value to change board size
    static int [][] queens = new int[boardSize][boardSize]; //set the size of the chess board [x][y]
    static int[][] queenCoords = new int[queens.length + 1][2];
    static int xLength = queens.length;
    static int yLength = queens[0].length;
    static ArrayList<int[][]> solutions = new ArrayList<int[][]>();
    static boolean solveable = true;

    public static void main(String[] args) {
        int desiredQueens = boardSize;
        long startTime = System.nanoTime();
        int[] lastQueen = new int[]{-1, 0};
        placeQueen(queens, lastQueen, desiredQueens); 
                 
        while (solveable) {
            removeLastQueen(queens);
            lastQueen = lastQueenCoord(queens);
            placeQueen(queens, lastQueen, desiredQueens);
            //System.out.println("\nThe last queen was placed at array indeces [x, y]: " + lastQueenCoord(queens)[0] + ", " + lastQueenCoord(queens)[1]);
        }

        System.out.println("\nThere are " + solutions.size() + " total solutions");
        long endTime = System.nanoTime();
        System.out.println("milliseconds taken to calculate: " + (endTime-startTime)/1000000);
    }

    public static void placeQueen (int[][] board, int[] lastQueen, int desiredQueens) {
        int numQueens = countQueens(queens);
        int attemptCounter = 0;
        boolean queenOnRow = false;

        while (numQueens != desiredQueens) {
            for (int y = lastQueen[1]; y < yLength; y++) {
                //loop through each square on the chess board starting with 1 square past where the last queen that didn't work was placed
                for (int x = lastQueen[0] + 1; x < xLength; x++) { 
                    if (squareIsNotLegal(queens, x, y)) {
                        continue;
                    }
                    queens[x][y] = 1;
                    queenOnRow = true;
                    numQueens ++;
                    attemptCounter++;
                    queenCoords[numQueens][0] = x;
                    queenCoords[numQueens][1] = y;

                    if (numQueens == desiredQueens) {
                        System.out.println("\nSOLUTION FOUND, took " + attemptCounter + " tries. Total solutions: " + (solutions.size() + 1));
                        printBoard(queens);
                        int[][] queenCoordsCopy = new int[queenCoords.length][];
                        for (int i = 0; i < queenCoords.length; i++) {
                            queenCoordsCopy[i] = queenCoords[i].clone();
                        }
                        solutions.add(queenCoordsCopy);
                        return;
                    }
                    break;    
                }
                lastQueen[0] = -1;
                if (!queenOnRow) //if no queens were added to the last row, remove last queen and reset
                    break;
                else
                    queenOnRow = false;
            }
            if (numQueens != desiredQueens) {
                queens[queenCoords[numQueens][0]][queenCoords[numQueens][1]] = 0; //delete last placed queen
                lastQueen[0] = queenCoords[numQueens][0]; // store the queen in another variable before delete, not actually needed maybe fix later
                lastQueen[1] = queenCoords[numQueens][1];
                numQueens --;
                if (numQueens == -1) {
                    solveable = false;
                    return;
                }
            }
        }
    }

    //returns the coordinates of the last queen placed on the board
    //in a single dimensional array, [x, y]
    public static int[] lastQueenCoord (int[][] queens) {
        for (int y = yLength - 1; y >= 0; y--) {
            for (int x = xLength - 1; x >= 0; x--) {
                if (queens[x][y] == 1) {
                    return new int []{x, y};
                }
            }
        }
        return new int []{0, 0};
    }
    
    public static void removeLastQueen (int[][] queens) {
        for (int y = yLength - 1; y >= 0; y--) {
            for (int x = xLength - 1; x >= 0; x--) {
                if (queens[x][y] == 1) {
                    queens[x][y] = 0;
                    return;
                }
            }
        }
    }

    public static int countQueens (int[][]queens) {
        int counter = 0;
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                if (queens[x][y] == 1) {
                    counter ++;
                }
            }
        }
        return counter;
    }

    public static boolean squareIsNotLegal (int[][] queens, int x, int y) {
        for (int checkX = 0; checkX < xLength; checkX ++) { // check row for queen
            if (queens[checkX][y] == 1) {
                return true;
            }
        }
        for (int checkY = 0; checkY < yLength; checkY++) { //check col for queen
            if (queens[x][checkY] == 1) {
                return true;
            }
        }
        //check diagonal top left to bot right, +x -y
        //coord start at x: 0 or x- (yLength - 1 - y) whichever bigger
        //coord start at y: x + y or yLength - 1 whichever smaller
        //repeat until searched y = 0 or search x = xLength

        int xStart = Math.max(0, x - (yLength - 1 - y));
        int yStart = Math.min(x + y, yLength - 1);
        for (int i = 0; (yStart - i >= 0) && (xStart + i < xLength); i++) {
            if (queens[xStart + i][yStart - i] == 1) {
                return true;
            }
        }

        //check diagonal bot left to top right, +x +y
        //coord start at x: x-y or 0 whichever bigger
        //coord start at y: 0 or y-x whichever bigger
        //repeat while y < yLength - 1 && x < array.length - 1

        xStart = Math.max(x - y, 0);
        yStart = Math.max(0, y - x);

        for (int i = 0; (yStart + i < yLength) && (xStart + i < xLength); i++) {
            if (queens[xStart + i][yStart + i] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void printArray (int[][] array) {
        for (int y = array[0].length - 1; y >= 0; y--) {
            for (int x = 0; x < array.length; x++) {
                System.out.print(" " + array[x][y]);
            }
            System.out.println("");
        }
    }

    //the following method creates a 2d demonstration of a chess board 
    //using a multidimensional array input
    public static void printBoard (int[][] array) {
        for (int y = yLength - 1; y >= 0; y--) {
            System.out.print(String.format("%-2d", y + 1) + "|");
            for (int x = 0; x < xLength; x++) {
                if (array[x][y] == 0) {
                    System.out.print(" |");
                }
                else{
                    System.out.print("Q|");
                }
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 0; i < xLength; i++) {
            System.out.print((char)('A' + i) + " ");
        }
    }
}