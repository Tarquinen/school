package playground;

import java.util.Arrays;

public class practice {
    public static int[] makeArray (int length, int start, int end) {
        int[] array = new int[length];
        int random;
        for (int i = 0; i < length; i ++) {
            random = (int) (Math.random() * (end - start + 1) + start);
            array[i] = random;
        }
        return array;
    }

    public static String printArray (int[] array) {
        String output = "";
        for (int i = 0; i < array.length; i ++) {
            if (i % 10 == 0)
                output += "\n";
            output += String.format("%-9d", array[i]);
        }

        return output;
    }

    public static int[] sortArray (int[] array) {
        int[] sorted = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = Integer.MAX_VALUE;
            sorted[i] = min;
        }
        return sorted;
    }

    
    public static int[] sortArray2(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
    

    public static int[] selectionSort (int[] array) {
        //int[] sorted = new int[array.length];
        int min;
        int minIndex = 0;
        int temp;
    
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            for (int j = 0 + i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int binarySearch (int[] array, int search) {
        int topIndex = array.length - 1;
        int bottomIndex = 0;
        int midIndex; //= (topIndex + bottomIndex) / 2;
        int counter = 1;
        while (true) {
            midIndex = (topIndex + bottomIndex) / 2; //+ bottomIndex;
            System.out.println("guess " + counter + ": " + array[midIndex] + " at index " + midIndex);
            if (counter == 12) {
                System.out.println("The closest found value was " + array[midIndex] + " " + array[midIndex + 1]);
                return -1;
            }
            if (array[midIndex] == search) {
                System.out.println("at index " + midIndex);
                return midIndex;
            }
            else if (array[midIndex] < search) {
                bottomIndex = midIndex + 1;
            }
            else {
                topIndex = midIndex -1;
            }
            counter ++;
        }
    }


    public static void main (String[] args) {
        int[] array = makeArray(1000, 0, 1000000);
        //System.out.println(printArray(array));
        //long startTime = System.nanoTime();
        //System.out.println(printArray(selectionSort(array)));
        //binarySearch(sortArray(array), 100);
        // long endTime = System.nanoTime();
        // System.out.println("milliseconds: " + (endTime-startTime)/1000000);
        /*
        long startTime = System.nanoTime();
        selectionSort(array);
        long endTime = System.nanoTime();
        System.out.println("selection sort milliseconds: " + (endTime-startTime)/1000000);

        startTime = System.nanoTime();
        sortArray(array);
        endTime = System.nanoTime();
        System.out.println("sort milliseconds: " + (endTime-startTime)/1000000);

        startTime = System.nanoTime();
        sortArray2(array);
        endTime = System.nanoTime();
        System.out.println("sort 2 milliseconds: " + (endTime-startTime)/1000000);
        
        long startTime = System.nanoTime();
        Arrays.sort(array);
        long endTime = System.nanoTime();
        System.out.println("default milliseconds: " + (endTime-startTime) / 1000000);
        */

        long startTime = System.nanoTime();
        Arrays.parallelSort(array);
        System.out.println(printArray(array));
        long endTime = System.nanoTime();
        System.out.println("default milliseconds: " + (endTime-startTime) / 1000000);

    }
}
