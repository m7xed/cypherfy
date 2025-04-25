package cypherfy;

import backend.system.ConfigManager;
import gui.GUIFeatures;
import gui.ScreenManager;
import backend.system.ThemeHandler;
import gui.TitleScreen;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class EntryPoint extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ConfigManager configManager = new ConfigManager();
        String theme = configManager.getTheme();
        if (theme == null || theme.isBlank()) {
            System.err.println("⚠️  Failed to config theme. Using Default Theme.");
            theme = "theme-default.css";
        }
        ThemeHandler.initDefaultTheme();

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/CypherfyFont.otf"), 48);
        if (customFont == null) {
            System.err.println("⚠️  Failed to load /fonts/CypherfyFont.otf");
        }

        TitleScreen titleScreen = new TitleScreen(stage, new ScreenManager(stage));

        stage.setScene(titleScreen.getTitleScene());
        stage.setTitle("Cypherfy");
        // Stage Dimensions
        stage.setWidth(GUIFeatures.GUI_WIDTH.getValue());
        stage.setHeight(GUIFeatures.GUI_HEIGHT.getValue());
        stage.setMinWidth(GUIFeatures.GUI_MIN_WIDTH.getValue());
        stage.setMinHeight(GUIFeatures.GUI_MIN_HEIGHT.getValue());
        stage.setResizable(true);

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/Logos/Cypherfy_Logo.png"))));


        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}