import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("duke.txt");

    private static void ensureDirectoryExists() throws IOException {
        // Create data directory if it doesn't exist
        if (!Files.exists(DATA_DIR)) {
            Files.createDirectories(DATA_DIR);
        }
    }

    public static void readFile(History history) throws IOException {
        ensureDirectoryExists();

        // If file doesn't exist, just return - starting with empty history
        if (!Files.exists(DATA_FILE)) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    Task task = null;
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
                    }
                    if (task != null) {
                        history.add(task);
                    }
                }
            }
        }
    }

    public static void writeToFile(History history) throws IOException {
        ensureDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile()))) {
            for (Task task : history.getTasks()) {
                writer.write(task.getStorageFormat());
                writer.newLine();
            }
        }
    }
}
