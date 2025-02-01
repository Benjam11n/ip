package joey.task;

public abstract class Task {
    private boolean isDone;
    private final String description;

    public Task(String description) {
        this.description  = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    // For joey.storage purposes
    public abstract String getStorageFormat();
    public static Task createFromStorage(String data) {
        // Provide implementation in a subclass
        throw new UnsupportedOperationException("Subclass must implement this method.");
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

