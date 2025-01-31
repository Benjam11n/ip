import enums.Command;
import exceptions.CommandFormatException;
import exceptions.TaskIndexOutOfBoundsException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

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

        try {
            Storage.readFile(history);  // Load saved tasks at startup
        } catch (IOException e) {
            System.out.println("No saved tasks found or error reading saved tasks.");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Joey!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            String commandWord = userInput.split(" ")[0];
            Command command;
            try {
                command = Command.fromString(commandWord);
            } catch (CommandFormatException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (Objects.requireNonNull(command)) {
                case MARK:
                    try {
                        int taskIndex = parseTaskIndex("mark", userInput);
                        history.markTask(taskIndex);
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task " + (taskIndex + 1) + " has been marked!");
                        System.out.println("____________________________________________________________");
                    } catch (TaskIndexOutOfBoundsException | CommandFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case UNMARK:
                    try {
                        int taskIndex = parseTaskIndex("unmark", userInput);
                        history.unMarkTask(taskIndex);
                        System.out.println("____________________________________________________________");
                        System.out.println("Joey: Task " + (taskIndex + 1) + " has been unmarked!");
                        System.out.println("____________________________________________________________");
                    } catch (TaskIndexOutOfBoundsException | CommandFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case TODO:
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
                    break;
                case DEADLINE:
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
                    break;
                case EVENT:
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
                    break;
                case DELETE:
                    try {
                        int taskIndex = parseTaskIndex("delete", userInput);
                        Task task = history.deleteTask(taskIndex);
                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task:\n" + task + "\n");
                        System.out.println(history);
                        System.out.println("____________________________________________________________");
                    } catch (TaskIndexOutOfBoundsException | CommandFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case LIST:
                    System.out.println("____________________________________________________________");
                    System.out.println(history);
                    System.out.println("____________________________________________________________");
                    break;
                case BYE:
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    scanner.close();
                    return;
            }

            try {
                Storage.writeToFile(history);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error saving tasks.");
            }
        }
    }
}
