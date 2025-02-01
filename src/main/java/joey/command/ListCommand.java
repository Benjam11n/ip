package joey.command;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
