public class Exercise10_01 {
    public static void main(String[] args) {
        Time time1 = new Time();
        System.out.println(time1.getHour() + ":" +
        time1.getMinute() + ":" + time1.getSecond());

        Time time2 = new Time(555550000);
        System.out.println(time2.getHour() + ":" +
        time2.getMinute() + ":" + time2.getSecond());

        Time time3 = new Time(5, 23, 55);
        System.out.println(time3.getHour() + ":" +
        time3.getMinute() + ":" + time3.getSecond());  
    }
}

class Time {
  // Write your code
  // Hint for finding hour, minute, second from elapse time.
  // See LiveExample 2.7 for extracting hour, minute, 
  // and second from the elapse time.

    private int hour;
    private int minute;
    private int second;
    Time() {
        setTime(System.currentTimeMillis());
    }

    Time(long elapsed) {
        setTime(elapsed);
    }

    Time (int hours, int minutes, int seconds) {
        this.hour = hours;
        this.minute = minutes;
        this.second = seconds;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    public void setTime (long elapseTime) {
        long secondsElapsed = elapseTime / 1000;
        long minutesElapsed = secondsElapsed / 60;
        long hoursElapsed = minutesElapsed / 60;
        this.hour = (int)(hoursElapsed % 24);
        this.minute =(int)(minutesElapsed % 60);
        this.second = (int)(secondsElapsed % 60);
        // System.out.println(this.hours + ":" + this.minutes + ":" + this.seconds);
    }
}