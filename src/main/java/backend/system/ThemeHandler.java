package backend.system;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public static void fadeThemeTransition(Scene scene, Runnable onThemeSwitch) {
        WritableImage snapshot = scene.snapshot(null);
        ImageView overlay = new ImageView(snapshot);
        overlay.setFitWidth(scene.getWidth());
        overlay.setFitHeight(scene.getHeight());

        // Add overlay to root
        Pane root = (Pane) scene.getRoot(); // Must be a Pane subclass
        root.getChildren().add(overlay);

        // Switch theme underneath
        onThemeSwitch.run();

        // Animate overlay fade out
        FadeTransition fade = new FadeTransition(Duration.millis(500), overlay);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> root.getChildren().remove(overlay));
        fade.play();
    }
}

