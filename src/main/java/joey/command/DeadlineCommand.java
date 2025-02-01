package joey.command;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Deadline;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private String description;
    private LocalDate by;
    private static final String ERROR_MESSAGE = """
            Please specify a task description and a deadline date after 'deadline'.
            For example: 'deadline return book /by 2025-02-01'""";


    public DeadlineCommand(String userInput) throws CommandFormatException {
        if (userInput.length() <= 9) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        this.description = parts[0].trim();

        try {
            this.by = LocalDate.parse(parts[1].trim());
        } catch (DateTimeParseException e) {
            throw new CommandFormatException("Invalid date format. Use YYYY-MM-DD.");
        }
        if (this.description.isEmpty()) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks);
        ui.showAddedTask(deadline, tasks);
    }
}