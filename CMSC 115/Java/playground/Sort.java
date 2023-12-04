package playground;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        //int[] builtArray = createArray(0, 10, 10);
        int[] builtArray = new int[] {9, 1, 5, 2, 7, 9, 6, 10, 6, 0};
        System.out.println("Initial array: " + Arrays.toString(builtArray));
        //builtArray = selectionSort(builtArray);
        //builtArray = bubbleSort(builtArray);
        builtArray = insertionSort(builtArray);
        System.out.println("Sorted array:  " + Arrays.toString(builtArray));
    }

    public static int[] createArray (int minValue, int maxValue, int numValues) {
        int[] array = new int[numValues];
        for (int i = 0; i < numValues; i++) {
            array[i] = (int)(Math.random() * (maxValue-minValue + 1) + minValue);
        }    
        return array;
    }

    public static int[] selectionSort(int[] arr) {
        int minValueIdx = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            minValueIdx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minValueIdx]) {
                    minValueIdx = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minValueIdx];
            arr[minValueIdx] = temp;
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        boolean swapped = true;
        for (int i = 0; i < length; i++) {
            if (!swapped)
                break;
            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
    

}
