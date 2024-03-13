package org.example.a7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 320, 240);
        MainController mainController = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("select-program.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
        Stage stage2 = new Stage();
        ProgramController programController = fxmlLoader2.getController();

        mainController.setProgramController(programController);
        stage.setTitle("Porgram Windows");
        stage.setScene(scene2);
        stage.show();

        stage2.setTitle("Main App");
        stage2.setScene(scene1);
        stage2.show();

    }

    public static void main(String[] args) {
        launch();
    }
}