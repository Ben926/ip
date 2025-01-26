package ronaldo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate due;

    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)",
                this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

}
