import java.util.*;

public class Ronaldo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can I help you?\n");
        ArrayList<Task> arr = new ArrayList<>();
        String line = sc.nextLine().trim();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, arr.get(i)));
                }
                System.out.println("");
            } else if (line.startsWith("mark")) {
                String[] tokens = line.split(" ");
                Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsDone();
                System.out.println(String.format("Ok, marked this " +
                        "task as done:\n%s\n", task));
            } else if (line.startsWith("unmark")) {
                String[] tokens = line.split(" ");
                Task task = arr.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsUndone();
                System.out.println(String.format("Ok, marked this " +
                        "task as undone:\n%s\n", task));
            } else if (line.startsWith("todo")){
                String description = line.substring(5).trim();
                Task task = new ToDo(description);
                arr.add(task);
                System.out.println("Added this task: ");
                System.out.println(task);
                System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));

            } else if (line.startsWith("deadline")) {
                String[] tokens = line.split("/by");
                String description = tokens[0].substring(9).trim();
                String due = tokens[1].trim();
                Task task = new Deadline(description, due);
                arr.add(task);
                System.out.println("Added this task: ");
                System.out.println(task);
                System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));

            } else if (line.startsWith("event")) {
                String[] tokens = line.split("/from|/to");
                String description = tokens[0].substring(6).trim();
                String from = tokens[1].trim();
                String to = tokens[2].trim();
                Task task = new Event(description, from, to);
                arr.add(task);
                System.out.println("Added this task: ");
                System.out.println(task);
                System.out.println(String.format("Now you have %d tasks in the list\n", arr.size()));

            } else {
                System.out.println("Sorry, I don't recognise your command\n");
            }
            line = sc.nextLine().trim();
        }
        System.out.println("Goodbye SIUUUU.");
    }


}
