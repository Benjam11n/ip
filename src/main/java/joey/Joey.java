package joey;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

import java.io.IOException;

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

    //    /**
    //     * Runs the main program loop.
    //     * Loads saved tasks, displays welcome message, and processes user commands until exit.
    //     * Each command is parsed, executed, and any errors are displayed to the user.
    //     */
    //    public void run() {
    //        Scanner scanner = new Scanner(System.in);
    //
    //        try {
    //            storage.load(tasks);
    //        } catch (IOException e) {
    //            ui.showError("Failed to load tasks: " + e.getMessage());
    //        }
    //
    //        ui.showWelcome();
    //        boolean isExit = false;
    //
    //        while (!isExit) {
    //            this.ui.showPrompt();
    //            String input = scanner.nextLine();
    //            try {
    //                Command command = Parser.parse(input);
    //                command.execute(tasks, ui, storage);
    //                isExit = command.isExit();
    //            } catch (CommandFormatException | IOException e) {
    //                ui.showError(e.getMessage());
    //            } finally {
    //                this.ui.showLine();
    //            }
    //        }
    //    }

    //    /**
    //     * Entry point of the application.
    //     * Creates a new Joey instance and starts the program.
    //     *
    //     * @param args Command line arguments (not used)
    //     */
    //    public static void main(String[] args) {
    //        new Joey().run();
    //    }
}
