package joey.task;

import joey.enums.TaskType;

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

    @Override
    public String getStorageFormat() {
        return String.format("E|%s|%b|%s|%s", getDescription(), isDone(), this.startDate, this.endDate);
    }

    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 5) {
            Event event = new Event(parts[1], parts[3], parts[4]);
            if (Boolean.parseBoolean(parts[2])) {
                event.markDone();
            }
            return event;
        }
        return null;
    }
}
