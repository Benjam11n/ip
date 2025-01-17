import java.util.ArrayList;

public class History {
    private ArrayList<Task> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void add(Task item) {
        this.history.add(item);
    }

    public boolean markTask(int index) {
        if (index >= 0 && index < history.size()) {
            this.history.get(index).markDone();
            return true;
        } else {
            return false;
        }
    }

    public boolean unMarkTask(int index) {
        if (index >= 0 && index < history.size()) {
            this.history.get(index).markUndone();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (history.isEmpty()) {
            return "History is empty.";
        } else {
            StringBuilder sb = new StringBuilder("History:\n");
            for (int i = 0; i < history.size(); i++) {
                sb.append((i + 1)).append(". ").append(history.get(i)).append("\n");
            }
            sb.append("Now you have ").append(this.history.size()).append(" tasks in the list.");
            return sb.toString();
        }
    }

}
