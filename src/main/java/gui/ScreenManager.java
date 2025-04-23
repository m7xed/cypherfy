package gui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class ScreenManager {
    private final Stage stage;

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(Scene newScene) {
        Parent oldRoot = stage.getScene().getRoot();
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), oldRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            // 1) Swap in the new scene
            stage.setScene(newScene);

            // 2) Force a layout update on the new scene
            Platform.runLater(stage::sizeToScene);

            // 3) Fade in the new scene
            Parent newRoot = newScene.getRoot();
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public Scene createTemplateScene(Stage stage){
        // Create layout objects
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        borderPane.setCenter(vBox);

        borderPane.setStyle("-fx-background-color: linear-gradient(to right, #e400c9, #2bc8f8);");

        // Init Scene
        return new Scene(borderPane,
                GUIFeatures.GUI_WIDTH.getValue(),
                GUIFeatures.GUI_HEIGHT.getValue());
    }

    public void applyStyleSheets(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/labelStyles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/buttonStyles.css")).toExternalForm());
    }

    public static Font loadFont(double size) {
        Font font = Font.loadFont(
                ScreenManager.class.getResourceAsStream("/fonts/CypherfyFont.otf"), size
        );
        if (font == null) {
            System.err.println("⚠️ Failed to load /fonts/CypherfyFont.otf");
        }
        return font;
    }

    public static void applyFont(javafx.scene.control.Labeled node, int size) {
        Font font = loadFont(size);
        if (font != null) {
            node.setFont(font);
            System.out.println("Font loaded: " + font.getName());
        } else {
            System.err.println("⚠️ Failed to load /fonts/CypherfyFont.otf");
        }
    }
}


