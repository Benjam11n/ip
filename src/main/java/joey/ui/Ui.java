package joey.ui;

import java.util.ArrayList;

import joey.enums.ToggleType;
import joey.task.Task;
import joey.task.TaskList;

/**
 * Handles all user interface operations for the task management application.
 * This class is responsible for displaying messages, task information, and
 * program status to the console.
 */

public class Ui {
    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Joey!\nWhat can I do for you?");
        this.showLine();
        return "Hello! I'm Joey!\nWhat can I do for you?";
    }

    /**
     * Displays confirmation of a task being added and shows the updated task list.
     *
     * @param task The task that was just added.
     * @param tasks The updated task list containing all tasks.
     */
    public String showAddedTask(Task task, TaskList tasks) {
        return "Added: " + task + "\n" + "\n" + tasks;
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param tasks The task list to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed to the user.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Displays confirmation that a task has been marked as completed/ incomplete.
     *
     * @param task The task that was marked as completed/ incomplete.
     * @param index The position of the task in the task list (1-based).
     */
    public String showToggledTask(Task task, int index, ToggleType type) {
        String message = type.getMessage() + " task " + index + ": " + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays confirmation of task deletion and shows the updated task list.
     *
     * @param task The task that was deleted.
     * @param tasks The updated task list after deletion.
     */
    public String showDeletedTask(Task task, TaskList tasks) {
        System.out.println("Removed task:\n  " + task
                + "\n" + "\n" + tasks);
        return "Removed task:\n  " + task
                + "\n" + "\n" + tasks.toString();
    }

    /**
     * Displays the all the matching tasks found in the current task list.
     *
     * @param tasks The matching tasks found.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("History is empty.");
            return "History is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            System.out.println(sb);
            return sb.toString();
        }
    }

    /**
     * Displays a separator line for visual clarity in the console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays the farewell message when the application closes.
     */
    public String showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }
}
