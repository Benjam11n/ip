package exceptions;

public class CommandFormatException extends Exception {
    public CommandFormatException(String message) {
        super(message);
    }
}