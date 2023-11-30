package Week8;

public class Exercise09_01 {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(4, 40);
        Rectangle rectangle2 = new Rectangle(3.5, 35.9);
        System.out.println("The area of a rectangle with width " + rectangle1.width
        + " and height " + rectangle1.height + " is " + rectangle1.getArea());
        System.out.println("The perimeter of a rectangle is " + rectangle1.getPerimeter());
        System.out.println("The area of a rectangle with width " + rectangle2.width + " and height " 
        + rectangle2.height + " is " + rectangle2.getArea());
        System.out.println("The perimeter of a rectangle is " + rectangle2.getPerimeter());
    }
}

class Rectangle {
    double width;
    double height;

    Rectangle() {
        width = 1;
        height = 1;
    }

    Rectangle(double newWidth, double newHeight) {
        width = newWidth;
        height = newHeight;
    }

    double getArea() {
        return width * height;
    }

    double getPerimeter() {
        return 2 * width + 2 * height;
    }
}
