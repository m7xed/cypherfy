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
        settingsScreen = ScreenManager.createTemplateScene(stage);

        BorderPane root = (BorderPane) settingsScreen.getRoot();
        VBox centerVBox = new VBox(30);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setPadding(new javafx.geometry.Insets(40, 20, 20, 20)); // Top-heavy padding for space
        root.setCenter(centerVBox);

        Label title = new Label("Settings");
        title.getStyleClass().add("title-label");

        // Section title for theme selection
        Label themeLabel = new Label("Choose Theme:");
        themeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        HBox themeButtons = new HBox(20,
                createThemeButton("theme-default.css", "linear-gradient(to right, #e400c9, #2bc8f8)", stage),
                createThemeButton("theme-flame.css", "linear-gradient(to right, #FF4E50, #F9D423)", stage),
                createThemeButton("theme-ocean.css", "linear-gradient(to right, #ffffff, #dddddd)", stage),
                createThemeButton("theme-space.css", "linear-gradient(to right, #0077be, #00c6ff)", stage)
        );
        themeButtons.setAlignment(Pos.CENTER);

        VBox themeSection = new VBox(10, themeLabel, themeButtons);
        themeSection.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back to Title");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(event ->
                screenManager.switchScene(new TitleScreen(stage, screenManager).getTitleScene())
        );

        // Add all components
        centerVBox.getChildren().addAll(title, themeSection);

        // Back button in bottom section with padding
        VBox bottomBox = new VBox(backButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new javafx.geometry.Insets(20));
        root.setBottom(bottomBox);

        screenManager.applyStyleSheets(settingsScreen);
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
            Scene scene = stage.getScene();
            if (scene != null) {
                ThemeHandler.fadeThemeTransition(scene, () -> {
                    ThemeHandler.switchTheme(themeFile, scene);
                    new ScreenManager(stage).reloadStyles(scene);
                });
            }
        });


        return btn;
    }

}