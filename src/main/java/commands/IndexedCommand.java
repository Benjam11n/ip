package commands;

import exceptions.CommandFormatException;

public abstract class IndexedCommand implements Command {
    protected final int taskIndex;

    public IndexedCommand(String commandName, String userInput) throws CommandFormatException {
        this.taskIndex = parseTaskIndex(commandName, userInput);
    }

    protected static int parseTaskIndex(String commandName, String userInput) throws CommandFormatException {
        if (userInput.trim().length() <= commandName.length()) {
            throw new CommandFormatException("The task index cannot be empty.");
        }

        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new CommandFormatException("Specify a task number after '" + commandName + "'.");
        }

        try {
            return Integer.parseInt(parts[1]) - 1; // 0-based index
        } catch (NumberFormatException e) {
            throw new CommandFormatException("'" + parts[1] + "' is not a valid task number.");
        }
    }
}