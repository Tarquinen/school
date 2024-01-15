/* 
 * Daniel Smolsky
 * Programming Project 1: Tallest Basketball Player
 * January 12, 2024
 * This class is used to create player height objects with two getter methods used
 * to access the player height in inches and their height as a string. 
 */

package project1;

public class Height {
    private int feet;
    private int inches;

    Height(int feet, int inches) {
        if (feet >= 0 && inches >= 0) {
            this.feet = feet + inches / 12;
            this.inches = inches % 12;
        }
        else
            throw new IllegalArgumentException();
    }

    public int toInches() {
        return this.feet * 12 + this.inches;
    }

    public String toString() {
        return this.feet + "\' " + this.inches + "\"";
    }
}