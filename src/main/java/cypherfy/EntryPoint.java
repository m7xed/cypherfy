package cypherfy;

import gui.CustomTitleBar;
import gui.ScreenManager;
import gui.TitleScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class EntryPoint extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TitleScreen titleScreen = new TitleScreen(stage, new ScreenManager(stage));

        stage.setScene(titleScreen.getTitleScene());
        stage.setTitle("Cypherfy");
        stage.setWidth(800);
        stage.setHeight(600);

        stage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                CustomTitleBar.restoreWindow(stage);
            }
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}