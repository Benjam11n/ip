import exceptions.CommandFormatException;
import exceptions.TaskIndexOutOfBoundsException;

import java.util.Objects;
import java.util.Scanner;

public class Joey {
    private static int parseTaskIndex(String command, String userInput) throws CommandFormatException, TaskIndexOutOfBoundsException {
        if (userInput.length() <= command.length()) {
            throw new CommandFormatException("The task index cannot be empty.");
        }

        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new CommandFormatException("Please specify a task number after '" + command + "'");
        }

        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CommandFormatException("'" + parts[1] + "' is not a valid task number");
        }
    }
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
            if (userInput.split(" ")[0].equals("mark")) {
                try {
                    int taskIndex = parseTaskIndex("mark", userInput);
                    history.markTask(taskIndex);
                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Task " + (taskIndex + 1) + " has been marked!");
                    System.out.println("____________________________________________________________");
                } catch (TaskIndexOutOfBoundsException | CommandFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Handling the "unmark" command
            else if (userInput.split(" ")[0].equals("unmark")) {
                try {
                    int taskIndex = parseTaskIndex("unmark", userInput);
                    history.unMarkTask(taskIndex);
                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Task " + (taskIndex + 1) + " has been unmarked!");
                    System.out.println("____________________________________________________________");
                } catch (TaskIndexOutOfBoundsException | CommandFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Handling the "todo" command
            else if (userInput.split(" ")[0].equals("todo")) {
                try {
                    if (userInput.length() <= 5) {
                        throw new CommandFormatException("Please specify a task description after 'todo'.\n" +
                                "For example: 'todo borrow book'");
                    }

                    String taskName = userInput.substring(5).trim();
                    if (taskName.isEmpty()) {
                        throw new CommandFormatException("Please specify a task description after 'todo'.\n" +
                                "For example: 'todo borrow book'");
                    }

                    Task todo = new Todo(taskName);
                    history.add(todo);

                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Todo: \"" + taskName + "\" to the history");
                    System.out.println("____________________________________________________________");
                } catch (CommandFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            else if (userInput.split(" ")[0].equals("deadline")) {
                try {
                    if (userInput.length() <= 8) {
                        throw new CommandFormatException("Please specify a task description and a deadline date after 'deadline'.\n" +
                                "For example: 'deadline return book /by Sunday'");
                    }

                    String[] parts = userInput.split(" /by", 2);

                    if (parts.length < 2) {
                        throw new CommandFormatException("Please specify a task description and a deadline date after 'deadline'.\n" +
                                "For example: 'deadline return book /by Sunday'");
                    }

                    String taskName = parts[0].substring(8).trim();
                    String deadlineDate = parts[1].trim();
                    Task deadline = new Deadline(taskName, deadlineDate);

                    history.add(deadline);

                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Deadline: \"" + taskName + "\" with deadline " + deadlineDate + " to the history");
                    System.out.println("____________________________________________________________");
                } catch (CommandFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            else if (userInput.split(" ")[0].equals("event")) {
                try {
                    if (userInput.length() <= 5) {
                        throw new CommandFormatException("Please specify a task description, start date, and end date after 'event'.\n" +
                                "For example: 'event project meeting /from Mon 2pm /to 4pm'");
                    }
                    String[] parts = userInput.split(" /from", 2);
                    if (parts.length < 2) {
                        throw new CommandFormatException("Please specify a task description, start date, and end date after 'event'.\n" +
                                "For example: 'event project meeting /from Mon 2pm /to 4pm'");
                    }

                    String taskName = parts[0].substring(6).trim();
                    String[] eventDetails = parts[1].split(" /to", 2);
                    if (eventDetails.length < 2) {
                        throw new CommandFormatException("Please specify a task description, start date, and end date after 'event'.\n" +
                                "For example: 'event project meeting /from Mon 2pm /to 4pm'");
                    }

                    String startDate = eventDetails[0].trim();
                    String endDate = eventDetails[1].trim();
                    Task event = new Event(taskName, startDate, endDate);

                    history.add(event);

                    System.out.println("____________________________________________________________");
                    System.out.println("Joey: Added Event: \"" + taskName + "\" from " + startDate + " to " + endDate + " to the history");
                    System.out.println("____________________________________________________________");
                } catch (CommandFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Handling the "list" command
            else if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println(history);
                System.out.println("____________________________________________________________");
            }

            // Handling the "bye" command
            else if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                return;
            } else {
                System.out.println("Error: I don't understand this command.\n" +
                        "Here are the commands you can use:\n" +
                        "  todo       - Add a todo task (e.g., todo read book)\n" +
                        "  deadline   - Add a task with deadline (e.g., deadline essay /by Sunday)\n" +
                        "  event      - Add an event (e.g., event meeting /from Mon 2pm /to 4pm)\n" +
                        "  list       - Show all tasks\n" +
                        "  mark       - Mark a task as done (e.g., mark 1)\n" +
                        "  unmark     - Mark a task as not done (e.g., unmark 1)\n" +
                        "  bye        - Exit the program");
            }
        }
    }
}
