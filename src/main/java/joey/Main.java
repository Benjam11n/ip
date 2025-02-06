package joey;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joey.ui.MainWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Create Joey instance
            Joey joey = new Joey();

            // Load tasks once at startup
            joey.loadTasks();

            // Load and set up the GUI
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set up controller
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setJoey(joey);
            mainWindow.showWelcome();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
