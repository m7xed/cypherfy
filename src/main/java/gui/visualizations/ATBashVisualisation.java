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

public class ATBashVisualisation {
    private final int MAX_LENGTH = 15;
    private final Scene ATBashScreen;
    private boolean isFlashing = false;
    private String lastOutputText = "";

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
        VBox centerBox = new VBox(25);
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

        // Label for output
        Text outputLabel = new Text("Encrypted Result:");
        outputLabel.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 24;");

        // Live result display as a list of individual Text nodes
        HBox visualOutputBox = new HBox(10);
        visualOutputBox.setAlignment(Pos.CENTER);
        visualOutputBox.setMaxWidth(500);
        visualOutputBox.setStyle("-fx-alignment: center;");

        StackPane resultContainer = new StackPane(visualOutputBox);
        resultContainer.setMaxWidth(500);
        resultContainer.setAlignment(Pos.CENTER);

        centerBox.getChildren().addAll(inputContainer, outputLabel, resultContainer);
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
        animationPause = new PauseTransition(Duration.millis(300));
        animationPause.setOnFinished(event -> {
            animateTextFlipping(visualOutputBox, applyAtbash(input.getText()));
        });

        // Update cipher output live with flip animation for each letter
        input.textProperty().addListener((obs, oldVal, newVal) -> {
            animationPause.playFromStart();
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
        int oldLength = lastOutputText.length();
        int newLength = newText.length();

        if (newLength < oldLength) {
            visualOutputBox.getChildren().clear();
            lastOutputText = "";
            animateTextFlipping(visualOutputBox, newText);
            return;
        }

        for (int i = oldLength; i < newLength; i++) {
            char c = newText.charAt(i);
            Text letter = new Text(String.valueOf(c));
            letter.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 36;");
            letter.setOpacity(0);
            visualOutputBox.getChildren().add(letter);

            RotateTransition rotateOut = new RotateTransition(Duration.millis(250), letter);
            rotateOut.setFromAngle(0);
            rotateOut.setToAngle(90);

            rotateOut.setOnFinished(e -> {
                letter.setOpacity(1);
                RotateTransition rotateIn = new RotateTransition(Duration.millis(250), letter);
                rotateIn.setFromAngle(-90);
                rotateIn.setToAngle(0);
                rotateIn.play();
            });

            rotateOut.setDelay(Duration.millis((i - oldLength) * 100));
            rotateOut.play();
        }

        lastOutputText = newText;
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
