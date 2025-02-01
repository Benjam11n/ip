package joey.command;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.task.Todo;
import joey.ui.Ui;

import java.io.IOException;

public class TodoCommand implements Command {
    private String description;
    private static final String ERROR_MESSAGE = """
            Please specify a task description after 'todo'.
            For example: 'todo borrow book'""";

    public TodoCommand(String userInput) throws CommandFormatException {
        if (userInput.length() <= 5) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
        this.description = userInput.substring(5).trim();
        if (this.description.isEmpty()) {
            throw new CommandFormatException(ERROR_MESSAGE);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Todo(this.description);
        tasks.add(deadline);
        storage.save(tasks);
        ui.showAddedTask(deadline, tasks);
    }
}