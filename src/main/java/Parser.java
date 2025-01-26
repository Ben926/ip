import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public static Task parseToDoCommand(String line) throws RonaldoException {
        String description = line.substring(5).trim();
        if (description.isEmpty()) {
            throw new RonaldoException("Proper description please.\n");
        }
        return  new ToDo(description);
    }

    public static Task parseDeadlineCommand(String line) throws RonaldoException {
       try {
           String[] tokens = line.split("/by");
           String description = tokens[0].substring(9).trim();
           String due = tokens[1].trim();
           LocalDate dueDate = LocalDate.parse(due, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           return new Deadline(description, dueDate);
       } catch (DateTimeParseException e) {
            throw new RonaldoException("Use 'yyyy-MM-dd' to specify dates. For example: 2025-01-26.\n");
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: deadline <description> /by <date>\n");
        }
    }

    public static Task parseEventCommand(String line) throws RonaldoException {
        try {
            String[] tokens = line.split("/from|/to");
            String description = tokens[0].substring(6).trim();
            String from = tokens[1].trim();
            String to = tokens[2].trim();
            LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Event(description, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new RonaldoException("Use 'yyyy-MM-dd' to specify dates. For example: 2025-01-26.\n");
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: event <description> /from <start_time> /to <end_time>\n");
        }
    }

    public static int parseIndex(String line, int maxIndex) throws RonaldoException {
        try {
            String[] tokens = line.split(" ");
            int index = Integer.parseInt(tokens[1]);
            if (index > maxIndex) {
                throw new RonaldoException(String.format("Don't be stupid. No such task exists! " +
                        "The list only has %d items.\n", maxIndex + 1));
            }
            return index - 1;
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: mark/unmark/delete <task_number>\n");
        }
    }
}
