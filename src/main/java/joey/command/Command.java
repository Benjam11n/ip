package joey.command;

import java.io.IOException;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command that can be executed by the application.
 * All commands must implement this interface to define their execution behavior.
 */
public interface Command {
    /**
     * Executes the command, performing its specific action on the task list.
     *
     * @param tasks The task list to perform operations on
     * @param ui The UI to display messages to the user
     * @param storage The storage to save any changes
     * @throws CommandFormatException if the command format is invalid during execution
     * @throws IOException if there is an error accessing storage
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException, IOException;

    /**
     * Checks if this command should terminate the application.
     * Default implementation returns false. Commands that should exit the program
     * should override this method to return true.
     *
     * @return true if the program should exit after this command, false otherwise
     */
    default boolean isExit() {
        return false;
    }
}
