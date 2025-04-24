package gui;

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
        Parent oldRoot = stage.getScene().getRoot();
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), oldRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            // 1) Swap in the new scene
            stage.setScene(newScene);

            // 2) Force a layout update on the new scene
            Platform.runLater(stage::sizeToScene);

            // 3) Fade in the new scene
            Parent newRoot = newScene.getRoot();
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public static Scene createTemplateScene(Stage stage){
        // Create layout objects
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        borderPane.setCenter(vBox);

        borderPane.setStyle("-fx-background-color: linear-gradient(to right, #e400c9, #2bc8f8);");

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

        // Set VBox background to transparent to match the scrollpane's background
        vBox.setStyle("-fx-background-color: linear-gradient(to right, #e400c9, #2bc8f8);");

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true); // make scroll content match width
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // horizontal scrolling off
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: linear-gradient(to right, #e400c9, #2bc8f8); -fx-padding: 0; -fx-border-width: 0;");

        // Make sure the VBox fills the ScrollPane by setting its preferred height
        vBox.setMinHeight(Region.USE_PREF_SIZE); // or Region.USE_COMPUTED_SIZE depending on your needs

        borderPane.setCenter(scrollPane);
        borderPane.setStyle("-fx-background-color: linear-gradient(to right, #e400c9, #2bc8f8);");

        return new Scene(borderPane,
                GUIFeatures.GUI_WIDTH.getValue(),
                GUIFeatures.GUI_HEIGHT.getValue());
    }


    public void applyStyleSheets(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/fieldStyles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/labelStyles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/buttonStyles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/sliderStyles.css")).toExternalForm());

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
}


