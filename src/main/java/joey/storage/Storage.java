package joey.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import joey.enums.TaskType;
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
    public static final int DESCRIPTION_INDEX = 1;
    public static final int STATUS_INDEX = 2;
    public static final int EVENT_START_DATE_INDEX = 3;
    public static final int EVENT_END_DATE_INDEX = 4;
    public static final int EVENT_PARTS_LENGTH = 5;
    public static final int DEADLINE_DATE_INDEX = 3;
    public static final int DEADLINE_PARTS_LENGTH = 4;
    public static final int TODO_PARTS_LENGTH = 3;
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("joey.txt");

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
    public void load(TaskList tasks) throws IOException, IllegalStateException {
        if (!shouldLoadTasks()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            loadTasksFromFile(reader, tasks);
        }
    }

    private boolean shouldLoadTasks() throws IOException {
        ensureDirectoryExists();
        return Files.exists(DATA_FILE);
    }

    private void loadTasksFromFile(BufferedReader reader, TaskList tasks) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!isValidTaskLine(line)) {
                continue;
            }

            parseAndAddTask(line, tasks);
        }
    }

    private boolean isValidTaskLine(String line) {
        String[] parts = line.split("\\|");
        return parts.length >= 2;
    }

    private void parseAndAddTask(String line, TaskList tasks) throws IllegalStateException {
        String taskType = getTaskType(line);
        Task task = createTaskByType(taskType, line);

        if (task != null) {
            tasks.add(task);
        }
    }

    private String getTaskType(String line) {
        return line.split("\\|")[0];
    }

    private Task createTaskByType(String taskTypeSymbol, String line) throws IllegalStateException {
        TaskType type;
        type = TaskType.fromSymbol(taskTypeSymbol);

        if (type == null) {
            throw new IllegalStateException("Unexpected task type symbol: " + taskTypeSymbol);
        }

        // In theory, this should be unreachable if fromSymbol is correct and enum is exhaustive.
        // As such, we do not need the default case.
        return switch (type) {
        case TODO -> Todo.createFromStorage(line);
        case DEADLINE -> Deadline.createFromStorage(line);
        case EVENT -> Event.createFromStorage(line);
        };
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
        writeTasksToFile(tasks);
    }

    private void writeTasksToFile(TaskList tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile()))) {
            for (Task task : tasks.getTasks()) {
                writeTask(writer, task);
            }
        }
    }

    private void writeTask(BufferedWriter writer, Task task) throws IOException {
        writer.write(task.getStorageFormat());
        writer.newLine();
    }
}
