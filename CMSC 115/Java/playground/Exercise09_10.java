package playground;

public class Exercise09_10 {
    public static void main(String[] args) {
        QuadraticEquation quad = new QuadraticEquation(1, 4, 3);
        System.out.println(quad.getDescriminant());
        System.out.println(quad.getRoot1());
        System.out.println(quad.getRoot2());
    }
}

class QuadraticEquation {
    double a;
    double b;
    double c;

    QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double getA() {
        return a;
    }

    double getB () {
        return b;
    }

    double getC() {
        return c;
    }

    double getDescriminant() {
        if (b * b - 4 * a * c <= 0)
            return 0;
        return b * b - 4 * a * c;
    }

    double getRoot1() {
        if (this.getDescriminant() == 0)
            return 0;
        return ((b * -1) + this.getDescriminant()) / (2 * a);
    }

    double getRoot2() {
        if (this.getDescriminant() == 0)
            return 0;
        return ((b * -1) - this.getDescriminant()) / (2 * a);
    }
}

