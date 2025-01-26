import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String TEXTFILE_PATH = "";

    public Storage (String textFilePath) {
        TEXTFILE_PATH = textFilePath;
        File file = new File(TEXTFILE_PATH);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(TEXTFILE_PATH, false);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.getTask(i).toString() + System.lineSeparator()); // Write each task to a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
