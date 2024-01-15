/* 
 * Daniel Smolsky
 * Programming Project 1: Tallest Basketball Player
 * January 12, 2024
 * This class promps the user for information about each basketball player. The information is
 * stored in an array, and then later accessed to determine the tallest player below average age.
 */

package project1;
import java.util.ArrayList;
import java.util.Scanner;

public class Project1 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many players will be part of the data set?");
        int playerCount = input.nextInt();
        ArrayList<Player> playersData = new ArrayList<>();
        int combinedAge = 0;

        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player " + (i + 1) + " information:\nName: ");
            String tempName = input.next();
            System.out.print("Height (Feet Inches): ");
            int tempHeightFeet = input.nextInt();
            int tempHeightInches = input.nextInt();
            System.out.print("Age: ");
            int tempAge = input.nextInt();
            combinedAge += tempAge;
            try {
                playersData.add(new Player(tempName, new Height(tempHeightFeet, tempHeightInches), tempAge));
            }
            catch (IllegalArgumentException e) {
                System.out.println("invalid height provided, enter information for this player again.");
                i--;
            }
        }

        double avgAge = (double) combinedAge / playerCount;
        Player tallest = null;

        for (Player player : playersData) {
            if (player.getAge() <= avgAge) {
                if (tallest == null)
                    tallest = player;
                else if (player.getHeight().toInches() > tallest.getHeight().toInches())
                    tallest = player;
            }
        }

        System.out.println("the tallest player is " + tallest.toString());
        input.close();
    }
}
