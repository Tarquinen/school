//https://adventofcode.com/2023/day/3

package adventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class day3 {
    public static void main(String[] args) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(
            "C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\CMSC 115\\Java\\adventOfCode\\day3.txt"));
        part1(file);
    }

    public static void part1 (BufferedReader f) throws Exception {
        ArrayList<String> fList = new ArrayList<String>();
        String line;

        while ((line = f.readLine()) != null) {
            fList.add(line);
        }
        int strLength = fList.get(0).length();
        int totalSum = 0;

        for (int i = 0; i < fList.size(); i++) {
            String str = fList.get(i);
            int[] numCoord = new int[2]; //[number start index][number end index]
            numCoord[0] = -1;

            for (int j = 0; j < strLength; j++) {
                if (Character.isDigit(str.charAt(j))) {
                    numCoord[1] = j;
                    if (numCoord[0] == -1) {
                        numCoord[0] = j;
                    }
                    else {
                        numCoord[1] = j;
                    }
                }
                boolean symbolNear = false;
                boolean finishedNumber = false;
                if (j == numCoord[1] + 1 || numCoord[1] == strLength - 1) { // number ended on prev character
                    finishedNumber = true;
                    //check all coords around the number
                    //System.out.println("past the number now " + numCoord[1]);
                    for (int k = -1; k < 2; k++) {
                        if (symbolNear)
                            break;
                        for (int l = 0; l < (numCoord[1] - numCoord[0] + 3); l++) {
                            if (symbolNear)
                                break;
                            try {
                                if (isSymbol(fList.get(i + k).charAt(numCoord[0] + l - 1))) {//check all the characters in a box around the number coords
                                    symbolNear = true;
                                }
                            }
                            catch (IndexOutOfBoundsException e) {
                            }
                        }
                    }
                }
                if (symbolNear) {
                    int coordValue = Integer.parseInt(str.substring(numCoord[0], numCoord[1] + 1));
                    System.out.println(coordValue);
                    totalSum += coordValue;
                }
                if (finishedNumber) {
                    numCoord[0] = -1;
                }
            }
        }
        System.out.println("TOTAL: " + totalSum);
    }

    public static boolean isSymbol (char c) {
        if (!Character.isDigit(c) && c != '.')
            return true;
        return false;
    }
}
