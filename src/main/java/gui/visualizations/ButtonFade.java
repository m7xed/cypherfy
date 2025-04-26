package gui.visualizations;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonFade {
    private static final double ANIMATION_DURATION = 0.5;

    // Fade out to red
    public static void fadeSubmitButtonOut(Button submitButton) {
        Platform.runLater(() -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), submitButton);
            fadeOut.setFromValue(1);   // Fully visible
            fadeOut.setToValue(0);     // Fully invisible
            fadeOut.setOnFinished(event -> submitButton.setOpacity(0)); // Ensure opacity is set to 0 once the fade is done
            fadeOut.play();
        });
    }

    public static void fadeSubmitButtonIn(Button button) {
        Timeline fadeToDefault = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(button.styleProperty(), "-fx-font-family: 'SDDystopianDemo'; -fx-background-color: #FF4C4C;")), // Stay red
                new KeyFrame(Duration.seconds(ANIMATION_DURATION),
                        new KeyValue(button.styleProperty(), "-fx-font-family: 'SDDystopianDemo'; -fx-background-color: linear-gradient(to right, #2575fc, #6a11cb)")) // Reset to default style (gradient)
        );

        fadeToDefault.play();  // Play the animation
    }

}
