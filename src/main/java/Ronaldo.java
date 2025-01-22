import java.util.*;

public class Ronaldo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can I help you?\n");
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input + "\n");
            input = sc.nextLine();
        }
        System.out.println("Goodbye SIUUUU.");
    }
}
