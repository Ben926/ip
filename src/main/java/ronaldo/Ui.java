package ronaldo;

import java.util.Scanner;

/**
 * Handles all displayed outputs in the Ronaldo application.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Returns the welcome message to the user.
     *
     * @return The welcome message as a string.
     */
    public String getWelcomeText() {
        return "SIIUUUUUU!!! Ronaldo here.\nHow can the GOAT help you?\n";
    }

    /**
     * Returns the goodbye message to the user.
     *
     * @return The goodbye message as a string.
     */
    public String getExitText() {
        return "Goodbye. SIUUUU.";
    }

    /**
     * Returns a message that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     * @return The message about the added task as a string.
     */
    public String getAddedTaskText(Task task, int size) {
        return String.format("SIIUUUU I am Cristiano and " +
                "I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, size);
    }

    /**
     * Returns a message that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The message about the marked task as a string.
     */
    public String getMarkedTaskText(Task task) {
        return String.format("SIIUUUU I am Cristiano and I've marked this task as done:\n%s\n", task);
    }

    /**
     * Returns a message that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     * @return The message about the unmarked task as a string.
     */
    public String getUnmarkedTaskText(Task task) {
        return String.format("SIIUUUU I am Cristiano and I've unmarked this task:\n%s\n", task);
    }

    /**
     * Returns a message that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @return The message about the deleted task as a string.
     */
    public String getDeletedTaskText(Task task) {
        return String.format("SIIUUUU I am Cristiano and I've deleted this task:\n%s\n", task);
    }

    /**
     * Returns a string representation of all tasks currently in the task list, or a message if it is empty.
     *
     * @param tasks The TaskList object containing all tasks.
     * @return The string representation of all tasks or a message if the list is empty.
     */
    public String getAllTasksText(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Task list is empty!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i)));
        }
        return sb.toString();
    }

    /**
     * Returns a message about the tasks found in the filtered task list.
     *
     * @param filteredTaskList The TaskList object containing the filtered tasks.
     * @return The message about the found tasks or a message if no tasks were found.
     */
    public String getFoundTasksText(TaskList filteredTaskList) {
        if (filteredTaskList.isEmpty()) {
            return "I couldn't find anything :(.\n";
        } else {
            return "SIUUUU. I found the following items:\n" + getAllTasksText(filteredTaskList);
        }
    }
}
