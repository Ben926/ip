import java.util.*;

public class Ronaldo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can I help you?\n");
        ArrayList<Task> arr = new ArrayList<>();
        String input = sc.nextLine();
        String[] tokens = input.split(" ");
        while (!tokens[0].equals("bye")) {
            if (tokens[0].equals("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, arr.get(i)));
                }
                System.out.println("");
            } else if (tokens[0].equals("mark")) {
                Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsDone();
                System.out.println(String.format("Ok, marked this " +
                        "task as done:\n%s\n", task));
            } else if (tokens[0].equals("unmark")) {
                Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsUndone();
                System.out.println(String.format("Ok, marked this " +
                        "task as undone:\n%s\n", task));
            } else {
                arr.add(new Task(input));
                System.out.println("Added: " + input + "\n");
            }
            input = sc.nextLine();
            tokens = input.split(" ");
        }
        System.out.println("Goodbye SIUUUU.");
    }

    public static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), this.description);
        }
    }
}
