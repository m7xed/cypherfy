package gui;

import backend.system.ThemeHandler;
import utils.ButtonHelper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.util.Objects;

public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage, ScreenManager screenManager) {
        // Create scene with helper method
        this.titleScreen = ScreenManager.createTemplateScene(stage);

        String themePath = ThemeHandler.getThemePath();
        this.titleScreen.getStylesheets().add(Objects.requireNonNull(getClass().getResource(themePath)).toExternalForm());

        // Access Layouts
        BorderPane root = (BorderPane) titleScreen.getRoot();
        VBox vBox = (VBox) root.getCenter();

        Button title = new Button("Cypherfy");
        title.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 48;");
        title.getStyleClass().add("title-button");

        title.setOnAction(_ -> {
            screenManager.switchScene(new MenuScreen(stage, screenManager).getMenuScene());
        });

        Button githubButton = ButtonHelper.createIconButton("/Logos/Github_Logo.png", 30);
        Button discordButton = ButtonHelper.createIconButton("/Logos/Discord_Logo.png", 30);

        githubButton.setOnAction(_ -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/m7xed/Cypherfy"));
            } catch (Exception e) {
                e.printStackTrace(); // Or display a user-friendly alert
            }
        });

        Button settingsButton = ButtonHelper.createIconButton("/Logos/Settings_Logo.png", 30);

        HBox menuBar = new HBox(settingsButton);
        menuBar.setStyle("-fx-padding: 6;");
        menuBar.setTranslateX(4);
        menuBar.setTranslateY(5);
        menuBar.setSpacing(5);
        menuBar.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);


        settingsButton.setOnAction(_ -> {
            screenManager.switchScene(new SettingsScreen(stage, screenManager).getScene());
        });
        root.setTop(settingsButton);

        HBox iconBox = new HBox(githubButton, discordButton);
        iconBox.setStyle("-fx-padding: 6;");
        iconBox.setTranslateX(4);
        iconBox.setTranslateY(5);
        iconBox.setSpacing(5);
        iconBox.setAlignment(Pos.TOP_RIGHT);

        Label creditLabel = new Label("Developed by Max Field (@m7xed)");
        creditLabel.getStyleClass().add("credit-label");

        StackPane bottomRightPane = new StackPane();
        bottomRightPane.setPrefHeight(60); // reserve bottom height
        bottomRightPane.getChildren().addAll(iconBox, creditLabel);

        StackPane.setAlignment(iconBox, javafx.geometry.Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(creditLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        root.setBottom(bottomRightPane);

        vBox.getChildren().addAll(title);
        screenManager.applyStyleSheets(titleScreen);
    }

    public Scene getTitleScene() {
        return titleScreen;
    }
}
