package gui.visualizations;

import gui.ScreenManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EncryptionScreen {
    private final int MAX_LENGTH = 15;
    private final Scene encryptionScreen;
    private boolean isFlashing = false;

    public EncryptionScreen(Stage stage, ScreenManager screenManager, String cipherName) {

        encryptionScreen = ScreenManager.createTemplateScene(stage);

        // Access layout
        BorderPane root = (BorderPane) encryptionScreen.getRoot();
        VBox vBox = (VBox) root.getCenter();

        Button title = new Button(cipherName);
        title.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 48;");
        title.getStyleClass().add("title-label");
        title.setAlignment(Pos.TOP_CENTER);
        title.setFocusTraversable(false);
        title.setMouseTransparent(true);

        TextField input = new TextField();
        input.setPromptText("Enter text to encrypt");
        input.getStyleClass().add("input-field");

        StackPane inputContainer = new StackPane(input);
        inputContainer.setMaxWidth(600);
        inputContainer.setAlignment(Pos.CENTER);

        Rectangle redOverlay = new Rectangle();
        redOverlay.setFill(Color.RED);
        redOverlay.setOpacity(0);
        redOverlay.setMouseTransparent(true);
        redOverlay.widthProperty().bind(input.widthProperty());
        redOverlay.heightProperty().bind(input.heightProperty());
        inputContainer.getChildren().add(redOverlay);

        // TextFormatter to *accept* only valid changes
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getText();
            if (!newText.matches("[a-zA-Z]*")) return null;         // only letters
            if (change.getControlNewText().length() > MAX_LENGTH) {  // enforce length
                playFlash(redOverlay);
                return null;
            }
            return change;
        });
        input.setTextFormatter(textFormatter);

        input.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            // 1) Ignore system shortcuts
            if (evt.isControlDown() || evt.isAltDown() || evt.isMetaDown()) {
                return;
            }

            String ch = evt.getCharacter();
            // 2) Ignore ENTER (submission) so it won't flash
            if ("\r".equals(ch) || "\n".equals(ch)) {
                // you can trigger your encrypt logic here if you like:
                // encrypt.fire();
                return;
            }

            // 3) Now handle “real” invalid letters
            if (!ch.matches("[a-zA-Z]") && !"\b".equals(ch)) {
                evt.consume();
                playFlash(redOverlay);
            }
        });


        Button encrypt = new Button("Encrypt");
        encrypt.setStyle("-fx-font-family: 'SDDystopianDemo';" +
                "-fx-font-size: 32px;" +
                " -fx-padding: 5px 10px;" +
                "-fx-shape: \"M 15,0 L 210,0 L 188,75 L 0,75 Z\";\n");
        encrypt.getStyleClass().add("menu-button");

        encrypt.setOnAction(e -> {
            String text = input.getText();

            switch (cipherName) {
                case "Caesar Cipher":
                    screenManager.switchScene(new CaesarVisual(stage, screenManager, text).getScene());

                case "Vigenere Cipher":

                case "ATBash Cipher":



            }





        });

        input.setOnAction(e -> encrypt.fire());

        HBox titleContainer = new HBox(title);
        titleContainer.setAlignment(Pos.CENTER); // Center horizontally
        titleContainer.setPadding(new Insets(10, 0, 10, 0)); // Optional: add spacing

        root.setTop(titleContainer);

        vBox.getChildren().addAll(inputContainer, encrypt);


        screenManager.applyStyleSheets(encryptionScreen);
    }

    private void playFlash(Rectangle overlay) {
        if (isFlashing) return;  // Do not start a new flash if one is already running

        isFlashing = true;

        // fade in to 40% opacity, then fade back out
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(overlay.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(100), new KeyValue(overlay.opacityProperty(), 0.4)),
                new KeyFrame(Duration.millis(300), new KeyValue(overlay.opacityProperty(), 0))
        );

        tl.setOnFinished(e -> isFlashing = false); // Reset the flag when the animation finishes
        tl.play();
    }


    public Scene getScene() {
        return encryptionScreen;
    }
}
