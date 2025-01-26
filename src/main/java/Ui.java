import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void printWelcomeText() {
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
    }

    public String readCommand() {
        String line = sc.nextLine().trim();
        return line;
    }

    public void printTasks(TaskList tasks) {
        ArrayList<Task> arr = tasks.getTasks();
        if (arr.isEmpty()) {
            System.out.println("List is empty!");
        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, arr.get(i)));
        }
        System.out.println("");
    }
}
