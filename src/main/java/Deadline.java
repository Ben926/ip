public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", this.due);
    }

}
