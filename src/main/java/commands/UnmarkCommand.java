package commands;

import exceptions.CommandFormatException;
import exceptions.TaskIndexOutOfBoundsException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends IndexedCommand {
    public UnmarkCommand(String userInput) throws CommandFormatException {
        super("unmark", userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            tasks.unMarkTask(taskIndex);
            storage.save(tasks);
            ui.showUnmarkedTask(tasks.getTask(taskIndex), taskIndex + 1);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}