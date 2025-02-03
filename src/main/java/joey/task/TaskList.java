package joey.task;

import java.util.ArrayList;

import joey.exception.TaskIndexOutOfBoundsException;

/**
 * Manages a collection of tasks and provides operations for task manipulation.
 * This class handles adding, marking, unmarking, and deleting tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param item The task to be added
     */
    public void add(Task item) {
        this.tasks.add(item);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a specific task from the list by its index.
     *
     * @param index The index of the task to retrieve
     * @return The task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(Integer index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a task as completed at the specified index.
     *
     * @param index The index of the task to mark as completed
     * @throws TaskIndexOutOfBoundsException if the index is out of range
     */
    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            this.tasks.get(index).markDone();
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Marks a task as not completed at the specified index.
     *
     * @param index The index of the task to mark as not completed
     * @throws TaskIndexOutOfBoundsException if the index is out of range
     */
    public void unMarkTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            this.tasks.get(index).markUndone();
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Removes and returns a task at the specified index.
     *
     * @param index The index of the task to delete
     * @return The deleted task
     * @throws TaskIndexOutOfBoundsException if the index is out of range
     */
    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            return task;
        } else {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Finds and returns tasks that matches the search query
     *
     * @param query The search query
     * @return The tasks matching the search query
     */
    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
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
