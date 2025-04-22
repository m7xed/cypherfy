package gui;

import com.sun.tools.javac.Main;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage) {
        BorderPane pane = new BorderPane();

        CustomTitleBar titleBar = new CustomTitleBar(stage);

        Label title = new Label("Cypherfy");

        pane.setTop(titleBar);
        pane.setCenter(title);
        pane.setStyle("-fx-background-color: linear-gradient(to right, #E55D87, #5FC3E4);");

        titleScreen = new Scene(pane, 450,450);

        titleScreen.getStylesheets().add(getClass().getResource("/menuBarStyle.css").toExternalForm());
    }

    public Scene getTitleScene() {
        return titleScreen;
    }
}


