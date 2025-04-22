package gui;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CustomTitleBar extends HBox {
    public CustomTitleBar(Stage stage) {
        this.setStyle("-fx-background-color: linear-gradient(to right, #E55D87, #5FC3E4);");
        this.setSpacing(10);
        this.setPadding(new Insets(5));

        // Create buttons for close, maximize, and minimize
        Button closeBtn = new Button("✕");
        closeBtn.getStyleClass().add("custom-button");
        closeBtn.setOnAction(e -> stage.close());

        Button maxBtn = new Button("⬜");
        maxBtn.getStyleClass().add("custom-button");
        maxBtn.setOnAction(e -> stage.setMaximized(!stage.isMaximized()));

        Button minBtn = new Button("-");
        minBtn.getStyleClass().add("custom-button");
        minBtn.setOnAction(e -> minAnimation(stage));

        // Add buttons to the title bar
        this.getChildren().addAll(minBtn, maxBtn, closeBtn);
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    private void minAnimation(Stage stage) {
        double initialWidth = stage.getWidth();
        double initialHeight = stage.getHeight();

        // Capture the initial position of the stage
        double initialX = stage.getX();
        double initialY = stage.getY();

        // Use Timeline or simple loop to shrink the stage size over time
        final double targetWidth = 10; // Minimized stage width
        final double targetHeight = 10; // Minimized stage height

        // Get screen width and height using Screen class
        Screen screen = Screen.getPrimary(); // Get the primary screen (use getScreens() for multi-monitor setups)
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // Fixed taskbar location (bottom-right corner of the screen)
        double targetX = screenWidth / 2; // Assuming taskbar icon is near the bottom-right corner
        double targetY = screenHeight - 50;

        // Start a simple animation of shrinking the stage size
        new Thread(() -> {
            for (double t = 0; t <= 1; t += 0.05) { // Increment stage shrinking
                final double width = initialWidth - (initialWidth - targetWidth) * t;
                final double height = initialHeight - (initialHeight - targetHeight) * t;

                // Calculate the position of the stage shrinking towards the target position (taskbar location)
                final double xPos = initialX + (targetX - initialX) * t;
                final double yPos = initialY + (targetY - initialY) * t;

                // Set the stage size and position in the JavaFX Application thread
                javafx.application.Platform.runLater(() -> {
                    stage.setWidth(width);
                    stage.setHeight(height);
                    stage.setX(xPos);
                    stage.setY(yPos);
                });

                try {
                    Thread.sleep(20); // Sleep for a small duration to create the animation effect
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            // Finally, minimize the window
            javafx.application.Platform.runLater(() -> stage.setIconified(true));
        }).start();
    }

    public static void restoreWindow(Stage stage) {
        // Reset to original size and position
        stage.setWidth(GUIFeatures.GUI_WIDTH.getValue());
        stage.setHeight(GUIFeatures.GUI_HEIGHT.getValue());

        // Reset to original position (optional)
        stage.setX(100);
        stage.setY(100);

        // Fade-in animation to restore visibility
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.0001), stage.getScene().getRoot());
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        fadeIn.play();
    }
}
