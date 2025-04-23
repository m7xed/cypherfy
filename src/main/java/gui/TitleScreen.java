package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.stage.Stage;

public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage, ScreenManager screenManager) {
        // Create scene with helper method
        this.titleScreen = screenManager.createTemplateScene(stage);

        // Access Layouts
        BorderPane root = (BorderPane) titleScreen.getRoot();
        CustomTitleBar titleBar = (CustomTitleBar) root.getTop();
        VBox vBox = (VBox) root.getCenter();

        Label title = new Label("Cypherfy");
        title.getStyleClass().add("label");

        Button encryptMenu = new Button("Encrypt Message");
        encryptMenu.getStyleClass().add("encrypt-button");

        encryptMenu.setOnAction(event -> {
            screenManager.switchScene(new MenuScreen(stage, screenManager, titleBar).getScene());
        });

        vBox.getChildren().addAll(title, encryptMenu);
        screenManager.applyStyleSheets(titleScreen);
    }

    public Scene getTitleScene() {
        return titleScreen;
    }
}


