package joey.command;

import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.util.ArrayList;

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
