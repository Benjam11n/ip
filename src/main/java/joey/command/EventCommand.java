package joey.command;

import java.io.IOException;
import java.time.LocalDate;

import joey.storage.Storage;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to create a new event task with a start and end date.
 * This command adds the event to the task list and persists it to storage.
 */
public class EventCommand implements Command {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs a new event command.
     *
     * @param description The description of the event
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     */
    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Executes the event command by creating and adding a new event to the task list.
     * The new event is saved to storage and confirmation is shown to the user.
     *
     * @param tasks The task list to add the event to
     * @param ui The UI to display the confirmation message
     * @param storage The storage to save the updated task list
     * @return The confirmation message indicating the deadline was added
     * @throws IOException if there is an error saving to storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task event = new Event(this.description, this.startDate, this.endDate);
        tasks.add(event);
        storage.save(tasks);
        return ui.showAddedTask(event, tasks);
    }
}
