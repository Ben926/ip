import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate due;

    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", this.due);
    }

}
