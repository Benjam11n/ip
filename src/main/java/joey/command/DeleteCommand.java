package joey.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.exception.CommandFormatException;
import joey.exception.TaskIndexOutOfBoundsException;
import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to delete a task in the task list.
 * This command adds the task specified from the task list and removes it from storage.
 */
public class DeleteCommand implements Command {
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("delete", "del"));
    private int taskIndex;

    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    /**
     * Checks if the given command word matches any of the delete command identifiers.
     * This includes aliases like "delete", "del".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any delete command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            Task deletedTask = tasks.deleteTask(taskIndex);
            storage.save(tasks);
            return ui.showDeletedTask(deletedTask, tasks);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}
