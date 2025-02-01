package joey.command;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private static final String ERROR_MESSAGE = """
            Please specify a task description, start date, and end date after 'event'
            For example: 'event concert /from 2025-02-01 /to 2025-02-02'""";


    public EventCommand(String userInput) throws CommandFormatException {
        if (userInput.length() <= 5) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }

        String[] parts = userInput.split(" /from ", 2);
        if (parts.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }

        this.description = parts[0].substring(6).trim();
        if (this.description.isEmpty()) {
            throw new CommandFormatException("Description cannot be empty.");
        }

        String[] eventDetails = parts[1].split(" /to ", 2);
        if (eventDetails.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }

        try {
            this.startDate = LocalDate.parse(eventDetails[0].trim());
            this.endDate = LocalDate.parse(eventDetails[1].trim());

            if (startDate.isAfter(endDate)) {
                throw new CommandFormatException("Start date cannot be after end date.");
            }
        } catch (DateTimeParseException e) {
            throw new CommandFormatException("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task event = new Event(this.description, this.startDate, this.endDate);
        tasks.add(event);
        storage.save(tasks);
        ui.showAddedTask(event, tasks);
    }
}