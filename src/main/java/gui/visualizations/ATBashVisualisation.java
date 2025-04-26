package gui.visualizations;

import gui.MenuScreen;
import gui.ScreenManager;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ATBashVisualisation {
    private final int MAX_LENGTH = 15;
    private final Scene ATBashScreen;
    private boolean isFlashing = false;

    // Used for debouncing the animation start
    private PauseTransition animationPause;

    public ATBashVisualisation(Stage stage, ScreenManager screenManager) {
        ATBashScreen = ScreenManager.createTemplateScene(stage);
        BorderPane root = (BorderPane) ATBashScreen.getRoot();

        // Title
        Button title = new Button("ATBash Cipher");
        title.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 48;");
        title.getStyleClass().add("title-label");
        title.setFocusTraversable(false);
        title.setMouseTransparent(true);

        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(30, 0, 20, 0));
        root.setTop(titleContainer);

        // Center container for input + output
        VBox centerBox = new VBox(25); // spacing between input and output
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));

        // Input field with red overlay flash
        TextField input = new TextField();
        input.setPromptText("Enter text to encrypt");
        input.getStyleClass().add("input-field");
        input.setAlignment(Pos.CENTER);

        StackPane inputContainer = new StackPane(input);
        inputContainer.setMaxWidth(500);
        inputContainer.setAlignment(Pos.CENTER);

        Rectangle redOverlay = new Rectangle();
        redOverlay.setFill(Color.RED);
        redOverlay.setOpacity(0);
        redOverlay.setMouseTransparent(true);
        redOverlay.widthProperty().bind(input.widthProperty());
        redOverlay.heightProperty().bind(input.heightProperty());
        inputContainer.getChildren().add(redOverlay);

        // Live result display as a list of individual Text nodes
        HBox visualOutputBox = new HBox(10);
        visualOutputBox.setAlignment(Pos.CENTER);
        visualOutputBox.setMaxWidth(500);
        visualOutputBox.setStyle("-fx-alignment: center;");  // Ensures center alignment in its container

        // Wrap the result box inside a StackPane to maintain position
        StackPane resultContainer = new StackPane(visualOutputBox);
        resultContainer.setMaxWidth(500);
        resultContainer.setAlignment(Pos.CENTER);

        centerBox.getChildren().addAll(inputContainer, resultContainer);
        root.setCenter(centerBox);

        // Input formatting and filtering
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getText();
            if (!newText.matches("[a-zA-Z]*")) return null;
            if (change.getControlNewText().length() > MAX_LENGTH) {
                playFlash(redOverlay);
                return null;
            }
            return change;
        });
        input.setTextFormatter(textFormatter);

        input.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (evt.isControlDown() || evt.isAltDown() || evt.isMetaDown()) return;

            String ch = evt.getCharacter();
            if ("\r".equals(ch) || "\n".equals(ch)) return;

            if (!ch.matches("[a-zA-Z]") && !"\b".equals(ch)) {
                evt.consume();
                playFlash(redOverlay);
            }
        });

        // Debounce and delay before triggering the animation
        animationPause = new PauseTransition(Duration.millis(300)); // 300ms delay
        animationPause.setOnFinished(event -> {
            animateTextFlipping(visualOutputBox, applyAtbash(input.getText()));
        });

        // Update cipher output live with flip animation for each letter
        input.textProperty().addListener((obs, oldVal, newVal) -> {
            animationPause.playFromStart(); // Restart the delay on every new input
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo';");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(_ -> screenManager.switchScene(new MenuScreen(stage, screenManager).getMenuScene()));

        HBox bottomBox = new HBox(backButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20));
        root.setBottom(bottomBox);

        screenManager.applyStyleSheets(ATBashScreen);
    }

    private String applyAtbash(String input) {
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                sb.append((char) ('Z' - (ch - 'A')));
            } else if (Character.isLowerCase(ch)) {
                sb.append((char) ('z' - (ch - 'a')));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private void animateTextFlipping(HBox visualOutputBox, String newText) {
        // Clear previous output
        visualOutputBox.getChildren().clear();

        List<Text> letters = new ArrayList<>();
        for (char c : newText.toCharArray()) {
            Text letter = new Text(String.valueOf(c));
            letter.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 36;");
            letters.add(letter);
        }

        // Add the letters to the output box
        visualOutputBox.getChildren().addAll(letters);

        // Create flip animation for each letter
        for (int i = 0; i < letters.size(); i++) {
            Text letter = letters.get(i);
            letter.setOpacity(0); // Initially make letters invisible

            RotateTransition rotateOut = new RotateTransition(Duration.millis(250), letter);
            rotateOut.setFromAngle(0);
            rotateOut.setToAngle(90);
            int finalI = i;
            rotateOut.setOnFinished(e -> {
                letter.setOpacity(1); // Make it visible after rotation
                letter.setText(String.valueOf(newText.charAt(finalI))); // Update text to flipped value

                RotateTransition rotateIn = new RotateTransition(Duration.millis(250), letter);
                rotateIn.setFromAngle(-90);
                rotateIn.setToAngle(0);
                rotateIn.setCycleCount(1);
                rotateIn.play();
            });
            rotateOut.setCycleCount(1);
            rotateOut.setDelay(Duration.millis(i * 100)); // Stagger the animations
            rotateOut.play();
        }
    }

    private void playFlash(Rectangle overlay) {
        if (isFlashing) return;

        isFlashing = true;
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(overlay.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(100), new KeyValue(overlay.opacityProperty(), 0.4)),
                new KeyFrame(Duration.millis(300), new KeyValue(overlay.opacityProperty(), 0))
        );
        tl.setOnFinished(_ -> isFlashing = false);
        tl.play();
    }

    public Scene getScene() {
        return ATBashScreen;
    }
}
