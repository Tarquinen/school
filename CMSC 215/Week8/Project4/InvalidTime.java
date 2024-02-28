package Project4;

public class InvalidTime extends Exception {
    private String message;

    public InvalidTime(String message) {
        super(message);
        this.message = message;
    }
}
