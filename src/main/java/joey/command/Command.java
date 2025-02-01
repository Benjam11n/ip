package joey.command;


import joey.exception.CommandFormatException;
import joey.task.TaskList;
import joey.ui.Ui;
import joey.storage.Storage;

import java.io.IOException;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException, IOException;
    public default boolean isExit() {
        return false;
    }
}