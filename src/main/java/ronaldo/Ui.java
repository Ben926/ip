package ronaldo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void printWelcomeText() {
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
    }

    public void printExitText() {
        System.out.println("Goodbye. SIUUUU.");
    }

    public String readCommand() {
        String line = sc.nextLine().trim();
        return line;
    }

    public void printAddedTask(Task task, int size) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've added this task:\n%s", task));
        System.out.println(String.format("Now you have %d tasks in the list.\n", size));
    }

    public void printMarkedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've marked this task as done:\n%s\n", task));
    }

    public void printUnmarkedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've unmarked this task:\n%s\n", task));
    }

    public void printDeletedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've deleted this task:\n%s\n", task));
    }

    public void printAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.getTask(i)));
        }
        System.out.println("");
    }

    public void printFoundTasks(TaskList filteredTaskList) {
        if (filteredTaskList.isEmpty()) {
            System.out.println("I couldn't find anything :(.\n");
        } else {
            System.out.println("SIUUUU. I found the following items:");
            printAllTasks(filteredTaskList);
        }
    }
}
