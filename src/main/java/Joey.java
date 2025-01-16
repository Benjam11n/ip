import java.util.Objects;
import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        History history = new History();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Joey!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Handling the "mark" command
            if (userInput.startsWith("mark")) {
                try {
                    String[] parts = userInput.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;

                    if (history.markTask(taskIndex)) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task " + (taskIndex + 1) + " has been marked!");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task Index out of range");
                        System.out.println("____________________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("Please specify a task number after 'mark'.");
                }
                continue;
            }

            // Handle the "unmark" command
            if (userInput.startsWith("unmark")) {
                try {
                    String[] parts = userInput.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;

                    if (history.unMarkTask(taskIndex)) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task " + (taskIndex + 1) + " has been marked undone!");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task Index out of range");
                        System.out.println("____________________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("Please specify a task number after 'unmark'.");
                }
                continue;
            }

            // Handling the "list" command
            if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println(history);
                System.out.println("____________________________________________________________");
                continue;
            }

            // Handling the "bye" command
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                return;
            }

            // Default case for adding new tasks
            Task newTask = new Task(userInput);
            history.add(newTask);
            System.out.println("____________________________________________________________");
            System.out.println("Joey: Added \"" + userInput + "\" to the history");
            System.out.println("____________________________________________________________");
        }
    }
}
