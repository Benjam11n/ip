public class Task {
    private boolean done = false;
    private String name;

    public Task(String name) {
        this.name  = name;
        this.done = false;
    }

    public void done() {
        this.done = true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
