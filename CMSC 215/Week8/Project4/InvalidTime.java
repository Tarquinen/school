/**
 * Daniel Smolsky
 * Programming Project 4 - Time Interval Checker
 * February 28, 2017
 * Represents an exception for invalid time inputs in the Time Interval Checker application.
 * This class extends the Exception class and is used to indicate that an invalid time has been provided.
 * It is thrown by the Time class when the time does not meet the specified format or value constraints.
 */

package Project4;

public class InvalidTime extends Exception {
    private String message;

    public InvalidTime(String message) {
        super(message);
        this.message = message;
    }
}
