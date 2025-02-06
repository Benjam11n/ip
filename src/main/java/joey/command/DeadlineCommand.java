package joey.command;

import java.io.IOException;
import java.time.LocalDate;

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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandFormatException, IOException {
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks);
        return ui.showAddedTask(deadline, tasks);
    }
}
