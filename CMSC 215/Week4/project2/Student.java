package project2;

public class Student {
    private String NAME;
    private int CREDIT_HOURS;
    private int QUALITY_POINTS;
    private static double GPA_THRESHOLD = 3;

    Student(String name, int creditHours, int qualityPoints) {
        this.NAME = name;
        this.CREDIT_HOURS = creditHours;
        this.QUALITY_POINTS = qualityPoints;
    }

    public static void setGpaThreshold(double gpa) {
        GPA_THRESHOLD = gpa;
    }

    public double gpa() {
        return Double.parseDouble(String.format("%.2f", (double) this.QUALITY_POINTS / this.CREDIT_HOURS));
    } 

    public boolean eligibleForHonorSociety() {
        return this.gpa() >= GPA_THRESHOLD;
    }

    public String toString() {
        return "Students name: " + this.NAME + " GPA: " + this.gpa();
    }
}
