package joey.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    private static final Set<String> MARK_IDENTIFIERS = new HashSet<>(Arrays.asList(
            "mark", "m", "done", "complete"
    ));

    private static final Set<String> UNMARK_IDENTIFIERS = new HashSet<>(Arrays.asList(
            "unmark", "un", "undo", "incomplete"
    ));
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

    /**
     * Checks if the given command word matches any of the mark command identifiers.
     * This includes aliases like "mark", "m", "done", "complete".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any mark command identifier, false otherwise
     */
    public static boolean isMarkCommand(String commandWord) {
        return MARK_IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    /**
     * Checks if the given command word matches any of the unmark command identifiers.
     * This includes aliases like "unmark", "un", "undo", "incomplete".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any unmark command identifier, false otherwise
     */
    public static boolean isUnmarkCommand(String commandWord) {
        return UNMARK_IDENTIFIERS.contains(commandWord.toLowerCase());
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
