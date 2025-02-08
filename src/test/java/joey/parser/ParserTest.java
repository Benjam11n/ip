package joey.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import joey.command.Command;
import joey.command.DeadlineCommand;
import joey.command.DeleteCommand;
import joey.command.EventCommand;
import joey.command.ExitCommand;
import joey.command.ListCommand;
import joey.command.TodoCommand;
import joey.exception.CommandFormatException;

public class ParserTest {
    // Test valid commands
    @Test
    public void parse_validTodoCommand_returnsTodoCommand() throws CommandFormatException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_validDeadlineCommand_returnsDeadlineCommand() throws CommandFormatException {
        Command command = Parser.parse("deadline return book /by 2025-01-01");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void parse_validEventCommand_returnsEventCommand() throws CommandFormatException {
        Command command = Parser.parse("event project meeting /from 2025-01-01 /to 2025-01-02");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void parse_validMarkCommand_returnsMarkCommand() throws CommandFormatException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_validUnmarkCommand_returnsUnmarkCommand() throws CommandFormatException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws CommandFormatException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void parse_validListCommand_returnsListCommand() throws CommandFormatException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_validExitCommand_returnsExitCommand() throws CommandFormatException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    // Test invalid commands
    @Test
    public void parse_invalidCommand_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("invalidcommand"));
    }

    @Test
    public void parse_emptyTodoCommand_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_emptyDeadlineCommand_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void parse_emptyEventCommand_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("event"));
    }

    @Test
    public void parse_missingDeadlineDate_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void parse_missingEventDates_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("event project meeting"));
    }

    @Test
    public void parse_invalidTaskIndex_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("mark invalid"));
    }

    @Test
    public void parse_invalidDateFormat_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("deadline return book /by invalid-date"));
    }

    @Test
    public void parse_eventStartAfterEnd_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("event project meeting /from 2025-01-02 /to 2025-01-01")
        );
    }

    // Test edge cases
    @Test
    public void parse_extraSpaces_ignoresSpaces() throws CommandFormatException {
        Command command = Parser.parse("  mark   2  ");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_caseInsensitiveCommand_returnsCorrectCommand() throws CommandFormatException {
        Command command = Parser.parse("TODO read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_emptyInput_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_whitespaceInput_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("   "));
    }
}
