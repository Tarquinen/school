package playground;

import java.util.Arrays;

public class removeDuplicates {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 4, 43, 2, 1, 0, 234, 10, 4, 2, 1, 7, 111, 111, 1};
        nums = insertionSort(nums);
        System.out.println(Arrays.toString(nums));
        Solution solution = new Solution();
        solution.removeDuplicates(nums);
        System.out.println(Arrays.toString(solution.removeDuplicates(nums)));
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

class Solution {
    public int[] removeDuplicates(int[] nums) {
        int length = nums.length;
        int[][] numsDict = new int[length][2]; //[value in original array][count occurences]
        int dictIdx = 0;
        int outputLength = 0;
        
        for (int i = 0; i < length - 1; i++) {
            numsDict[dictIdx][0] = nums[i];
            numsDict[dictIdx][1] ++;
            if (nums[i + 1] != numsDict[dictIdx][0]) {
                dictIdx++;
            }
        }
        numsDict[dictIdx][0] = nums[length-1];
        numsDict[dictIdx][1] ++;
        for (int i = 0; i < numsDict.length; i++) {
            if (numsDict[i][1] > 0) {
                outputLength ++;
            }
        }
        int[] output = new int[outputLength];
        for (int i = 0; i < outputLength; i++) {
            output[i] = numsDict[i][0];
        }



        return output;

    }
}