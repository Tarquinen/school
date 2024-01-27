import java.util.ArrayList;
import java.util.Scanner;

public class Exercise11_13 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("Enter ten integers: ");
        for (int i = 0; i < 10; i++) {
            list.add(input.nextInt());
        }
        removeDuplicate(list);
        input.close();
    }

    public static void removeDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> temp = new ArrayList<>();
        String distinct = "";
        for (Integer x : list) {
            if (!temp.contains(x)) {
                temp.add(x);
                distinct += x + " ";
            }
        }
        list = temp;
        System.out.println("The distinct integers are " + distinct);
    }    
}