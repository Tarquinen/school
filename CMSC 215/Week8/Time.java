public class Time implements Comparable {
    private int hours;
    private int minutes;
    private String meridan;
    
    public Time (int hours, int minutes, String meridan) {
        this.hours = hours;
        this.minutes = minutes;
        this.meridan = meridan;
    }

    public Time (String timeString) {
        this.hours = Integer.parseInt(timeString.substring(0, 2));
        this.minutes = Integer.parseInt(timeString.substring(3, 5));
        this.meridan = timeString.substring(6, 8);
    }

    public static 
}
