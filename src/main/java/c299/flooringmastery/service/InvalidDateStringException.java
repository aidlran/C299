package c299.flooringmastery.service;

public class InvalidDateStringException extends Exception {

    public InvalidDateStringException(String message) {
        super(message);
    }

    public InvalidDateStringException(String message, Throwable cause) {
        super(message, cause);
    }
}
