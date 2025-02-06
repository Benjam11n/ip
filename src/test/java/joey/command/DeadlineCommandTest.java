package joey.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import joey.exception.CommandFormatException;
import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;

public class DeadlineCommandTest {

    private TaskList mockTaskList;
    private Ui mockUi;
    private Storage mockStorage;
    private DeadlineCommand deadlineCommand;

    @BeforeEach
    void setUp() {
        mockTaskList = Mockito.mock(TaskList.class);
        mockUi = Mockito.mock(Ui.class);
        mockStorage = Mockito.mock(Storage.class);

        this.deadlineCommand = new DeadlineCommand("Test deadline task",
                LocalDate.parse("2025-02-01"));
    }

    @Test
    void execute_validCommand_addsToTaskList() throws CommandFormatException, IOException {
        deadlineCommand.execute(mockTaskList, mockUi, mockStorage);

        // Verify that TaskList's add method was called once with a new Deadline task
        Mockito.verify(mockTaskList, times(1)).add(any());

        // Verify that Storage's save method was called once
        Mockito.verify(mockStorage, times(1)).save(mockTaskList);

        // Verify that Ui's showAddedTask method was called once with the new task and task list
        Mockito.verify(mockUi, times(1)).showAddedTask(any(), eq(mockTaskList));
    }
}
