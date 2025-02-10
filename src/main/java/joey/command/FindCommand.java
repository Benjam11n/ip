package joey.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand implements Command {
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("find", "f"));
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Checks if the given command word matches any of the find command identifiers.
     * This includes aliases like "find", "f".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any find command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(this.query);
        return ui.showMatchingTasks(matchingTasks);
    }
}
