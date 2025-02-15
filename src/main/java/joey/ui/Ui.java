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
        return "Hey there! 👋 I'm Joey, your friendly task assistant!\n"
                + "Ready to get things done? Just tell me what's up!";
    }

    /**
     * Displays confirmation of a task being added and shows the updated task list.
     *
     * @param task The task that was just added.
     * @param tasks The updated task list containing all tasks.
     */
    public String showAddedTask(Task task, TaskList tasks) {
        return "Alright! I've added this task for ya:\n"
                + "  -> " + task + "\n"
                + "\n"
                + "Current tasks in your list:\n"
                + tasks; // TaskList's toString() should handle the list display nicely
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param tasks The task list to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is currently empty! Time to add some tasks, maybe? 😉";
        } else {
            StringBuilder sb = new StringBuilder("History:\n");
            String[] taskStrings = tasks.toString().split("\n");
            for (int i = 0; i < taskStrings.length; i++) {
                sb.append((i + 1)).append(". ").append(taskStrings[i]).append("\n");
            }
            sb.append("\n");
            sb.append("You have ").append(tasks.getSize()).append(" tasks in the list.");
            return sb.toString();
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed to the user.
     */
    public String showError(String message) {
        return "Oops! 😬 Something went wrong...\n"
                + ">>> " + message + "\n"
                + "Let's try that again, shall we?";
    }

    /**
     * Displays confirmation that a task has been marked as completed/ incomplete.
     *
     * @param task The task that was marked as completed/ incomplete.
     * @param index The position of the task in the task list (1-based).
     */
    public String showToggledTask(Task task, int index, ToggleType type) {
        String message = type.getMessage() + " task number " + index + "!\n"
                + "  -> " + task + "\n"
                + (type == ToggleType.MARK ? "Awesome job completing it! 🎉"
                : "Okay, I've marked it as not done. Let's get it done soon! 💪");
        return message;
    }

    /**
     * Displays confirmation of task deletion and shows the updated task list.
     *
     * @param task The task that was deleted.
     * @param tasks The updated task list after deletion.
     */
    public String showDeletedTask(Task task, TaskList tasks) {
        return "Poof! Task removed! 💨\n"
                + "  ->  " + task + "\n"
                + "\n"
                + "Updated task list:\n"
                + tasks.toString();
    }

    /**
     * Displays the all the matching tasks found in the current task list.
     *
     * @param tasks The matching tasks found.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Hmm, no tasks match your search.  Maybe try a different keyword? 🤔";
        } else {
            StringBuilder sb = new StringBuilder("Ta-da! ✨ Here are the tasks matching your search:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("  ").append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Displays the farewell message when the application closes.
     */
    public String showExit() {
        return "Alright, signing off! 👋  Catch you again soon!  Remember to stay productive! 😉";
    }
}
