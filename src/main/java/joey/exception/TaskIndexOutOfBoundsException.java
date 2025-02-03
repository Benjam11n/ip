package joey.exception;

/**
 * Exception thrown when attempting to access a task at an invalid index.
 * This occurs when trying to mark, unmark, or delete a task using an index
 * that is out of range of the current task list.
 */
public class TaskIndexOutOfBoundsException extends Exception {
    private static final String ERROR_MESSAGE = "Task Index out of range";

    /**
     * Constructs a new TaskIndexOutOfBoundsException with a predefined error message.
     */
    public TaskIndexOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }
}
