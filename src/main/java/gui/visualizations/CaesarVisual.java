package gui.visualizations;
import backend.CaesarCipher;
import gui.ScreenManager;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CaesarVisual {
    private final Scene caesarVisual;

    public CaesarVisual(Stage stage, ScreenManager screenManager, String data) {
        caesarVisual = ScreenManager.createVisualisationScene(stage);

        // Access layout
        BorderPane root = (BorderPane) caesarVisual.getRoot();

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));
        root.setCenter(centerBox);

        // Slider setup
        Slider keySlider = new Slider(1, 8, 1);
        keySlider.setMajorTickUnit(1);
        keySlider.setMinorTickCount(0);
        keySlider.setShowTickLabels(true);
        keySlider.setShowTickMarks(true);
        keySlider.setSnapToTicks(true);
        keySlider.getStyleClass().add("slider");
        root.setTop(keySlider);

        // Buttons
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo';");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(_ -> {
            screenManager.switchScene(new EncryptionScreen(stage, screenManager, CipherNames.CAESAR.getValue()).getScene());

        });

        Button submitButton = new Button("Submit");
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

        SequentialTransition st = new SequentialTransition();

        VBox vBox = (VBox) ((BorderPane) caesarVisual.getRoot()).getCenter();
        vBox.getChildren().clear();

        // Split data into Chars
        Character[] encrypted = new Character[0];
        char[] charArray = data.toCharArray();
        Character[] dataSplit = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            dataSplit[i] = charArray[i];
        }

        for (int i = 0; i < key; i++) {
            HBox listBox = new HBox();
            listBox.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(500), listBox);
            fade.setFromValue(0);
            fade.setToValue(1);
            st.getChildren().add(fade);
            listBox.setSpacing(10);
            listBox.setAlignment(Pos.CENTER);
            CaesarCipher caesarCipher = new CaesarCipher();
            encrypted = caesarCipher.caesarCipher(dataSplit);

            for (Character c : encrypted) {
                Label label = new Label(c.toString());
                ScreenManager.applyFont(label, 45);
                listBox.getChildren().add(label);
            }
            vBox.getChildren().add(listBox);
        }
        st.play();
    }


    public Scene getScene() {
        return caesarVisual;
    }
}
