package utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ButtonHelper {

    // Method to create an icon button with an image and size
    public static Button createIconButton(String iconPath, int size) {
        Button button = new Button();

        // Load the image from the specified path
        Image image = new Image(Objects.requireNonNull(ButtonHelper.class.getResourceAsStream(iconPath)));

        // Create an ImageView to scale the image
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);  // Set width of the image
        imageView.setFitHeight(size); // Set height of the image

        // Set the ImageView as the button's graphic
        button.setGraphic(imageView);

        // Style the button to remove the background and border
        button.getStyleClass().add("icon-button");

        return button;  // Return the created button
    }
}
