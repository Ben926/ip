import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> arr;

    public TaskList() {
        arr = new ArrayList<>();
    }

    public void addTask(Task task) {
        arr.add(task);
    }

    public Task deleteTask(int index) {
        return arr.remove(index);
    }

    public Task getTask(int index) {
        return arr.get(index);
    }

    public Task markTask(int index) {
        Task task = arr.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = arr.get(index);
        task.markAsUndone();
        return task;
    }

    public int size() {
        return arr.size();
    }

    public boolean isEmpty() {
        return arr.isEmpty();
    }
}
