package joey.command;

import java.util.ArrayList;

import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand implements Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(this.query);
        ui.showMatchingTasks(matchingTasks);
    }
}
