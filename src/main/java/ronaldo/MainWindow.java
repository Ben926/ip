package ronaldo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ronaldo ronaldo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/RonaldoSIU.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRonaldo(Ronaldo r) {
        this.ronaldo = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ronaldo.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Check if input is "bye", and exit if true
        if (input.equalsIgnoreCase("bye")) {
            System.exit(0); // Close the application
        }

    }
}
