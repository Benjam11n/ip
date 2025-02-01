package joey;

import joey.command.Command;
import joey.exception.CommandFormatException;
import joey.parser.Parser;
import joey.storage.Storage;
import joey.task.*;
import joey.ui.Ui;

import java.util.Scanner;

import java.io.IOException;

public class Joey {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Joey() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            storage.load(tasks);
        } catch (IOException e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
        }

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            this.ui.showPrompt();
            String input = scanner.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (CommandFormatException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Joey().run();
    }
}
