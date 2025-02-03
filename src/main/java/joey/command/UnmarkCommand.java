package joey.command;

import java.io.IOException;

import joey.exception.CommandFormatException;
import joey.exception.TaskIndexOutOfBoundsException;
import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to mark a task in the task list as undone.
 */
public class UnmarkCommand implements Command {
    private int taskIndex;
    public UnmarkCommand(int index) {
        this.taskIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            tasks.unMarkTask(taskIndex);
            storage.save(tasks);
            ui.showUnmarkedTask(tasks.getTask(taskIndex), taskIndex + 1);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}
