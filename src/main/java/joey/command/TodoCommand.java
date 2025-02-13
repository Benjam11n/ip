package joey.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Task;
import joey.task.TaskList;
import joey.task.Todo;
import joey.ui.Ui;

/**
 * Represents a command to create a new todo task with a description.
 * This command adds the todo to the task list and persists it to storage.
 */
public class TodoCommand implements Command {
    private static final String TODO_ERROR_MESSAGE = """
            Please specify a task description after 'todo'.
            For example: 'todo borrow book'""";
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("todo", "t"));
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Parses the user input for the description of the todo
     *
     * @param commandArgs The user input
     * @return TodoCommand after parsing the relevant details
     */
    public static TodoCommand parse(String commandArgs) throws CommandFormatException {
        if (commandArgs.trim().isEmpty()) {
            throw new CommandFormatException(TODO_ERROR_MESSAGE);
        }
        return new TodoCommand(commandArgs.trim());
    }


    /**
     * Checks if the given command word matches any of the todo command identifiers.
     * This includes aliases like "todo", "t".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any todo command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Todo(this.description);
        tasks.add(deadline);
        storage.save(tasks);
        return ui.showAddedTask(deadline, tasks);
    }
}
