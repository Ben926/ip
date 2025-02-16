package ronaldo;

/**
 * An abstract class that represents a task with a description and a completion status.
 */
abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     * This constructor is called by the subclasses of this abstract class.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isMatchingDescription(String subDescription) {
        return this.description.contains(subDescription);
    }

    /**
     * Returns the status icon of the task, which indicates if it is done ("X") or not (" ").
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done by setting isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including
     * the status icon ([ ] or [X]) and the task's description.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}
