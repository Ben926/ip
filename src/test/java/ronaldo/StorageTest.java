package ronaldo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void storageInitialisation_noFileExists_fileCreated() {
        String testFilePath = "./test_data/test.txt";
        new Storage(testFilePath);

        File dir = new File("./test_data");
        assertTrue(dir.exists());
    }

    @Test
    public void saveTasks_tasksGiven_tasksSavedIntoTextFile() throws Exception {
        String testFilePath = "./test_data/test_tasks.txt";
        Storage storage = new Storage(testFilePath);

        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("buy food"));
        tasks.addTask(new Deadline("return book", LocalDate.parse("2025-01-26")));
        tasks.addTask(new Event("party",
                LocalDate.parse("2025-01-25"), LocalDate.parse("2025-01-27")));
        storage.saveTasks(tasks);

        File file = new File(testFilePath);
        assertTrue(file.exists());
        assertEquals(String.join(" ", Files.readAllLines(file.toPath())),
                "[T] [ ] buy food [D] [ ] return book (by: Jan 26 2025) " +
                        "[E] [ ] party (from: Jan 25 2025 to: Jan 27 2025)");
    }

    @AfterEach
    public void cleanup() {
        File dir = new File("./test_data/");
        deleteDirectories(dir);
    }

    public void deleteDirectories(File dir) {
        if (dir != null && dir.exists()) {
            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete(); // Delete files in the directory
                } else {
                    deleteDirectories(file);
                }
            }
            dir.delete(); // Delete the directory itself
        }
    }
}
