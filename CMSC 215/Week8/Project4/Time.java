/**
 * Daniel Smolsky
 * Programming Project 4 - Time Interval Checker
 * February 28, 2017
 * Represents a time in either 12-hour or 24-hour format.
 * This class provides methods to set and validate time in 12-hour format with AM/PM notation,
 * and internally converts and stores the time in 24-hour format for comparison purposes.
 * It implements the Comparable interface to allow comparison between Time objects.
 */

package Project4;

public class Time implements Comparable<Time> {
    private int hours;
    private int minutes;
    private String meridian;
    private int hours24; // this variable will store hours converted to military format (24-hour clock)
    
    public Time (int hours, int minutes, String meridian) throws InvalidTime {
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
        validateTime();
        hoursTo24();
    }

    public Time (String timeString) throws InvalidTime {
        validateString(timeString);
        this.hours = Integer.parseInt(timeString.substring(0, 2));
        this.minutes = Integer.parseInt(timeString.substring(3, 5));
        this.meridian = timeString.substring(6, 8);
        validateTime();
        hoursTo24();
    }

    // calculate 24-hour format
    private void hoursTo24() {
        if ("PM".equals(meridian) && hours != 12) 
            hours24 = hours + 12;
        else if ("AM".equals(meridian) && hours == 12) 
            hours24 = 0;
        else 
            hours24 = hours;
    }

    // check input parameters match HH:MM AM
    private void validateTime () throws InvalidTime {
        if (hours < 0 || hours > 12) {
            throw new InvalidTime("Hours must be between 00 and 12");
        }
        if (minutes < 0 || minutes > 59) {
            throw new InvalidTime("Minutes must be between 00 and 59");
        }
        if (!meridian.equals("AM") && !meridian.equals("PM")) {
            throw new InvalidTime("Must specify AM or PM");
        }
    }

    // check input string matches "HH:MM AM"
    private void validateString(String inString) throws InvalidTime {
        if (inString.length() != 8) {
            throw new InvalidTime("Input string incorrect length");
        }
        try {
            Integer.parseInt(inString.substring(0, 2));
        } catch (NumberFormatException e) {
            throw new InvalidTime("Hours aren't numerical");
        }
        try {
            Integer.parseInt(inString.substring(3, 5));
        } catch (NumberFormatException e) {
            throw new InvalidTime("Minutes aren't numerical");
        }   
    }

    // returns 1 if parameter is greater than instance, -1 if less, 0 if equal
    @Override
    public int compareTo (Time otherTime) {
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

    // return string representation of the time
    @Override
    public String toString() {
        return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + meridian;
    }
}
