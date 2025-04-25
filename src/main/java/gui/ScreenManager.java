package gui;

import backend.system.ThemeHandler;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class ScreenManager {
    private final Stage stage;

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(Scene newScene) {
        newScene.getStylesheets().clear();

        applyStyleSheets(newScene);

        String themePath = ThemeHandler.getThemePath();
        System.out.println("Applying theme from path: " + themePath);
        System.out.println("Resource URL: " + getClass().getResource(themePath));
        newScene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource(themePath)).toExternalForm()
        );


        Scene currentScene = stage.getScene();

        if (currentScene == null) {
            // No scene currently on stage — no fade needed
            stage.setScene(newScene);
            Platform.runLater(stage::sizeToScene);
            return;
        }

        Parent oldRoot = currentScene.getRoot();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), oldRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(_ -> {
            stage.setScene(newScene);
            Platform.runLater(stage::sizeToScene);

            Parent newRoot = newScene.getRoot();
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }


    public static Scene createTemplateScene(Stage stage) {
        // Create layout objects
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        borderPane.setCenter(vBox);

        borderPane.getStyleClass().add("themed-background");

        // Init Scene
        return new Scene(borderPane,
                GUIFeatures.GUI_WIDTH.getValue(),
                GUIFeatures.GUI_HEIGHT.getValue());
    }

    public static Scene createVisualisationScene(Stage stage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);

        // Set VBox background too transparent to match the scroll pane's background
        borderPane.getStyleClass().add("themed-background");


        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true); // make scroll content match width
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // horizontal scrolling off
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.getStyleClass().add("themed-scroll-pane");

        // Make sure the VBox fills the ScrollPane by setting its preferred height
        vBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        borderPane.setCenter(scrollPane);
        borderPane.getStyleClass().add("themed-background");

        return new Scene(borderPane,
                GUIFeatures.GUI_WIDTH.getValue(),
                GUIFeatures.GUI_HEIGHT.getValue());
    }


    public void applyStyleSheets(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/base.css")).toExternalForm());
    }

    public static Font loadFont(double size) {
        Font font = Font.loadFont(
                ScreenManager.class.getResourceAsStream("/fonts/CypherfyFont.otf"), size
        );
        if (font == null) {
            System.err.println("⚠️ Failed to load /fonts/CypherfyFont.otf");
        }
        return font;
    }

    public static void applyFont(javafx.scene.control.Labeled node, int size) {
        Font font = loadFont(size);
        if (font != null) {
            node.setFont(font);
            System.out.println("Font loaded: " + font.getName());
        } else {
            System.err.println("⚠️ Failed to load /fonts/CypherfyFont.otf");
        }
    }

    public void reloadStyles(Scene scene) {
        // Clear previous stylesheets
        scene.getStylesheets().clear();
        System.out.println("Stylesheets cleared.");

        // Apply base styles
        applyStyleSheets(scene);
        System.out.println("Base styles applied.");

        // Apply the theme stylesheet
        String themePath = ThemeHandler.getThemePath();
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource(themePath)).toExternalForm());
        System.out.println("Theme stylesheet applied: " + themePath);

        // Apply CSS and force layout recalculation
        scene.getRoot().applyCss();  // Forces the CSS to be applied to the scene
        scene.getRoot().requestLayout();  // Forces a layout recalculation

        // Delay layout update to allow UI to refresh properly
        Platform.runLater(() -> {
            scene.getRoot().requestLayout();  // Request layout again if needed
        });
    }
}



