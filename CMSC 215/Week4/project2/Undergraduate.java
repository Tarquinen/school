package project2;

public class Undergraduate extends Student {
    private String YEAR; 
    
    public Undergraduate(String name, int creditHours, int qualityPoints, String schoolYear) {
        super(name, creditHours, qualityPoints);
        this.YEAR = schoolYear;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        if (this.YEAR.equals("Freshman") || this.YEAR.equals("Sophomore")) {
            return false;
        }
        else {
            return super.eligibleForHonorSociety();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.YEAR;
    }
}