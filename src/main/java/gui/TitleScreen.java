package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class TitleScreen {
    private final Scene titleScreen;

    public TitleScreen(Stage stage) {
        BorderPane pane = new BorderPane();

        CustomTitleBar titleBar = new CustomTitleBar(stage);

        Label title = new Label("Cypherfy");
        title.getStyleClass().add("label");
        title.setTranslateY(-100);

        pane.setTop(titleBar);
        pane.setCenter(title);
        pane.setStyle("-fx-background-color: linear-gradient(to right, #E55D87, #5FC3E4);");

        titleScreen = new Scene(pane, 450,450);

        titleScreen.getStylesheets().add(getClass().getResource("/miscStyles.css").toExternalForm());
        titleScreen.getStylesheets().add(getClass().getResource("/menuBarStyle.css").toExternalForm());
    }

    public Scene getTitleScene() {
        return titleScreen;
    }
}


