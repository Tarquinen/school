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
