package gui;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class ScreenManager {
    private final Stage stage;

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(Scene newScene) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            stage.setScene(newScene);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public Scene createTemplateScene(Stage stage){
        // Create layout objects
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);

        // Create title bar object
        CustomTitleBar titleBar = new CustomTitleBar(stage);

        borderPane.setCenter(vBox);
        borderPane.setTop(titleBar);

        borderPane.setStyle("-fx-background-color: linear-gradient(to right, #E55D87, #5FC3E4);");

        // Init Scene
        return new Scene(borderPane);
    }

    public void applyStyleSheets(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/miscStyles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/menuBarStyle.css")).toExternalForm());
    }
}


