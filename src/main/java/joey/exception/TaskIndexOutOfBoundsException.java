package joey.exception;

public class TaskIndexOutOfBoundsException extends Exception {
    private static final String ERROR_MESSAGE = "joey.task.Task Index out of range";

    public TaskIndexOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }

    public TaskIndexOutOfBoundsException(String message) {
        super(message);
    }
}