package com.example.cv_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCV extends Application {

   /*
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("CV Desktop App");
        stage.setScene(scene);
        stage.show();
    }


    */



    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage.setTitle("CV Desktop App");
            stage.setScene(new Scene(root, 650, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        launch(args);

    }
}
