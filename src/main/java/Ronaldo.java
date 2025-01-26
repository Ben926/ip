import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Ronaldo {
    private static String TEXTFILE_PATH = "./data/ronaldo.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;
    static ArrayList<Task> arr;

    public Ronaldo(String filePath) {
        TEXTFILE_PATH = filePath;
    }

    public void run() {
        ui = new Ui();
        storage = new Storage("./data/ronaldo.txt");
        tasks = new TaskList();

        ui.printWelcomeText();
        while (isRunning) {
            try {
                String line = ui.readCommand();
                Command command = Parser.parseCommand(line);
                isRunning = this.executeCommand(command, line);
            } catch (RonaldoException e) {
                System.out.println(e.getMessage());
            } finally {
                storage.saveTasks(tasks);
            }
        }

    }

    public boolean executeCommand(Command command, String line) throws RonaldoException{
        Task task;
        switch (command) {
            case LIST:
                ui.printAllTasks(tasks);
                break;
            case MARK:
                task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
                ui.printMarkedTask(task);
                break;
            case UNMARK:
                task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
                break;
            case DELETE:
                task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
                break;
            case TODO:
                task = Parser.parseToDoCommand(line);
                tasks.addTask(task);
                ui.printAddedTask(task);
                break;
            case DEADLINE:
                task = Parser.parseDeadlineCommand(line);
                tasks.addTask(task);
                ui.printAddedTask(task);
                break;
            case EVENT:
                task = Parser.parseEventCommand(line);
                tasks.addTask(task);
                ui.printAddedTask(task);
                break;
            case BYE:
                ui.printExitText();
                return false;
            default:
                throw new RonaldoException("Unexpected error occurred!\n");
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
        arr = new ArrayList<>();
        String line = sc.nextLine().trim();
        while (!line.equals("bye")) {
            try {
                Command command = parseCommand(line);
                switch (command) {
                    case LIST:
                        if (arr.isEmpty()) {
                            System.out.println("List is empty!");
                        }
                        for (int i = 0; i < arr.size(); i++) {
                            System.out.println(String.format("%d. %s", i + 1, arr.get(i)));
                        }
                        System.out.println("");
                        break;
                    case MARK:
                        handleMarkCommand(line, arr);
                        break;
                    case UNMARK:
                        handleUnmarkCommand(line, arr);
                        break;
                    case TODO:
                        handleTodoCommand(line, arr);
                        break;
                    case DEADLINE:
                        handleDeadlineCommand(line, arr);
                        break;
                    case EVENT:
                        handleEventCommand(line, arr);
                        break;
                    case DELETE:
                        handleDeleteCommand(line, arr);
                        break;
                    default:
                        throw new RonaldoException("No no no. Wrong. Speak properly please.\n");
                }
                saveTasks(arr);
            } catch (RonaldoException e) {
                System.out.println(e.getMessage());
            } finally {
                line = sc.nextLine().trim();
            }
        }
        System.out.println("Goodbye. SIUUUU.");
    }

    private static void saveTasks(ArrayList<Task> arr) {
        try {
            File dir = new File("./data/");
            if (!dir.exists()) {
                dir.mkdirs(); // Create the directory if it doesn't exist
            }

            FileWriter fw = new FileWriter(TEXTFILE_PATH, false);

            for (Task task : arr) {
                fw.write(task.toString() + System.lineSeparator()); // Write each task to a new line
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static Command parseCommand(String line) throws RonaldoException {
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

    private static void handleDeleteCommand(String line, ArrayList<Task> arr) throws RonaldoException {
        try {
            String[] tokens = line.split(" ");
            Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
            arr.remove(task);
            System.out.println(String.format("SIIUUUU I am Cristiano and I've deleted this task:\n%s\n", task));
        } catch (IndexOutOfBoundsException e) {
            throw new RonaldoException(String.format("Don't be stupid. No such task exists! " +
                    "The list only has %d items.\n", arr.size()));
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: delete <task_number>\n");
        }
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
            LocalDate dueDate = LocalDate.parse(due, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Deadline(description, dueDate);
            arr.add(task);
            System.out.println("SIIUUU I am Cristiano and I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));
        } catch (DateTimeParseException e) {
            throw new RonaldoException("Use 'yyyy-MM-dd' to specify dates. For example: 2025-01-26.\n");
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
            LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Event(description, fromDate, toDate);
            arr.add(task);
            System.out.println("SIIUUU I am Cristiano and I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));
        } catch (DateTimeParseException e) {
            throw new RonaldoException("Use 'yyyy-MM-dd' to specify dates. For example: 2025-01-26.\n");
        } catch (Exception e) {
            throw new RonaldoException("Do it like this: event <description> /from <start_time> /to <end_time>\n");
        }
    }
}
