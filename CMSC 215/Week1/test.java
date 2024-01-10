// package Week1;

public class test {
    private int[] elements;
    private int size;
    public static final int DEFAULT_CAPACITY = 16;

    /** Construct a stack with the default capacity 16 */
    public test() {
        this(DEFAULT_CAPACITY);
    }

    /** Construct a stack with the specified capacity */
    public test(int capacity) {
        elements = new int[capacity];
    }

    /** Push a new integer into the top of the stack */
    public void push(int value) {
        if (size >= elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }

        elements[size++] = value;
    }

    /** Return and remove the top element from the stack */
    public int pop() {
        return elements[--size];
    }

    /** Return the top element from the stack */
    public int peek() {
        return elements[size - 1];
    }

    /** Test whether the stack is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of elements in the stack */
    public int getSize() {
        return size;
    }

    public void printStack() {
        for (int i = 0; i < size; i++) {
            if (i < size - 1)
                System.out.print(elements[i] + ", ");
            else
                System.out.println(elements[i]);
        }
    }


    public static void main(String[] args) {
        test test1 = new test();
        test1.push(4);
        test1.push(5);
        test1.push(-5);
        test1.push(423);
        
        StringBuilder strBuilder = new StringBuilder("abcdefg");
        System.out.println(strBuilder.charAt(strBuilder.length() - 2));
        System.out.println(strBuilder);

    }
}