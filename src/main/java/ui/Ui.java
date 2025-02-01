package ui;

import task.Task;
import task.TaskList;

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
        System.out.println("Marked task " + index + ": " + task);
    }

    public void showUnmarkedTask(Task task, int index) {
        System.out.println("Unmarked task " + index + ": " + task);
    }

    public void showDeletedTask(Task task, TaskList tasks) {
        System.out.println("Removed task:\n  " + task +
                "\n" + "\n" + tasks);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}