package ronaldo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the editing and storage of the text file that contains all tasks in the application.
 */
class Storage {
    private String textFilePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     * Ensures that the parent directory exists or creates it if it doesn't.
     *
     * @param textFilePath The path to the file where the text file containing the tasks will be stored.
     */
    public Storage(String textFilePath) {
        this.textFilePath = textFilePath;
        File file = new File(this.textFilePath);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }
    }

    /**
     * Overwrites and saves all tasks in the provided TaskList to the
     * text file in the instance's textFilePath.
     *
     * @param tasks The TaskList containing all tasks to be saved into the text file.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.textFilePath, false);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.getTask(i).toString() + System.lineSeparator()); // Write each task to a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
