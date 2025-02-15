package joey.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import joey.Joey;
import joey.command.Command;
import joey.exception.CommandFormatException;
import joey.parser.Parser;
import joey.storage.Storage;
import joey.task.TaskList;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Joey joey;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image joeyImage = new Image(this.getClass().getResourceAsStream("/images/Joey.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJoey(Joey j) {
        joey = j;
    }

    /**
     * Displays the welcome message
     */
    public void showWelcome() {
        String welcomeMsg = joey.getUi().showWelcome();

        dialogContainer.getChildren().add(
                DialogBox.getJoeyDialog(welcomeMsg, joeyImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            // Parse and execute the command
            Command command = Parser.parse(input);

            TaskList tasks = joey.getTasks();
            Ui ui = joey.getUi();
            Storage storage = joey.getStorage();

            assert tasks != null : "TaskList cannot be null.";
            assert ui != null : "Ui cannot be null.";
            assert storage != null : "Storage cannot be null.";

            String response = command.execute(tasks, ui, storage);

            // Add the dialog boxes
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJoeyDialog(response, joeyImage)
            );

            if (command.isExit()) {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            }
        } catch (CommandFormatException | IOException e) {
            String errorMsg = joey.getUi().showError(e.getMessage());
            DialogBox errorDialog = DialogBox.getJoeyDialog(errorMsg, joeyImage);

            Label errorLabel = errorDialog.getLabel();
            if (errorLabel != null) {
                errorLabel.getStyleClass().add("error-label");
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    errorDialog
            );
        }
        userInput.clear();
    }
}
