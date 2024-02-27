public class Interval <T extends Comparable<T>> {
    private T start;
    private T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;    
    }

    public boolean within(T inside) {
        return inside.compareTo(start) >= 0 && inside.compareTo(end) <= 0;
    }

    public boolean subinterval(Interval<T> interval) {
        return this.within(interval.start) && this.within(interval.end);
    }

    public boolean overlaps(Interval<T> interval) {
        return (!this.within(interval.start) && this.within(interval.end)) ||
        (this.within(interval.start) && !this.within(interval.end));
    }
}
