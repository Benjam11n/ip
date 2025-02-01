package task;

import enums.TaskType;

public class Todo extends Task {
    private final TaskType type;

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
