package joey.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand implements Command {
    private static final String FIND_ERROR_MESSAGE = """
            Please specify a description after 'find'
            For example: 'find book'""";
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("find", "f"));
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Parses the user input for the query to search by
     *
     * @param commandArgs The user input
     * @return FindCommand after parsing the query
     * @throws CommandFormatException if the user input is not in the specified format
     */
    public static FindCommand parse(String commandArgs) throws CommandFormatException {
        if (commandArgs.isEmpty()) {
            throw new CommandFormatException(FIND_ERROR_MESSAGE);
        }
        return new FindCommand(commandArgs);
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
