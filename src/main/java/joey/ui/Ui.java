package joey.ui;

import joey.task.Task;
import joey.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Joey!\nWhat can I do for you?");
        this.showLine();
    }

    public void showPrompt() {
        System.out.print("You: ");
    }
    public void showAddedTask(Task task, TaskList tasks) {
        System.out.println("Added: " + task + "\n" + "\n" + tasks);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMarkedTask(Task task, int index) {
        System.out.println("Marked joey.task " + index + ": " + task);
    }

    public void showUnmarkedTask(Task task, int index) {
        System.out.println("Unmarked joey.task " + index + ": " + task);
    }

    public void showDeletedTask(Task task, TaskList tasks) {
        System.out.println("Removed joey.task:\n  " + task +
                "\n" + "\n" + tasks);
    }

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

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}