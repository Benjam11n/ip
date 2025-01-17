package exceptions;

public class CommandFormatException extends Exception {
    public CommandFormatException() {
        super("Invalid command format");
    }

    public CommandFormatException(String message) {
        super(message);
    }
}