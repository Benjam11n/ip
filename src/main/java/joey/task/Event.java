package joey.task;

import joey.enums.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private final TaskType type;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskType.EVENT;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedStartDate = this.startDate.format(formatter);
        String formattedEndDate = this.endDate.format(formatter);

        return "[" + this.type + "]" + super.toString() + " (from: "
                + formattedStartDate + " to: " + formattedEndDate + ")";
    }

    @Override
    public String getStorageFormat() {
        return String.format("E|%s|%b|%s|%s", getDescription(), isDone(), this.startDate, this.endDate);
    }

    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 5) {
            Event event = new Event(parts[1], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
            if (Boolean.parseBoolean(parts[2])) {
                event.markDone();
            }
            return event;
        }
        return null;
    }
}
