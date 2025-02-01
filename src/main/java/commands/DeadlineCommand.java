package commands;

import exceptions.CommandFormatException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeadlineCommand implements Command {
    private String description;
    private String by;
    private static final String ERROR_MESSAGE = """
            Please specify a task description and a deadline date after 'deadline'.
            For example: 'deadline return book /by Sunday'""";


    public DeadlineCommand(String userInput) throws CommandFormatException {
        if (userInput.length() <= 9) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
        if (this.description.isEmpty() || this.by.isEmpty()) {
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