package com.example.cv_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
public class HelpController extends Application {

    public Button backButton;
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void BackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void start(Stage primaryStage) {
    }
}
