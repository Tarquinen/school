/* 
 * Daniel Smolsky
 * Programming Project 1: Tallest Basketball Player
 * January 12, 2024
 * This class is used to create player objects. This class has 3 getter methods to access
 * all of the stored variables and a toString method to return all of the player information.
 */

package project1;

public class Player {
    private String name;
    private Height height;
    private int age;

    Player(String name, Height height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public Height getHeight() {
        return this.height;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return "name: " + this.name + ", player height: " + this.height.toString() + ", player age: " + this.age;
    }
}