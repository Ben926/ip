package ronaldo;

/**
 * A basic task with no dates specified.
 */
class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description A brief description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task
     * , including its description.
     *
     * @return A formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
