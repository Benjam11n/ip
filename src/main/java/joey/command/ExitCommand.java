package joey.command;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to end the current session of Joey.
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
