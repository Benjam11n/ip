package joey.command;

import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.task.Todo;
import joey.ui.Ui;

import java.io.IOException;

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