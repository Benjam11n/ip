package joey.command;

import java.io.IOException;

import joey.exception.CommandFormatException;
import joey.exception.TaskIndexOutOfBoundsException;
import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to mark a task in the task list as done.
 */
public class MarkCommand implements Command {
    private int taskIndex;
    public MarkCommand(int index) {
        this.taskIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            tasks.markTask(taskIndex);
            storage.save(tasks);
            ui.showMarkedTask(tasks.getTask(taskIndex), taskIndex + 1); // 1-based index for UI
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}
