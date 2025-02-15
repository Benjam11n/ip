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
import joey.command.ToggleCommand;
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
        assertInstanceOf(ToggleCommand.class, command);
    }

    @Test
    public void parse_validUnmarkCommand_returnsUnmarkCommand() throws CommandFormatException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(ToggleCommand.class, command);
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
        assertInstanceOf(ToggleCommand.class, command);
    }

    @Test
    public void parse_todoWithMultipleWords_returnsTodoCommand() throws CommandFormatException {
        Command command = Parser.parse("todo finish the project presentation slides");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_deadlineWithSpecialCharacters_returnsDeadlineCommand() throws CommandFormatException {
        Command command = Parser.parse("deadline review PR #123 & update docs /by 2025-01-01");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void parse_todoWithEmoji_returnsTodoCommand() throws CommandFormatException {
        Command command = Parser.parse("todo call mom ❤️");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_markInvalidInteger_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("mark test"));
    }

    @Test
    public void parse_deadlineWithoutByKeyword_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("deadline submit report 2025-01-01"));
    }

    @Test
    public void parse_eventWithoutFromToKeywords_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("event team meeting 2025-01-01 2025-01-02"));
    }

    @Test
    public void parse_eventWithMissingToDate_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("event team meeting /from 2025-01-01"));
    }

    @Test
    public void parse_eventWithMissingFromDate_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("event team meeting /to 2025-01-02"));
    }

    @Test
    public void parse_deadlineWithInvalidTimeFormat_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("deadline submit report /by 2025-01-01 25:00"));
    }

    @Test
    public void parse_eventWithInvalidTimeFormat_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("event meeting /from 2025-01-01 9:60 /to 2025-01-01 10:00"));
    }

    @Test
    public void parse_todoWithOnlyWhitespace_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("todo     "));
    }

    @Test
    public void parse_multipleConsecutiveSpaces_returnsTodoCommand() throws CommandFormatException {
        Command command = Parser.parse("todo    write     unit    tests");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_todoWithLeadingNumbers_returnsTodoCommand() throws CommandFormatException {
        Command command = Parser.parse("todo 1. First task 2. Second task");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_deadlineWithTimezone_throwsException() {
        assertThrows(CommandFormatException.class, () ->
                Parser.parse("deadline meeting /by 2025-01-01 14:30 GMT+8"));
    }

    @Test
    public void parse_markWithFloatingPointIndex_throwsException() {
        assertThrows(CommandFormatException.class, () -> Parser.parse("mark 1.5"));
    }

    @Test
    public void parse_unexpectedCapitalization_ignoresCapitalization() throws CommandFormatException {
        Command command = Parser.parse("  mARk   2  ");
        assertInstanceOf(ToggleCommand.class, command);
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
