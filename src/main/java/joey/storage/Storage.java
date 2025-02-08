package joey.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import joey.task.Deadline;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.task.Todo;

/**
 * Handles the persistence of tasks to and from file storage.
 * This class manages saving and loading tasks from a text file
 * located in the 'data' directory.
 */
public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("duke.txt");

    /**
     * Creates the data directory if it doesn't already exist.
     *
     * @throws IOException if there is an error creating the directory
     */
    private static void ensureDirectoryExists() throws IOException {
        if (!Files.exists(DATA_DIR)) {
            Files.createDirectories(DATA_DIR);
        }
    }

    /**
     * Loads tasks from the storage file into the provided task list.
     * If the storage file doesn't exist, the task list remains empty.
     * Each line in the file represents one task in the format specific
     * to its type (Todo, Deadline, or Event).
     *
     * @param tasks The task list to load the tasks into
     * @throws IOException if there is an error reading from the file
     */
    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public void load(TaskList tasks) throws IOException, IllegalStateException {
        ensureDirectoryExists();

        if (!Files.exists(DATA_FILE)) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    Task task;
                    switch (parts[0]) {
                    case "T":
                        task = Todo.createFromStorage(line);
                        break;
                    case "D":
                        task = Deadline.createFromStorage(line);
                        break;
                    case "E":
                        task = Event.createFromStorage(line);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + parts[0]);
                    }

                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        }
    }

    /**
     * Saves all tasks from the provided task list to the storage file.
     * Creates the storage file if it doesn't exist, or overwrites it if it does.
     * Each task is saved in its type-specific format using getStorageFormat().
     *
     * @param tasks The task list containing the tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws IOException {
        ensureDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile()))) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.getStorageFormat());
                writer.newLine();
            }
        }
    }
}
