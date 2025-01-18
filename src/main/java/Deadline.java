import enums.TaskType;

public class Deadline extends Task {
    private String deadline;
    private final TaskType type;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
