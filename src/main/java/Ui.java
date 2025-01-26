import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private static String TEXTFILE_PATH = "";

    public Ui(String textFilePath) {
        TEXTFILE_PATH = textFilePath;
    }

    public void printWelcomeText() {
        System.out.println("SIIUUUUUU!!! Ronaldo here.");
        System.out.println("How can the GOAT help you?\n");
    }

    public void readCommand() {
        String line = sc.nextLine().trim();
    }

}
