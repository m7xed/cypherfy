package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import utils.ButtonHelper;

import java.awt.Desktop;
import java.net.URI;

public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage, ScreenManager screenManager) {
        // Create scene with helper method
        this.titleScreen = ScreenManager.createTemplateScene(stage);

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

        HBox iconBox = new HBox(githubButton, discordButton);
        iconBox.setStyle("-fx-padding: 6;");
        iconBox.setTranslateX(4);
        iconBox.setTranslateY(5);
        iconBox.setSpacing(5);
        iconBox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

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
