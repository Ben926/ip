package ronaldo;

import java.util.ArrayList;

/**
 * An abstraction of the list of tasks in the Ronaldo application.
 */
public class TaskList {
    private final ArrayList<Task> arr;

    /**
     * Constructs a TaskList with an empty ArrayList.
     */
    public TaskList() {
        arr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        arr.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        return arr.remove(index);
    }

    /**
     * Gets a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return arr.get(index);
    }

    /**
     * Marks a task in the TaskList at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        Task task = arr.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task in the TaskList at the specified index.
     *
     * @param index The index of the task to unmark.
     * @return The task that was unmarked.
     */
    public Task unmarkTask(int index) {
        Task task = arr.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of the tasks in the ArrayList arr of the TaskList instance.
     */
    public int size() {
        return arr.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the ArrayList arr is empty, and false otherwise.
     */
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    public TaskList findMatchingTasks(String subDescription) {
        return new TaskList(new ArrayList<Task>(this.arr.stream()
                .filter(task -> task.checkMatchingDescription(subDescription)).toList()));
    }
}
