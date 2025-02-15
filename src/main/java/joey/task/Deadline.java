package joey.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import joey.enums.TaskType;
import joey.enums.ToggleType;
import joey.storage.Storage;

/**
 * Represents a task with a specific deadline date.
 * Extends the base Task class with deadline-specific functionality.
 */
public class Deadline extends Task {
    private LocalDate by;
    private final TaskType type;

    /**
     * Constructs a new deadline task.
     *
     * @param description The description of the deadline task
     * @param by The date by which the task must be completed
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = this.by.format(formatter);

        return "[" + this.type + "]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String getStorageFormat() {
        return String.format("%s|%s|%b|%s", TaskType.DEADLINE, getDescription(), isDone(), this.by);
    }

    /**
     * Creates a new Deadline task from its storage format string representation.
     *
     * @param data The string representation of the deadline task from storage
     *             Expected format: "D|description|isDone|date"
     * @return A new Deadline task instance, or null if the data format is invalid
     */
    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (!(parts.length == Storage.DEADLINE_PARTS_LENGTH)) {
            return null;
        }

        Deadline deadline = createDeadlineFromParts(parts);
        applyCompletionStatus(deadline, parts[Storage.STATUS_INDEX]);
        return deadline;
    }

    private static Deadline createDeadlineFromParts(String[] parts) {
        return new Deadline(parts[Storage.DESCRIPTION_INDEX],
                LocalDate.parse(parts[Storage.DEADLINE_DATE_INDEX]));
    }

    private static void applyCompletionStatus(Deadline deadline, String status) {
        if (Boolean.parseBoolean(status)) {
            deadline.toggle(ToggleType.MARK);
        }
    }
}
