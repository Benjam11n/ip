package joey.command;
import joey.storage.Storage;
import joey.task.Deadline;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private String description;
    private LocalDate by;


    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        storage.save(tasks);
        ui.showAddedTask(deadline, tasks);
    }
}