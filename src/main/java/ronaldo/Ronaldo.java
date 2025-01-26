package ronaldo;

public class Ronaldo {
    private final String filePath;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    public Ronaldo(String filePath) {
        this.filePath = filePath;
    }

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
        default:
            throw new RonaldoException("Unexpected error occurred!\n");
        }
        return true;
    }

    public static void main(String[] args) {
        new Ronaldo("./data/ronaldo.txt").run();
    }

}
