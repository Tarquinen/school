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

        for (int i = 0; i < fList.size(); i++) {
            String str = fList.get(i);

            int[] numCoord = new int[2]; //[number start index][number end index]
            for (int j = 0; j < strLength; j++) {
                if (Character.isDigit(str.charAt(j))) {
                    if (numCoord[0] == 0) {
                        numCoord[0] = j;
                    }
                    else {
                        numCoord[1] = j;
                    }
                }
                if (j == numCoord[1] + 1) { // number ended on prev character
                    //check all coords around the number
                    

                }













                // System.out.println("j start : " + j);
                // int k;
                // for (k = 0; Character.isDigit(str.charAt(j + k)); k ++) {
                //     numCoord[0] = j;
                //     numCoord[1] = j + k;
                //     System.out.println("found number");
                // }
                // System.out.println(Arrays.toString(numCoord) + " current coord: " + i + ", " + j);
                // if (k > 0) 
                //     j += k-1;
                // //System.out.println("k here: " + k);
                // System.out.println("j here: " + j);
            }
        }
    }
}
