package joey.task;

import joey.enums.TaskType;
import joey.enums.ToggleType;
import joey.storage.Storage;

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
        return String.format("%s|%s|%b", TaskType.TODO, getDescription(), isDone());
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
        if (!(parts.length == Storage.TODO_PARTS_LENGTH)) {
            return null;
        }

        Todo todo = createTodoFromParts(parts);
        applyCompletionStatus(todo, parts[Storage.STATUS_INDEX]);

        return todo;
    }

    private static Todo createTodoFromParts(String[] parts) {
        return new Todo(parts[Storage.DESCRIPTION_INDEX]);
    }

    private static void applyCompletionStatus(Todo todo, String status) {
        if (Boolean.parseBoolean(status)) {
            todo.toggle(ToggleType.MARK);
        }
    }
}
