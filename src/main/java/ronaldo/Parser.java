package ronaldo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the parsing of all inputs entered by the user.
 */
public class Parser {

    /**
     * Parses the user input and identifies the command type based on the Command enum.
     *
     * @param line The input string entered by the user.
     * @return The corresponding Command enum for the input.
     * @throws RonaldoException If the input does not match any recognized command.
     */
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
        } else if (line.startsWith("find")) {
            return Command.FIND;
        } else {
            throw new RonaldoException("No no no. Wrong. Speak properly please.\n");
        }
    }

    /**
     * Parses the user input to create a ToDo task.
     *
     * @param line The input string containing "todo" and description.
     * @return A ToDo task with the specified description.
     * @throws RonaldoException If the description is empty or invalid.
     */
    public static Task parseToDoCommand(String line) throws RonaldoException {
        String description = line.substring(4).trim();
        if (description.isEmpty()) {
            throw new RonaldoException("Proper description please.\n");
        }
        return new ToDo(description);
    }

    /**
     * Parses the user input to create a Deadline task.
     *
     * @param line The input string containing "deadline", the description, and due date.
     * @return A Deadline task with the specified description and due date.
     * @throws RonaldoException If the format is incorrect or the date is invalid.
     */
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

    /**
     * Parses the user input to create an Event task.
     *
     * @param line The input string containing "event", the description, from date, and to date.
     * @return An Event task with the specified description, start date, and end date.
     * @throws RonaldoException If the format is incorrect or the date format is invalid.
     */
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
            throw new RonaldoException("Do it like this: event <description> /from <start_date> /to <end_date>\n");
        }
    }

    /**
     * Parses the user input to extract the 1-based index specified, checking if it is valid.
     *
     * @param line The input string containing the command and 1-based index keyed in by the user.
     * @param maxIndex The maximum valid 1-based index for the task list.
     * @return The zero-based index of the task.
     * @throws RonaldoException If the format is incorrect or the index is out of bounds.
     */
    public static int parseIndex(String line, int maxIndex) throws RonaldoException {
        int index;
        try {
            String[] tokens = line.split(" ");
            index = Integer.parseInt(tokens[1]);
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: mark/unmark/delete <task_number>\n");
        }
        if (index > maxIndex) {
            throw new RonaldoException(String.format("Don't be stupid. No such task exists! " +
                    "The list only has %d items.\n", maxIndex + 1));
        }
        return index - 1;
    }

    public static String parseFindCommand(String line) throws RonaldoException {
        try {
            String subDescription = line.substring(4).trim();
            return subDescription;
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: find <description>\n");
        }
    }

}
