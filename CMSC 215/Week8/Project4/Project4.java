package Project4;

public class Project4 {
    public static void main(String[] args) throws Exception {
        Time time1 = new Time(01, 55, "AM");
        Time time2 = new Time(03, 50, "PM");
        Time time3 = new Time("03:49 PM");
        // System.out.println(time1.compareTo(time2)); 
        Interval<Time> int1 = new Interval<Time>(time1, time2);
        System.out.println(int1.within(time3));





    }
}
