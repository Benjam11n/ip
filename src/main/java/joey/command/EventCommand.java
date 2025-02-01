package joey.command;

import joey.storage.Storage;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class EventCommand implements Command {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;


    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task event = new Event(this.description, this.startDate, this.endDate);
        tasks.add(event);
        storage.save(tasks);
        ui.showAddedTask(event, tasks);
    }
}