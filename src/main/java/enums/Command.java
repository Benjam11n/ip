package enums;

import exceptions.CommandFormatException;

public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    BYE("bye");

    private final String commandWord;

    Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public static Command fromString(String text) throws CommandFormatException {
        for (Command command : Command.values()) {
            if (command.commandWord.equals(text)) {
                return command;
            }
        }
        throw new CommandFormatException("Error: I don't understand this command.\n" +
                "Here are the commands you can use:\n" +
                "  todo       - Add a todo task (e.g., todo read book)\n" +
                "  deadline   - Add a task with deadline (e.g., deadline essay /by Sunday)\n" +
                "  event      - Add an event (e.g., event meeting /from Mon 2pm /to 4pm)\n" +
                "  delete     - Delete a task\n" +
                "  list       - Show all tasks\n" +
                "  mark       - Mark a task as done (e.g., mark 1)\n" +
                "  unmark     - Mark a task as not done (e.g., unmark 1)\n" +
                "  bye        - Exit the program");
    }
}
