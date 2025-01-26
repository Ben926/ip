package ronaldo;

/**
 * The main class of the Ronaldo application.
 */
public class Ronaldo {
    private final String filePath;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    /**
     * Constructs a Ronaldo instance with the specified file path.
     *
     * @param filePath The path to the file where the text file containing the tasks will be stored.
     */
    public Ronaldo(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Executes the Ronaldo application and processes user commands indefinitely until
     * the user stops the program or the program is killed.
     */
    public void run() {
        ui = new Ui();
        Storage storage = new Storage(filePath);
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

    /**
     * Executes a specific command based on user input, according to the Command enum types.
     *
     * @param command The command to execute, parsed from user input by the Parser class.
     * @param line The full input string from the user.
     * @return A boolean indicating whether the application should continue running, which is true
     * by default and false only if the command is of type BYE.
     * @throws RonaldoException If an unexpected error occurs while executing the command.
     */
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
            ui.printUnmarkedTask(task);
            break;
        case DELETE:
            task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
            ui.printDeletedTask(task);
            break;
        case TODO:
            task = Parser.parseToDoCommand(line);
            tasks.addTask(task);
            ui.printAddedTask(task, tasks.size());
            break;
        case DEADLINE:
            task = Parser.parseDeadlineCommand(line);
            tasks.addTask(task);
            ui.printAddedTask(task, tasks.size());
            break;
        case EVENT:
            task = Parser.parseEventCommand(line);
            tasks.addTask(task);
            ui.printAddedTask(task, tasks.size());
            break;
        case BYE:
            ui.printExitText();
            return false;
        case FIND:
            String subDescription = Parser.parseFindCommand(line);
            TaskList filteredTaskList = tasks.findMatchingTasks(subDescription);
            ui.printFoundTasks(filteredTaskList);
            break;
        default:
            throw new RonaldoException("Unexpected error occurred!\n");
        }
        return true;
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Ronaldo("./data/ronaldo.txt").run();
    }
}
