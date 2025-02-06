package joey;

import java.io.IOException;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * joey.Main class for the Joey task management application.
 * Handles initialization, user interaction, and program flow.
 */
public class Joey {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Joey application instance.
     * Initializes the user interface, task list, and storage components.
     */
    public Joey() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage();
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public void loadTasks() throws IOException {
        storage.load(tasks);
    }
}
