public class Parser {
    public static Command parseCommand(String line) throws RonaldoException {
        if (line.equals("list")) {
            return Command.LIST;
        } else if (line.startsWith("mark")) {
            return Command.MARK;
        } else if (line.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (line.startsWith("todo")) {
            return Command.TODO;
        } else if (line.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (line.startsWith("event")) {
            return Command.EVENT;
        } else if (line.startsWith("delete")) {
            return Command.DELETE;
        } else if (line.equals("bye")) {
            return Command.BYE;
        } else {
            throw new RonaldoException("No no no. Wrong. Speak properly please.\n");
        }
    }

    public static void executeCommand(Command command, String line) {

    }
}
