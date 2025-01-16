import java.util.Objects;
import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Joey!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println("Joey: " + userInput);
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
