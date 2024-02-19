public class test {
    public static void main(String[] args) {
        // int[] arr = new int[]{3, 6, 8, 1, 3, 1, 9, 34, 11};
        // System.out.println(binarySearch(arr, 34));
        System.out.println(fibonacciDynamic(50)); // not index based
        System.out.println(fibonacci(49)); // index based
    }
    
    public static int binarySearch(int[] array, int key) {
        return binarySearch(array, key, 0, array.length-1);
    }

    public static int binarySearch(int[] array, int key, int startI, int endI) {
        int midI = (startI + endI) / 2;
        if (startI > endI) {
            return -1;
        }
        if (key == array[midI])
            return midI;
        else if (key < array[midI]) {
            return binarySearch(array, key, startI, midI - 1);
        }
        else {
            return binarySearch(array, key, midI + 1, endI);
        }
    }

    public static long  fibonacci(int x) {
        if (x == 0)
            return 0;
        else if (x == 1) 
            return 1;
        else 
            return fibonacci(x - 1) + fibonacci(x - 2);
    }


    static long[] fibDict;
    public static long fibonacciDynamic(int target) {
        fibDict = new long[target];
        fibDict[0] = 0;
        fibDict[1] = 1;
        return fibonacciDynamic(target, 2);
    }

    public static long fibonacciDynamic(long target, int index) {
        if (target <= index)
            return fibDict[index - 1];
        fibDict[index] = fibDict[index - 1] + fibDict[index - 2];
        // System.out.println("index: " + index + " " + fibDict[index]);
        return fibonacciDynamic(target, index + 1);
    }
}