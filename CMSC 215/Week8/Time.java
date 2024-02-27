public class Time implements Comparable<Time> {
    private int hours;
    private int minutes;
    private String meridian;
    private int hours24; // this variable will store hours converted to military format (24-hour clock)
    
    public Time (int hours, int minutes, String meridian) {
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
        hoursTo24();
    }

    public Time (String timeString) {
        this.hours = Integer.parseInt(timeString.substring(0, 2));
        this.minutes = Integer.parseInt(timeString.substring(3, 5));
        this.meridian = timeString.substring(6, 8);
        hoursTo24();
    }

    private void hoursTo24() {
        if ("PM".equals(meridian) && hours != 12) 
            hours24 = hours + 12;
        else if ("AM".equals(meridian) && hours == 12) 
            hours24 = 0;
        else 
            hours24 = hours;
    }

    @Override
    public int compareTo (Time otherTime) {
        // returns 1 if parameter is greater than instance
        if (otherTime.hours24 > this.hours24) {
            return 1;
        }
        else if (otherTime.hours24 < this.hours24) {
            return -1;
        }
        // if hours are equal, compare minutes
        else {
            if (otherTime.minutes > this.minutes) {
                return 1;
            }
            else if (otherTime.minutes < this.minutes) {
                return -1;
            }
            else return 0; //times are equal
        }
    }
}
