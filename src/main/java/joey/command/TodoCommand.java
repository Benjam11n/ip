package joey.command;

import java.io.IOException;

import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.task.Todo;
import joey.ui.Ui;

/**
 * Represents a command to create a new todo task with a description.
 * This command adds the todo to the task list and persists it to storage.
 */
public class TodoCommand implements Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Todo(this.description);
        tasks.add(deadline);
        storage.save(tasks);
        ui.showAddedTask(deadline, tasks);
    }
}
