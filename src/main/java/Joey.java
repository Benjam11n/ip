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

            // Handle the "todo" command
            if (userInput.startsWith("todo")) {
                try {
                    String taskName = userInput.substring(5);
                    Task todo = new Todo(taskName);
                    history.add(todo);
                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Todo: \"" + taskName + "\" to the history");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Please specify a description after 'todo'.");
                }
            }

            if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.split(" /by", 2);
                    String taskName = parts[0];
                    String deadlineDate = parts[1];
                    Task deadline = new Deadline(taskName, deadlineDate);
                    history.add(deadline);
                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Deadline: \"" + taskName + "\" with deadline " + deadlineDate + " to the history");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Please specify a task description and a deadline date after 'deadline'.\n" +
                            "For example: 'deadline return book /by Sunday'");
                }
            }

            if (userInput.startsWith("event")) {
                try {
                    String[] parts = userInput.split(" /from", 2);
                    String taskName = parts[0].substring(6);
                    String[] eventDetails = parts[1].split(" /to", 2);
                    String startDate = eventDetails[0];
                    String endDate = eventDetails[1];
                    Task event = new Event(taskName, startDate, endDate);
                    history.add(event);
                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Event: \"" + taskName + "\" from " + startDate + " to " + endDate + " to the history");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Please specify a task description, start date, and end date after 'event'.\n" +
                            "For example: 'event project meeting /from Mon 2pm /to 4pm'");
                }
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
        }
    }
}
