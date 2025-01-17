import exceptions.TaskIndexOutOfBoundsException;

import java.util.ArrayList;

public class History {
    private ArrayList<Task> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void add(Task item) {
        this.history.add(item);
    }

    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < history.size()) {
            this.history.get(index).markDone();
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    public void unMarkTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < history.size()) {
            this.history.get(index).markUndone();
        } else {
            throw new TaskIndexOutOfBoundsException();
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
            sb.append("\n");
            sb.append("Now you have ").append(this.history.size()).append(" tasks in the list.");
            return sb.toString();
        }
    }

}
