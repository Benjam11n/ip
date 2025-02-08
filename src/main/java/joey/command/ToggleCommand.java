package joey.command;

import java.io.IOException;

import joey.enums.ToggleType;
import joey.exception.CommandFormatException;
import joey.exception.TaskIndexOutOfBoundsException;
import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to mark a task in the task list as complete/ incomplete.
 */
public class ToggleCommand implements Command {
    private int taskIndex;
    private ToggleType type;

    /**
     * Creates a new ToggleCommand to mark or unmark a task.
     *
     * @param index The index of the task to toggle (0-based)
     * @param type The type of toggle operation (MARK or UNMARK)
     */
    public ToggleCommand(int index, ToggleType type) {
        this.taskIndex = index;
        this.type = type;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            tasks.toggleTask(taskIndex, this.type);
            storage.save(tasks);

            return ui.showToggledTask(tasks.getTask(taskIndex), taskIndex + 1, type);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}
