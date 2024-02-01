package project2;

public class Student {
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
        return Double.parseDouble(String.format("%.2f", (double) this.qualityPoints / this.creditHours));
    } 

    public boolean eligibleForHonorSociety() {
        return this.gpa() >= GPA_THRESHOLD;
    }

    public String toString() {
        return "Students name: " + this.name + " GPA: " + this.gpa();
    }
}