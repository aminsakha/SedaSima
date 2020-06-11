package Model;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PageLoader.initStage(primaryStage);
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
