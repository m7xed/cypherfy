package gui;

import backend.system.ThemeHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsScreen {
    private final Scene settingsScreen;

    public SettingsScreen(Stage stage, ScreenManager screenManager) {
        // Create scene using template method
        settingsScreen = ScreenManager.createTemplateScene(stage);

        BorderPane root = (BorderPane) settingsScreen.getRoot();
        VBox vBox = (VBox) root.getCenter();
        Label title = new Label("Settings");
        title.getStyleClass().add("title-label");

        Button themeDefault = createThemeButton("theme-default.css", "linear-gradient(to right, #e400c9, #2bc8f8)", stage);
        Button themeFlame = createThemeButton("theme-flame.css", "linear-gradient(to right, #FF4E50, #F9D423)", stage);
        Button themeOcean = createThemeButton("theme-ocean.css", "linear-gradient(to right, #ffffff, #dddddd)", stage);
        Button themeSpace = createThemeButton("theme-space.css", "linear-gradient(to right, #0077be, #00c6ff)", stage);

        Button backButton = new Button("Back to Title");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        backButton.getStyleClass().add("menu-button");
        screenManager.applyStyleSheets(settingsScreen);

        backButton.setOnAction(event -> {
            screenManager.switchScene(new TitleScreen(stage, screenManager).getTitleScene());
        });


        HBox themeButtons = new HBox(15, themeDefault, themeFlame, themeOcean, themeSpace);
        themeButtons.setAlignment(Pos.CENTER);



        vBox.getChildren().addAll(title, themeButtons);
        root.setBottom(backButton);
    }

    public Scene getScene() {
        return settingsScreen;
    }

    private Button createThemeButton(String themeFile, String gradientStyle, Stage stage) {
        Button btn = new Button();
        btn.setPrefSize(40, 40);
        btn.setStyle(
                "-fx-background-radius: 20;" +
                        "-fx-min-width: 40px;" +
                        "-fx-min-height: 40px;" +
                        "-fx-max-width: 40px;" +
                        "-fx-max-height: 40px;" +
                        "-fx-background-color: " + gradientStyle + ";"
        );
        btn.setOnAction(e -> {
            // Switch to the new theme first
            ThemeHandler.switchTheme(themeFile, stage.getScene());

            // Then reload styles to ensure the theme is applied correctly
            ScreenManager screenManager = new ScreenManager(stage);
            Scene currentScene = stage.getScene();
            if (currentScene != null) {
                screenManager.reloadStyles(currentScene);  // Ensure the current scene gets its styles refreshed
            }
        });

        return btn;
    }

}