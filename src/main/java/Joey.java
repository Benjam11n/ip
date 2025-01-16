import java.util.Objects;
import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        History history = new History();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Joey!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println(history); // Displays the history
                System.out.println("____________________________________________________________");
                continue;
            }

            if (Objects.equals(userInput, "bye")) {
                break;
            }

            // Create a new task and add it to history
            Task newTask = new Task(userInput);
            history.add(newTask);

            System.out.println("____________________________________________________________");
            System.out.println("Joey: Added \"" + userInput + "\" to the history");
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
