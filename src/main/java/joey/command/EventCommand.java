package joey.command;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;

public class EventCommand implements Command {
    private String description;
    private String startDate;
    private String endDate;
    private static final String ERROR_MESSAGE = """
            Please specify a task description, start date, and end date after 'event'
            For example: 'event project meeting /from Mon 2pm /to 4pm'""";


    public EventCommand(String userInput) throws CommandFormatException {
        if (userInput.length() <= 5) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        String[] parts = userInput.split(" /from", 2);
        if (parts.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }

        this.description = parts[0].substring(6).trim();
        String[] eventDetails = parts[1].split(" /to", 2);
        if (eventDetails.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }

        this.startDate = eventDetails[0].trim();
        this.endDate = eventDetails[1].trim();

        if (this.description.isEmpty() || this.startDate.isEmpty() || this.endDate.isEmpty()) {
            throw new CommandFormatException("Description, start date and end date cannot be empty.");
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