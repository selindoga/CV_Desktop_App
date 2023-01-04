package com.example.cv_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelpController extends Application {

    public Button backButton;
    private Parent root;
    private Stage stage;
    private Scene scene;
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
