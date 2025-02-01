package joey.command;

import joey.storage.Storage;
import joey.task.TaskList;
import joey.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class TodoCommandTest {

    private TaskList mockTaskList;
    private Ui mockUi;
    private Storage mockStorage;
    private TodoCommand todoCommand;

    @BeforeEach
    void setUp() {
        mockTaskList = Mockito.mock(TaskList.class);
        mockUi = Mockito.mock(Ui.class);
        mockStorage = Mockito.mock(Storage.class);

        todoCommand = new TodoCommand("Test todo task");
    }

    @Test
    void execute_validCommand_addsToTaskList() throws IOException {
        todoCommand.execute(mockTaskList, mockUi, mockStorage);

        // Verify that TaskList's add method was called once with a new Todo task
        Mockito.verify(mockTaskList, times(1)).add(any());

        // Verify that Storage's save method was called once
        Mockito.verify(mockStorage, times(1)).save(mockTaskList);

        // Verify that Ui's showAddedTask method was called once with the new task and task list
        Mockito.verify(mockUi, times(1)).showAddedTask(any(), eq(mockTaskList));
    }
}
