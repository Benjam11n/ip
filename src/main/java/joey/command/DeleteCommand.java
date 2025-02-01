package joey.command;

import joey.exception.CommandFormatException;
import joey.exception.TaskIndexOutOfBoundsException;
import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;

public class DeleteCommand implements Command {
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            Task deletedTask = tasks.deleteTask(taskIndex);
            storage.save(tasks);
            ui.showDeletedTask(deletedTask, tasks);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}