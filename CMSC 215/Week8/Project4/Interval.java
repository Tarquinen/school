/**
 * Daniel Smolsky
 * Programming Project 4 - Time Interval Checker
 * February 28, 2017
 * Represents a generic interval between two points of a comparable type.
 * This class provides methods to check if a value or another interval is within this interval,
 * if it overlaps with another interval, or if another interval is a subinterval of this one.
 * 
 * @param <T> the type of the interval boundaries, which must be Comparable
 */

package Project4;

public class Interval <T extends Comparable<T>> {
    private T start;
    private T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    // returns true if the parameter is within the interval
    public boolean within(T inside) {
        return start.compareTo(inside) >= 0 && end.compareTo(inside) <= 0;
    }

    // returns true if paremeter is a subinterval of this instance
    public boolean subinterval(Interval<T> interval) {
        return this.within(interval.start) && this.within(interval.end);
    }

    // returns true if the intervals overlap
    public boolean overlaps(Interval<T> interval) {
        return (!this.within(interval.start) && this.within(interval.end)) ||
        (this.within(interval.start) && !this.within(interval.end));
    }
}
