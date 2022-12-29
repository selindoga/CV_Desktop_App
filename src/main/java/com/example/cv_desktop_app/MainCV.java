package com.example.cv_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainCV extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("CV Desktop App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
