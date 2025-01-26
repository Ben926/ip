import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static String TEXTFILE_PATH = "";

    public Storage (String textFilePath) {
        TEXTFILE_PATH = textFilePath;
    }

    public static void saveTasks(ArrayList<Task> arr) {
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
}
