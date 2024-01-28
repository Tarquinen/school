package project2;

public class Graduate extends Student {
    private String YEAR;
    
    Graduate(String name, int creditHours, int qualityPoints, String schoolYear) {
        super(name, creditHours, qualityPoints);
        this.YEAR = schoolYear;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        if (this.YEAR.equals("Doctorate")) {
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