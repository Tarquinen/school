package Week7;

public class discussion {
    public static void main(String[] args) {

    }

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


