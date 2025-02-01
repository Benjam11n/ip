package joey.task;

import joey.enums.TaskType;

/**
 * Represents a basic todo task without a specific date.
 * Extends the base Task class with todo-specific functionality.
 */
public class Todo extends Task {
    private final TaskType type;

    /**
     * Constructs a new todo task.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }

    @Override
    public String getStorageFormat() {
        return String.format("T|%s|%b", getDescription(), isDone());
    }

    /**
     * Creates a new Todo task from its storage format string representation.
     *
     * @param data The string representation of the todo task from storage
     *             Expected format: "T|description|isDone"
     * @return A new Todo task instance, or null if the data format is invalid
     */
    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 3) {
            Todo todo = new Todo(parts[1]);
            if (Boolean.parseBoolean(parts[2])) {
                todo.markDone();
            }
            return todo;
        }
        return null;
    }
}