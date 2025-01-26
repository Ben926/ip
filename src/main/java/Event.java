import java.time.LocalDate;

public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + String.format(" (from: %s to: %s)", this.from, this.to);
    }

}
