package gui.visualizations;

import backend.CaesarCipher;
import gui.ScreenManager;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CaesarVisual {
    private final Scene caesarVisual;
    private final Button submitButton;

    public CaesarVisual(Stage stage, ScreenManager screenManager, String data) {
        caesarVisual = ScreenManager.createVisualisationScene(stage);

        // Access layout
        BorderPane root = (BorderPane) caesarVisual.getRoot();
        ScrollPane scrollPane = (ScrollPane) root.getCenter();

        VBox centerBox = new VBox(10); // Added spacing between components
        centerBox.setAlignment(Pos.CENTER); // Align to the left
        centerBox.setPadding(new Insets(20));
        root.setCenter(centerBox);

        // Label for slider title
        Label sliderTitle = new Label("Select Caesar Cipher Key");
        sliderTitle.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 18px;");
        centerBox.getChildren().add(sliderTitle);

        // Slider setup
        Slider keySlider = new Slider(1, 6, 1);
        keySlider.setMajorTickUnit(1);
        keySlider.setMinorTickCount(0);
        keySlider.setShowTickLabels(true);
        keySlider.setShowTickMarks(true);
        keySlider.setSnapToTicks(true);
        keySlider.setStyle("-fx-font-size: 24px;");
        keySlider.getStyleClass().add("slider");
        centerBox.getChildren().add(keySlider);

        // Create a label to show the current value of the slider
        Label sliderValue = new Label("Key: " + keySlider.getValue());
        sliderValue.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 14px;");
        centerBox.getChildren().add(sliderValue);

        // Update the slider value label as the slider moves
        keySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText("Key: " + String.format("%.0f", newValue));  // Update text dynamically
        });

        // Buttons
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo';");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(_ -> {
            screenManager.switchScene(new EncryptionScreen(stage, screenManager, CipherNames.CAESAR.getValue()).getScene());
        });

        submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-family: 'SDDystopianDemo';");
        submitButton.getStyleClass().add("menu-button");
        submitButton.setOnAction(_ -> {
            playVisualisation(data, (int) keySlider.getValue());
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonBox = new HBox(10, backButton, spacer, submitButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);
        root.setBottom(buttonBox);

        screenManager.applyStyleSheets(caesarVisual);
    }

    public void playVisualisation(String data, int key) {

        submitButton.setDisable(true); // Disable the button to prevent multiple clicks
        buttonFade.fadeSubmitButtonOut(submitButton); // Optionally fade out the button

        SequentialTransition st = new SequentialTransition();

        VBox vBox = (VBox) ((BorderPane) caesarVisual.getRoot()).getCenter();
        vBox.getChildren().clear();

        // Add the original data at the top of the list
        Label originalDataLabel = new Label("Original Data: " + data);
        originalDataLabel.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 16px;");
        vBox.getChildren().add(originalDataLabel);

        // Add a separator (break)
        Separator separator1 = new Separator();
        vBox.getChildren().add(separator1);

        // Process the ciphered data based on the key
        Character[] encrypted = new Character[0];
        char[] charArray = data.toCharArray();
        Character[] dataSplit = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            dataSplit[i] = charArray[i];
        }

        // Add passes to the list (each pass is a step in the ciphering)
        for (int i = 0; i < key; i++) {
            HBox listBox = new HBox();
            listBox.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(500), listBox);
            fade.setFromValue(0);
            fade.setToValue(1);
            st.getChildren().add(fade);
            listBox.setSpacing(10);
            listBox.setAlignment(Pos.BASELINE_LEFT); // Align labels within the HBox to the left

            CaesarCipher caesarCipher = new CaesarCipher();
            encrypted = caesarCipher.caesarCipher(dataSplit);

            Label passLabel = new Label("Pass " + (i + 1) + ":");
            passLabel.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 18px;");

            listBox.getChildren().add(passLabel);
            for (Character c : encrypted) {
                Label label = new Label(c.toString());
                ScreenManager.applyFont(label, 45);
                listBox.getChildren().add(label);
            }

            vBox.getChildren().add(listBox);

            // Add a separator (break) after each pass
            if (i < key - 1) {
                Separator separator = new Separator();
                vBox.getChildren().add(separator);
            }
        }

        // Add a separator before the final result
        Separator separatorBetweenPassesAndFinal = new Separator();
        vBox.getChildren().add(separatorBetweenPassesAndFinal);

        // Add final result
        HBox finalResultBox = new HBox();
        finalResultBox.setOpacity(0);
        FadeTransition fadeFinal = new FadeTransition(Duration.millis(1000), finalResultBox);
        fadeFinal.setFromValue(0);
        fadeFinal.setToValue(1);
        st.getChildren().add(fadeFinal);
        finalResultBox.setSpacing(10);
        finalResultBox.setAlignment(Pos.CENTER); // Align final result label to the left
        Label finalResultLabel = new Label("Final Result:");
        finalResultLabel.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 18px;");
        finalResultBox.getChildren().add(finalResultLabel);

        for (Character c : encrypted) {
            Label label = new Label(c.toString());
            ScreenManager.applyFont(label, 45);
            finalResultBox.getChildren().add(label);
        }

        vBox.getChildren().add(finalResultBox);

        st.play();
    }
    public Scene getScene() {
        return caesarVisual;
    }
}
