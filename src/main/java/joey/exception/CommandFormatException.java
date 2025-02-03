package joey.exception;

/**
 * Exception thrown when a command's format is invalid.
 * This includes cases such as missing arguments, invalid date formats,
 * or incorrect command syntax.
 */
public class CommandFormatException extends Exception {
    /**
     * Constructs a new CommandFormatException with the specified error message.
     *
     * @param message The detailed error message explaining why the command format was invalid
     */
    public CommandFormatException(String message) {
        super(message);
    }
}
