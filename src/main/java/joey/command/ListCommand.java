package joey.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to list all the tasks in the task list.
 */
public class ListCommand implements Command {
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("list", "l"));

    /**
     * Checks if the given command word matches any of the list command identifiers.
     * This includes aliases like "list", "l".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any list command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
