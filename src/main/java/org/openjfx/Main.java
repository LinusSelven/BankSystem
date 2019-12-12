package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        loader.setControllerFactory(param -> new Controller(model));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
