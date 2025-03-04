package ronaldo;

/**
 * The main class of the Ronaldo application.
 */
class Ronaldo {
    private final String filePath;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Ronaldo instance with the specified file path.
     *
     * @param filePath The path to the file where the text file containing the tasks will be stored.
     */
    public Ronaldo(String filePath) {
        assert filePath != null : "File path should not be null!";
        assert !filePath.trim().isEmpty() : "File path should not be empty!";
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList();
    }

    /**
     * Executes the Ronaldo application and processes user's commands indefinitely until
     * the user stops the program or the program is killed.
     */
    public String run(String input) {
        assert input != null : "User input should not be null!";
        assert !input.trim().isEmpty() : "User input should not be empty!";
        String output = null;
        try {
            Command command = Parser.parseCommand(input);
            output = this.executeCommand(command, input);
        } catch (RonaldoException e) {
            return e.getMessage();
        }
        storage.saveTasks(tasks);
        return output;
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
    public String executeCommand(Command command, String line) throws RonaldoException{
        Task task;
        String output = "";
        int initialSize;
        switch (command) {
        case LIST:
            output = ui.getAllTasksText(tasks);
            break;
        case MARK:
            task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
            output = ui.getMarkedTaskText(task);
            break;
        case UNMARK:
            task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
            output = ui.getUnmarkedTaskText(task);
            break;
        case DELETE:
            task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
            output = ui.getDeletedTaskText(task);
            break;
        case TODO:
            task = Parser.parseToDoCommand(line);
            initialSize = tasks.size();
            tasks.addTask(task);
            assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a TODO!";
            output = ui.getAddedTaskText(task, tasks.size());
            break;
        case DEADLINE:
            task = Parser.parseDeadlineCommand(line);
            initialSize = tasks.size();
            tasks.addTask(task);
            assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a TODO!";
            output = ui.getAddedTaskText(task, tasks.size());
            break;
        case EVENT:
            task = Parser.parseEventCommand(line);
            initialSize = tasks.size();
            tasks.addTask(task);
            assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a TODO!";
            output = ui.getAddedTaskText(task, tasks.size());
            break;
        case BYE:
            output = ui.getExitText();
            break;
        case FIND:
            String subDescription = Parser.parseFindCommand(line);
            TaskList filteredTaskList = tasks.findMatchingTasks(subDescription);
            output = ui.getFoundTasksText(filteredTaskList);
            break;
        case HELLO:
            output = ui.getWelcomeText();
            break;
        case SORT:
            tasks.sortTasks();
            output = ui.getSortedTasksText(tasks);
            break;
        default:
            throw new RonaldoException("Unexpected error occurred!\n");
        }
        return output;
    }

}
