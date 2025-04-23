package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import javafx.stage.Stage;


public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage, ScreenManager screenManager) {
        // Create scene with helper method
        this.titleScreen = screenManager.createTemplateScene(stage);

        // Access Layouts
        BorderPane root = (BorderPane) titleScreen.getRoot();
        VBox vBox = (VBox) root.getCenter();

        Button title = new Button("Cypherfy");
        title.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 48;");
        title.getStyleClass().add("title-button");

        title.setOnAction(event -> {
            screenManager.switchScene(new MenuScreen(stage, screenManager).getMenuScene());
        });

        vBox.getChildren().addAll(title);
        screenManager.applyStyleSheets(titleScreen);
    }

    public Scene getTitleScene() {
        return titleScreen;
    }
}


