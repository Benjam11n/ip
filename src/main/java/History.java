import java.util.ArrayList;

public class History {
    private ArrayList<Task> items;

    public History() {
        this.items = new ArrayList<>();
    }

    public void add(Task item) {
        this.items.add(item);
    }

    public boolean markTask(int index) {
        if (index >= 0 && index < items.size()) {
            this.items.get(index).markDone();
            return true;
        } else {
            return false;
        }
    }

    public boolean unMarkTask(int index) {
        if (index >= 0 && index < items.size()) {
            this.items.get(index).markUndone();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "History is empty.";
        } else {
            StringBuilder sb = new StringBuilder("History:\n");
            for (int i = 0; i < items.size(); i++) {
                sb.append((i + 1)).append(". ").append(items.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

}
