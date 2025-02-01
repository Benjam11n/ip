package joey.parser;

import joey.command.*;
import joey.exception.CommandFormatException;

public class Parser {
    public static Command parse(String userInput) throws CommandFormatException {
        String commandWord = userInput.split(" ")[0].toLowerCase();

        switch (commandWord) {
            case "todo":
                return new TodoCommand(userInput);
            case "deadline":
                return new DeadlineCommand(userInput);
            case "event":
                return new EventCommand(userInput);
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(userInput);
            case "unmark":
                return new UnmarkCommand(userInput);
            case "delete":
                return new DeleteCommand(userInput);
            case "bye":
                return new ExitCommand();
            default:
                throw new CommandFormatException("""
                        Error: I don't understand this command.
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