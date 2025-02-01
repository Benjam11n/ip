package joey.parser;

import joey.command.*;
import joey.exception.CommandFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into appropriate Command objects.
 * This class handles the interpretation of user commands and their arguments,
 * converting them into executable Command objects.
 */
public class Parser {
    private static final String TODO_ERROR_MESSAGE = """
            Please specify a task description after 'todo'.
            For example: 'todo borrow book'""";
    private static final String DEADLINE_ERROR_MESSAGE = """
            Please specify a task description and a deadline date after 'deadline'.
            For example: 'deadline return book /by 2025-02-01'""";
    private static final String EVENT_ERROR_MESSAGE = """
            Please specify a task description, start date, and end date after 'event'
            For example: 'event concert /from 2025-02-01 /to 2025-02-02'""";
    private static final String INVALID_DATE_ERROR_MESSAGE =
            "Invalid date format. Use YYYY-MM-DD.";

    /**
     * Parses a task index from user input.
     * Converts 1-based user input to 0-based index for internal use.
     *
     * @param commandName The name of the command being processed
     * @param userInput The full user input string
     * @return The parsed task index (0-based)
     * @throws CommandFormatException if the index is missing or invalid
     */
    private static int parseTaskIndex(String commandName, String userInput) throws CommandFormatException {
        String[] parts = userInput.trim().split("\\s+", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CommandFormatException("Specify a task number after '" + commandName + "'.");
        }

        try {
            return Integer.parseInt(parts[1]) - 1; // 0-based index
        } catch (NumberFormatException e) {
            throw new CommandFormatException("'" + parts[1] + "' is not a valid task number.");
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string in YYYY-MM-DD format
     * @return The parsed LocalDate
     * @throws CommandFormatException if the date format is invalid
     */
    private static LocalDate parseDate(String dateStr) throws CommandFormatException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new CommandFormatException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    /**
     * Parses user input into a specific Command object.
     * Supports commands: todo, deadline, event, list, mark, unmark, delete, and bye.
     * Command formats:
     * - todo: todo [description]
     * - deadline: deadline [description] /by [date]
     * - event: event [description] /from [start-date] /to [end-date]
     * - mark/unmark/delete: [command] [task-number]
     * - list/bye: no additional arguments needed
     *
     * @param userInput The raw user input string
     * @return A Command object representing the parsed command
     * @throws CommandFormatException if the command format is invalid
     */
    public static Command parse(String userInput) throws CommandFormatException {
        String commandWord = userInput.trim().split(" ")[0].toLowerCase();

        switch (commandWord) {
            case "todo":
                String[] todoParts = userInput.trim().split("\\s+", 2);
                if (todoParts.length < 2 || todoParts[1].trim().isEmpty()) {
                    throw new CommandFormatException(TODO_ERROR_MESSAGE);
                }
                return new TodoCommand(todoParts[1].trim());

            case "deadline":
                String[] deadlineParts = userInput.split(" /by ", 2);
                if (deadlineParts.length < 2 || deadlineParts[0].trim().length() <= 9 || deadlineParts[1].trim().isEmpty()) {
                    throw new CommandFormatException(DEADLINE_ERROR_MESSAGE);
                }

                String deadlineDescription = deadlineParts[0].substring(9).trim(); // Extract description after "deadline "
                LocalDate by = parseDate(deadlineParts[1].trim());

                return new DeadlineCommand(deadlineDescription, by);

            case "event":
                String[] eventParts = userInput.split(" /from ", 2);
                if (eventParts.length < 2) {
                    throw new CommandFormatException(EVENT_ERROR_MESSAGE);
                }

                String[] eventDetails = eventParts[1].split(" /to ", 2);
                if (eventDetails.length < 2) {
                    throw new CommandFormatException(EVENT_ERROR_MESSAGE);
                }

                String eventDescription = eventParts[0].trim();
                LocalDate startDate = parseDate(eventDetails[0].trim());
                LocalDate endDate = parseDate(eventDetails[1].trim());

                if (startDate.isAfter(endDate)) {
                    throw new CommandFormatException("Start date cannot be after end date.");
                }

                return new EventCommand(eventDescription, startDate, endDate);

            case "list":
                return new ListCommand();

            case "mark":
                int markIndex = parseTaskIndex(commandWord, userInput);
                return new MarkCommand(markIndex);

            case "unmark":
                int unmarkIndex = parseTaskIndex(commandWord, userInput);
                return new UnmarkCommand(unmarkIndex);

            case "delete":
                int deleteIndex = parseTaskIndex(commandWord, userInput);
                return new DeleteCommand(deleteIndex);

            case "bye":
                return new ExitCommand();

            default:
                throw new CommandFormatException("""
                        I don't understand this command.
                        Here are the commands you can use:
                          todo       - Add a todo task (e.g., todo read book)
                          deadline   - Add a task with deadline (e.g., deadline essay /by Sunday)
                          event      - Add an event (e.g., event meeting /from Mon 2pm /to 4pm)
                          delete     - Delete a task
                          list       - Show all tasks
                          mark       - Mark a task as done (e.g., mark 1)
                          unmark     - Mark a task as not done (e.g., unmark 1)
                          bye        - Exit the program""");
        }
    }
}