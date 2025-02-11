package ronaldo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a start date, and end date.
 */
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new Event task with the given description, from date and to date.
     *
     * @param description A brief description of the task.
     * @param from The start date of the task, stored as a LocalDate object.
     * @param to The end date of the task, stored as a LocalDate object.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event, including
     * its description and its start and end dates in the format "MMM d yyyy".
     *
     * @return A formatted string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + String.format(" (from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                        this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public LocalDate getFromDate() {
        return from;
    }
}
