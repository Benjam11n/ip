package joey.ui;

import joey.task.Task;
import joey.task.TaskList;

import java.util.ArrayList;

/**
 * Handles all user interface operations for the task management application.
 * This class is responsible for displaying messages, task information, and
 * program status to the console.
 */

public class Ui {
    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Joey!\nWhat can I do for you?");
        this.showLine();
    }

    /**
     * Displays the user input prompt "You: " to the console.
     */
    public void showPrompt() {
        System.out.print("You: ");
    }

    /**
     * Displays confirmation of a task being added and shows the updated task list.
     *
     * @param task The task that was just added.
     * @param tasks The updated task list containing all tasks.
     */
    public void showAddedTask(Task task, TaskList tasks) {
        System.out.println("Added: " + task + "\n" + "\n" + tasks);
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param tasks The task list to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed to the user.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays confirmation that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     * @param index The position of the task in the task list (1-based).
     */
    public void showMarkedTask(Task task, int index) {
        System.out.println("Marked task " + index + ": " + task);
    }

    /**
     * Displays confirmation that a task has been marked as incomplete.
     *
     * @param task The task that was marked as incomplete.
     * @param index The position of the task in the task list (1-based).
     */
    public void showUnmarkedTask(Task task, int index) {
        System.out.println("Unmarked task " + index + ": " + task);
    }

    /**
     * Displays confirmation of task deletion and shows the updated task list.
     *
     * @param task The task that was deleted.
     * @param tasks The updated task list after deletion.
     */
    public void showDeletedTask(Task task, TaskList tasks) {
        System.out.println("Removed task:\n  " + task +
                "\n" + "\n" + tasks);
    }

    /**
     * Displays the all the matching tasks found in the current task list.
     *
     * @param tasks The matching tasks found.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i));
            }
            System.out.println(sb);
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
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}