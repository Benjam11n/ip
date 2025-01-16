import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Joey!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < history.size(); i++) {
                    System.out.println((i + 1) + "." + history.get(i));
                }
                System.out.println("____________________________________________________________");

                continue;
            }

            if (Objects.equals(userInput, "bye")) {
                break;
            }

            history.add(userInput);

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
