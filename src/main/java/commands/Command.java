package commands;


import exceptions.CommandFormatException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException, IOException;
    public default boolean isExit() {
        return false;
    }
}