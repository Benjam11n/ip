package joey.task;

import java.util.ArrayList;

import joey.enums.ToggleType;
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
     * Marks a task as completed/ incomplete at the specified index.
     *
     * @param index The index of the task to mark as completed
     * @param type The type of command (UNMARK / MARK)
     * @throws TaskIndexOutOfBoundsException if the index is out of range
     */
    public void toggleTask(int index, ToggleType type) throws TaskIndexOutOfBoundsException {
        if (index >= 0 && index < this.tasks.size()) {
            this.tasks.get(index).toggle(type);
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

    /**
     * Checks if the task list is empty
     *
     * @return The boolean stating whether the task list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size of the tasklist
     *
     * @return The size of the tasklist
     */
    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Task task : this.tasks) {
                sb.append(task).append("\n");
            }
            return sb.toString();
        }
    }
}
