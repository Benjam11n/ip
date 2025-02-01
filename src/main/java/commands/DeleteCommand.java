package commands;

import exceptions.CommandFormatException;
import exceptions.TaskIndexOutOfBoundsException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends IndexedCommand {
    public DeleteCommand(String userInput) throws CommandFormatException {
        super("delete", userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException {
        try {
            Task deletedTask = tasks.deleteTask(taskIndex);
            storage.save(tasks);
            ui.showDeletedTask(deletedTask, tasks);
        } catch (TaskIndexOutOfBoundsException e) {
            throw new CommandFormatException("Task " + (taskIndex + 1) + " does not exist.");
        } catch (IOException e) {
            throw new CommandFormatException("Failed to save tasks: " + e.getMessage());
        }
    }
}