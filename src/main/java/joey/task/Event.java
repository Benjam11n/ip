package joey.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import joey.enums.TaskType;
import joey.enums.ToggleType;
import joey.storage.Storage;

/**
 * Represents a task with a start and end date.
 * Extends the base Task class with event-specific functionality.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private final TaskType type;

    /**
     * Constructs a new event task.
     *
     * @param description The description of the event
     * @param startDate The date when the event starts
     * @param endDate The date when the event ends
     */
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
        return String.format("%s|%s|%b|%s|%s", TaskType.EVENT,
                getDescription(), isDone(), this.startDate, this.endDate);
    }

    /**
     * Creates a new Event task from its storage format string representation.
     *
     * @param data The string representation of the event task from storage
     *             Expected format: "E|description|isDone|startDate|endDate"
     * @return A new Event task instance, or null if the data format is invalid
     */
    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (!(parts.length == Storage.EVENT_PARTS_LENGTH)) {
            return null;
        }

        Event event = createEventFromParts(parts);
        applyCompletionStatus(event, parts[Storage.STATUS_INDEX]);

        return event;
    }

    private static Event createEventFromParts(String[] parts) {
        return new Event(parts[Storage.DESCRIPTION_INDEX],
                LocalDate.parse(parts[Storage.EVENT_START_DATE_INDEX]),
                LocalDate.parse(parts[Storage.EVENT_END_DATE_INDEX]));
    }

    private static void applyCompletionStatus(Event event, String status) {
        if (Boolean.parseBoolean(status)) {
            event.toggle(ToggleType.MARK);
        }
    }
}
