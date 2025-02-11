package ronaldo;

import java.util.Comparator;

class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            return ((Deadline) t1).getDueDate().compareTo(((Deadline) t2).getDueDate());
        } else if (t1 instanceof Event && t2 instanceof Event) {
            return ((Event) t1).getFromDate().compareTo(((Event) t2).getFromDate());
        } else if (t1 instanceof Deadline && !(t2 instanceof Deadline)) {
            return -1; // Prioritize deadlines
        } else if (t1 instanceof Event && !(t2 instanceof Deadline || t2 instanceof Event)) {
            return -1; // Prioritize events over ToDos
        }
        return 0;
    }
}
