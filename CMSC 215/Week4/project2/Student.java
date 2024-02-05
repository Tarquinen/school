/* 
 * Daniel Smolsky
 * Programming Project 2: Student Honor Society
 * Feb 2, 2024
 * This class creates abstract Student objects. This class has 4 base variables and 4 methods
 * that the Student subclasses will inherit.
*/

package project2;

public abstract class Student {
    private String name;
    private int creditHours;
    private int qualityPoints;
    private static double GPA_THRESHOLD;

    public Student(String name, int creditHours, int qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    public static void setGpaThreshold(double gpaThreshold) {
        GPA_THRESHOLD = gpaThreshold;
    }

    public double gpa() {
        return (double) this.qualityPoints / this.creditHours;
    } 

    public boolean eligibleForHonorSociety() {
        return this.gpa() >= GPA_THRESHOLD;
    }

    @Override
    public String toString() {
        return "Students name: " + this.name + " GPA: " + String.format("%.2f", this.gpa());
    }
}