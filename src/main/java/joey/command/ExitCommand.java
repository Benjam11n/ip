package joey.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to end the current session of Joey.
 */
public class ExitCommand implements Command {
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("bye", "b"));
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    /**
     * Checks if the given command word matches any of the exit command identifiers.
     * This includes aliases like "bye", "b".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any exit command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
