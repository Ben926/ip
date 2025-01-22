public class ToDo extends Task{
    private String from;
    private String to;

    public ToDo(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString() + String.format("(from: %s to: %s", this.from, this.to);
    }

}
