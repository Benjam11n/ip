package joey.task;

/**
 * Represents an abstract base class for all types of tasks in the application.
 * Provides common functionality for task management including completion status
 * and description handling.
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    /**
     * Constructs a new task with the given description.
     * Tasks are initialized as not done.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task suitable for storage.
     * Each subclass must implement this method to provide its specific storage format.
     *
     * @return A string representation of the task for storage purposes
     */
    public abstract String getStorageFormat();

    /**
     * Creates a task instance from its storage format string representation.
     * This method must be implemented by subclasses to handle their specific storage format.
     *
     * @param data The string representation of the task from storage
     * @return A new Task instance created from the storage data
     * @throws UnsupportedOperationException if called on the base Task class
     */
    public static Task createFromStorage(String data) {
        throw new UnsupportedOperationException("Subclass must implement this method.");
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}