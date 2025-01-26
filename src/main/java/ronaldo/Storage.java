package ronaldo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String textFilePath;

    public Storage (String textFilePath) {
        this.textFilePath = textFilePath;
        File file = new File(this.textFilePath);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }
    }

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
