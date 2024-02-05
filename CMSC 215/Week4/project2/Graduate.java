/* 
 * Daniel Smolsky
 * Programming Project 2: Student Honor Society
 * Feb 2, 2024
 * This class creates Graduate objects extending from the Student variables and methods. 
 * This class has 1 instance variable and 2 methods that override methods within the Student class.
*/

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