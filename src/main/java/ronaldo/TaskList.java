package ronaldo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> arr;

    public TaskList() {
        arr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
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

    public TaskList findMatchingTasks(String subDescription) {
        return new TaskList(new ArrayList<Task>(this.arr.stream()
                .filter(task -> task.checkMatchingDescription(subDescription)).toList()));
    }
}
