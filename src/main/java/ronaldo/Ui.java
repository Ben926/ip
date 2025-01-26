package ronaldo;

import java.util.Scanner;

/**
 * Handles all displayed outputs in the Ronaldo application.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcomeText() {
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void printExitText() {
        System.out.println("Goodbye. SIUUUU.");
    }

    /**
     * Reads the String entered by the user from the console.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        String line = sc.nextLine().trim();
        return line;
    }

    /**
     * Prints a message that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     */
    public void printAddedTask(Task task, int size) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've added this task:\n%s", task));
        System.out.println(String.format("Now you have %d tasks in the list.\n", size));
    }

    /**
     * Prints a message that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've marked this task as done:\n%s\n", task));
    }

    /**
     * Prints a message that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void printUnmarkedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've unmarked this task:\n%s\n", task));
    }

    /**
     * Prints a message that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public void printDeletedTask(Task task) {
        System.out.println(String.format("SIIUUUU I am Cristiano and I've deleted this task:\n%s\n", task));
    }

    /**
     * Prints all tasks currently in the task list, or that its empty if it is such.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public void printAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.getTask(i)));
        }
        System.out.println("");
    }
}
