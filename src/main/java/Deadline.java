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

    @Override
    public String getStorageFormat() {
        return String.format("D|%s|%b|%s", getDescription(), isDone(), this.deadline);
    }

    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 4) {
            System.out.println(parts[3]);
            Deadline deadline = new Deadline(parts[1], parts[3]);
            if (Boolean.parseBoolean(parts[2])) {
                deadline.markDone();
            }
            return deadline;
        }
        return null;
    }
}
