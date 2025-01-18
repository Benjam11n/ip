import enums.TaskType;

public class Event extends Task {
    private String startDate;
    private String endDate;
    private final TaskType type;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
