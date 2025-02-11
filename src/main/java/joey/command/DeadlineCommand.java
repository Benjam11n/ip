package joey.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.Deadline;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to create a new deadline task with a due date.
 * This command adds the deadline to the task list and persists it to storage.
 */
public class DeadlineCommand implements Command {
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("deadline", "d"));
    private String description;
    private LocalDate by;

    /**
     * Constructs a new deadline command.
     *
     * @param description The description of the deadline task
     * @param by The due date of the deadline
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Checks if the given command word matches any of the deadline command identifiers.
     * This includes aliases like "deadline", "d".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any deadline command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException, IOException {
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks);
        return ui.showAddedTask(deadline, tasks);
    }
}
