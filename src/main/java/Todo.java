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
}
