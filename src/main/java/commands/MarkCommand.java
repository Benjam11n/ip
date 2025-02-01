package commands;

import exceptions.CommandFormatException;
import exceptions.TaskIndexOutOfBoundsException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class MarkCommand extends IndexedCommand {
    public MarkCommand(String userInput) throws CommandFormatException {
        super("mark", userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            tasks.markTask(taskIndex);
            storage.save(tasks);
            ui.showMarkedTask(tasks.getTask(taskIndex), taskIndex + 1); // 1-based index for UI
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}