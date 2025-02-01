package joey.task;

import joey.enums.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    private final TaskType type;

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
        return String.format("D|%s|%b|%s", getDescription(), isDone(), this.by);
    }

    public static Task createFromStorage(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 4) {
            System.out.println(parts[3]);
            Deadline deadline = new Deadline(parts[1], LocalDate.parse(parts[3]));
            if (Boolean.parseBoolean(parts[2])) {
                deadline.markDone();
            }
            return deadline;
        }
        return null;
    }
}
