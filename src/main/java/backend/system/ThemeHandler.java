package backend.system;

import javafx.scene.Scene;

import java.util.Objects;

public class ThemeHandler {

    private static String currentTheme;

    public static void initDefaultTheme() {
        String theme = ConfigManager.getTheme();
        currentTheme = (theme == null || theme.isBlank()) ? "theme-default.css" : theme;
    }

    public static String getThemePath() {
        return "/Styles/" + currentTheme;
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void switchTheme(String newTheme, Scene currentScene) {
        if (newTheme != null && !newTheme.isBlank()) {
            currentTheme = newTheme;

            // Apply new stylesheet
            currentScene.getStylesheets().clear();
            currentScene.getStylesheets().add(Objects.requireNonNull(
                    ThemeHandler.class.getResource(getThemePath())
            ).toExternalForm());

            // Update config
            ConfigManager.setTheme(newTheme);
        }
    }

}

