package joey.command;

import java.io.IOException;
import java.time.LocalDate;

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

    /**
     * Executes the deadline command by creating and adding a new deadline to the task list.
     * The new deadline is saved to storage and confirmation is shown to the user.
     *
     * @param tasks The task list to add the deadline to
     * @param ui The UI to display the confirmation message
     * @param storage The storage to save the updated task list
     * @throws IOException if there is an error saving to storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks);
        return ui.showAddedTask(deadline, tasks);
    }
}
