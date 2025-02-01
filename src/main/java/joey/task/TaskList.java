package joey.task;

import joey.exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task item) {
        this.tasks.add(item);
    }

    public ArrayList<Task> getTasks()  {
        return this.tasks;
    }

    public Task getTask(Integer index)  {
        return this.tasks.get(index);
    }

    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            this.tasks.get(index).markDone();
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    public void unMarkTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            this.tasks.get(index).markUndone();
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            return task;
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "History is empty.";
        } else {
            StringBuilder sb = new StringBuilder("History:\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
            }
            sb.append("\n");
            sb.append("You have ").append(this.tasks.size()).append(" tasks in the list.");
            return sb.toString();
        }
    }

}
