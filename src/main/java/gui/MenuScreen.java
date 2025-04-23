package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MenuScreen {
    private final Scene menuScene;

    public MenuScreen(Stage stage, ScreenManager screenManager) {


        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPrefSize(GUIFeatures.GUI_WIDTH.getValue(), GUIFeatures.GUI_HEIGHT.getValue());

        // Create Buttons for Ciphers
        Button atbashButton = new Button("Atbash Cipher");
        Button caesarButton = new Button("Caesar Cipher");
        Button vigenereButton = new Button("Vigenère Cipher");
        Button backButton = new Button("Back to Title");

        // Apply fonts using ScreenManager
        ScreenManager.applyFont(atbashButton, 24);
        ScreenManager.applyFont(caesarButton, 24);
        ScreenManager.applyFont(vigenereButton, 24);
        ScreenManager.applyFont(backButton, 24);

        // Add style classes (if you have styles for buttons)
        atbashButton.getStyleClass().add("cipher-button");
        caesarButton.getStyleClass().add("cipher-button");
        vigenereButton.getStyleClass().add("cipher-button");
        backButton.getStyleClass().add("back-button");

        // Button actions
        atbashButton.setOnAction(event -> {
            // Go to Atbash Cipher screen (implement this screen)
//            screenManager.switchScene(new AtbashCipherScreen(stage, screenManager).getScene());
        });

        caesarButton.setOnAction(event -> {
            // Go to Caesar Cipher screen (implement this screen)
//            screenManager.switchScene(new CaesarCipherScreen(stage, screenManager).getScene());
        });

        vigenereButton.setOnAction(event -> {
            // Go to Vigenère Cipher screen (implement this screen)
//            screenManager.switchScene(new VigenereCipherScreen(stage, screenManager).getScene());
        });

        backButton.setOnAction(event -> {
            // Go back to the Title screen
//            screenManager.switchScene(new TitleScreen(stage, screenManager).getTitleScene());
        });

        // Add buttons to the VBox layout
        vBox.getChildren().addAll(atbashButton, caesarButton, vigenereButton, backButton);

        vBox.applyCss();
        vBox.layout();

        menuScene = new Scene(vBox,
                GUIFeatures.GUI_WIDTH.getValue(),
                GUIFeatures.GUI_HEIGHT.getValue()
        );

        screenManager.applyStyleSheets(menuScene);
    }

    public Scene getMenuScene() {
        return menuScene;
    }
}
