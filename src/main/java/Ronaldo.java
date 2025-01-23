import java.util.*;

public class Ronaldo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
        ArrayList<Task> arr = new ArrayList<>();
        String line = sc.nextLine().trim();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    if (arr.isEmpty()) {
                        System.out.println("List is empty! But my wallet is not. I am GOAT.");
                    }
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, arr.get(i)));
                    }
                    System.out.println("");
                } else if (line.startsWith("mark")) {
                    handleMarkCommand(line, arr);
                } else if (line.startsWith("unmark")) {
                    handleUnmarkCommand(line, arr);
                } else if (line.startsWith("todo")) {
                    handleTodoCommand(line, arr);
                } else if (line.startsWith("deadline")) {
                    handleDeadlineCommand(line, arr);
                } else if (line.startsWith("event")) {
                    handleEventCommand(line, arr);
                } else {
                    throw new RonaldoException("No no no. Wrong. Speak properly please or " +
                            "I will shoot knuckleball at you.\n");
                }
            } catch (RonaldoException e) {
                System.out.println(e.getMessage());
            }
            line = sc.nextLine().trim();
        }
        System.out.println("Goodbye SIUUUU.");
    }

    private static void handleMarkCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String[] tokens = line.split(" ");
            Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
            task.markAsDone();
            System.out.println(String.format("SIIUUUU I am Cristiano and I've marked this task as done:\n%s\n", task));
        } catch (IndexOutOfBoundsException e) {
            throw new RonaldoException(String.format("Don't be stupid. No such task exists! " +
                    "The list only has %d items.\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: mark <task_number>\n");
        }
    }

    private static void handleUnmarkCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String[] tokens = line.split(" ");
            Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
            task.markAsUndone();
            System.out.println(String.format("SIIUUUU I am Cristiano and I've marked this task as undone:\n%s\n", task));
        } catch (IndexOutOfBoundsException e) {
            throw new RonaldoException(String.format("Don't be stupid. No such task exists! " +
                    "The list only has %d items.\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: unmark <task_number>\n");
        }
    }

    private static void handleTodoCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String description = line.substring(5).trim();
            if (description.isEmpty()) {
                throw new RonaldoException("Proper description please.\n");
            }
            Task task = new ToDo(description);
            arr.add(task);
            System.out.println("SIIUUU I am Cristiano and I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: todo <description>\n");
        }
    }

    private static void handleDeadlineCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String[] tokens = line.split("/by");
            String description = tokens[0].substring(9).trim();
            String due = tokens[1].trim();
            Task task = new Deadline(description, due);
            arr.add(task);
            System.out.println("SIIUUU I am Cristiano and I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: deadline <description> /by <date>\n");
        }
    }

    private static void handleEventCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String[] tokens = line.split("/from|/to");
            String description = tokens[0].substring(6).trim();
            String from = tokens[1].trim();
            String to = tokens[2].trim();
            Task task = new Event(description, from, to);
            arr.add(task);
            System.out.println("SIIUUU I am Cristiano and I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: event <description> /from <start_time> /to <end_time>\n");
        }
    }
}
