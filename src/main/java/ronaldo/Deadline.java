package ronaldo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Task with a single due date
 */
class Deadline extends Task {
    private LocalDate due;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description A brief description of the task.
     * @param due The due date of the task as a LocalDate object.
     */
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    /**
     * Returns a string representation of the Deadline, including
     * its description and due date in the format "MMM d yyyy".
     *
     * @return A formatted string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)",
                this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

}
