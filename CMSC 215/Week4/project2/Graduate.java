package project2;

public class Graduate extends Student {
    private String year;
    
    public Graduate(String name, int creditHours, int qualityPoints, String schoolYear) {
        super(name, creditHours, qualityPoints);
        this.year = schoolYear;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        if (this.year.equals("Doctorate")) {
            return false;
        }
        else {
            return super.eligibleForHonorSociety();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.year;
    }
}