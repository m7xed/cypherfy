package gui;

import backend.system.ConfigManager;
import backend.system.ThemeHandler;
import gui.visualizations.CipherNames;
import gui.visualizations.EncryptionScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuScreen {
    private final Scene menuScene;

    public MenuScreen(Stage stage, ScreenManager screenManager) {
        // Create scene using template method
        menuScene = ScreenManager.createTemplateScene(stage);

        // Retrieve the root layout (BorderPane) and center VBox from the scene
        BorderPane root = (BorderPane) menuScene.getRoot();
        VBox vBox = (VBox) root.getCenter();
        vBox.setAlignment(Pos.TOP_LEFT);  // So the spacer can push the back button down
        vBox.setPadding(new Insets(10, 10, 10, 20));
        vBox.setSpacing(15);
        // Buttons
        Button atbashButton = new Button("Atbash Cipher");
        Button caesarButton = new Button("Caesar Cipher");
        Button vigenereButton = new Button("Vigenère Cipher");
        Button backButton = new Button("Back to Title");

        // Font styles
        atbashButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        caesarButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        vigenereButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");


        // CSS styles

        atbashButton.getStyleClass().add("menu-button");
        caesarButton.getStyleClass().add("menu-button");
        vigenereButton.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");
        screenManager.applyStyleSheets(menuScene);

        // Actions
        atbashButton.setOnAction(event -> {
            screenManager.switchScene(new EncryptionScreen(stage, screenManager, CipherNames.ATBASH.getValue()).getScene());
        });

        caesarButton.setOnAction(event -> {
            screenManager.switchScene(new EncryptionScreen(stage, screenManager, CipherNames.CAESAR.getValue()).getScene());
        });

        vigenereButton.setOnAction(event -> {
            screenManager.switchScene(new EncryptionScreen(stage, screenManager, CipherNames.VIGENERE.getValue()).getScene());
        });

        backButton.setOnAction(event -> {
            screenManager.switchScene(new TitleScreen(stage, screenManager).getTitleScene());
        });

        // Spacer to push backButton down
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Add all components
        vBox.getChildren().addAll(atbashButton, caesarButton, vigenereButton, spacer, backButton);

        // Apply CSS styles
        screenManager.applyStyleSheets(menuScene);
    }

    public Scene getMenuScene() {
        return menuScene;
    }
}
