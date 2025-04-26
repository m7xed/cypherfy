package gui;

import gui.visualizations.ATBashVisualisation;
import gui.visualizations.CipherNames;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuScreen {
    private final Scene menuScene;

    public MenuScreen(Stage stage, ScreenManager screenManager) {
        menuScene = ScreenManager.createTemplateScene(stage);
        BorderPane root = (BorderPane) menuScene.getRoot();

        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10, 10, 10, 20));
        hBox.setAlignment(Pos.TOP_LEFT);

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.TOP_LEFT);

        Label infoLabel = new Label("Hover over a cipher to see information.");
        infoLabel.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 18; -fx-padding: 10;");
        infoLabel.setWrapText(true); // Enable text wrapping for the label

        ScrollPane infoScrollPane = new ScrollPane();
        infoScrollPane.setContent(infoLabel);
        infoScrollPane.setFitToWidth(true); // Ensures the content fits the width of the ScrollPane
        infoScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        infoScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        HBox.setHgrow(infoScrollPane, Priority.ALWAYS);
        infoScrollPane.setMaxWidth(Double.MAX_VALUE); // Allow the ScrollPane to expand as much as possible

        Button atbashButton = new Button("Atbash Cipher");
        Button caesarButton = new Button("Caesar Cipher");
        Button vigenereButton = new Button("Vigenère Cipher");
        Button backButton = new Button("Back to Title");

        atbashButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        caesarButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        vigenereButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");
        backButton.setStyle("-fx-font-family: 'SDDystopianDemo'; -fx-font-size: 28;");

        atbashButton.getStyleClass().add("menu-button");
        caesarButton.getStyleClass().add("menu-button");
        vigenereButton.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");
        screenManager.applyStyleSheets(menuScene);

        atbashButton.setOnAction(event -> {
            screenManager.switchScene(new ATBashVisualisation(stage, screenManager).getScene());
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

        atbashButton.setOnMouseEntered(event -> infoLabel.setText("The Atbash Cipher is a substitution cipher where the alphabet is reversed. Example: Z -> A, Y -> B."));
        caesarButton.setOnMouseEntered(event -> infoLabel.setText("The Caesar Cipher is a substitution cipher with a fixed shift in the alphabet. Example: Shift by 3 -> A -> D."));
        vigenereButton.setOnMouseEntered(event -> infoLabel.setText("The Vigenère Cipher uses a keyword to shift letters in a more complex manner. Example: KEYWORD is the key."));

        atbashButton.setOnMouseExited(event -> infoLabel.setText("Hover over a cipher to see information."));
        caesarButton.setOnMouseExited(event -> infoLabel.setText("Hover over a cipher to see information."));
        vigenereButton.setOnMouseExited(event -> infoLabel.setText("Hover over a cipher to see information."));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        vBox.getChildren().addAll(atbashButton, caesarButton, vigenereButton, spacer, backButton);

        hBox.getChildren().addAll(vBox, infoScrollPane);
        root.setCenter(hBox);

        screenManager.applyStyleSheets(menuScene);
    }

    public Scene getMenuScene() {
        return menuScene;
    }
}
