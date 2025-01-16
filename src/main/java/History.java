import java.util.ArrayList;

public class History {
    ArrayList<Task> items;

    public History () {
        this.items = new ArrayList<>();
    }

    public void add(Task item) {
        this.items.add(item);
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
