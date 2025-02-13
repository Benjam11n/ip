package joey.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import joey.exception.CommandFormatException;
import joey.parser.Parser;
import joey.storage.Storage;
import joey.task.Event;
import joey.task.Task;
import joey.task.TaskList;
import joey.ui.Ui;

/**
 * Represents a command to create a new event task with a start and end date.
 * This command adds the event to the task list and persists it to storage.
 */
public class EventCommand implements Command {
    private static final String EVENT_ERROR_MESSAGE = """
            Please specify a task description, start date, and end date after 'event'
            For example: 'event concert /from 2025-02-01 /to 2025-02-02'""";
    private static final Set<String> IDENTIFIERS = new HashSet<>(Arrays.asList("event", "e"));
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs a new event command.
     *
     * @param description The description of the event
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     */
    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Parses the user input for the description, startDate and endDate of the event
     *
     * @param commandArgs The user input
     * @return EventCommand after parsing the relevant details
     */
    public static EventCommand parse(String commandArgs) throws CommandFormatException {
        String[] eventParts = commandArgs.split(" /from ", 2);
        if (eventParts.length < 2) {
            throw new CommandFormatException(EVENT_ERROR_MESSAGE);
        }

        String[] eventDetails = eventParts[1].split(" /to ", 2);
        if (eventDetails.length < 2) {
            throw new CommandFormatException(EVENT_ERROR_MESSAGE);
        }

        String eventDescription = eventParts[0].trim();
        LocalDate startDate = Parser.parseDate(eventDetails[0].trim());
        LocalDate endDate = Parser.parseDate(eventDetails[1].trim());

        if (startDate.isAfter(endDate)) {
            throw new CommandFormatException("Start date cannot be after end date.");
        }

        return new EventCommand(eventDescription, startDate, endDate);
    }

    /**
     * Checks if the given command word matches any of the event command identifiers.
     * This includes aliases like "event", "e".
     *
     * @param commandWord The command word to check
     * @return true if the command word matches any event command identifier, false otherwise
     */
    public static boolean matches(String commandWord) {
        return IDENTIFIERS.contains(commandWord.toLowerCase());
    }

    /**
     * Executes the event command by creating and adding a new event to the task list.
     * The new event is saved to storage and confirmation is shown to the user.
     *
     * @param tasks The task list to add the event to
     * @param ui The UI to display the confirmation message
     * @param storage The storage to save the updated task list
     * @return The confirmation message indicating the deadline was added
     * @throws IOException if there is an error saving to storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task event = new Event(this.description, this.startDate, this.endDate);
        tasks.add(event);
        storage.save(tasks);
        return ui.showAddedTask(event, tasks);
    }
}
