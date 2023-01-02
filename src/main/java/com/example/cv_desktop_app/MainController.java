package com.example.cv_desktop_app;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventObject;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public TreeView<String> mainView = new TreeView<>();

    public TreeItem<String> rootNode = new TreeItem<>("CVs");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainView.setEditable(true);

        mainView.setRoot(rootNode);

        rootNode.setExpanded(true);



    }


    @FXML
    void delete() {
        TreeItem c =mainView.getSelectionModel().getSelectedItem();
        if (c == null) {
            return;
        }
        if (c.getParent() != null) {
            boolean remove = c.getParent().getChildren().remove(c);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("You cannot delete the root!");
            alert.showAndWait();
        }
    }



    @FXML
    void switchToCreateWindow () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("create.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);


        stage.show();
    }

    @FXML
    void switchToEditWindow () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);


        stage.show();
    }

    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void showmoredetails (ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cvpage.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);


        stage.show();
    }
    @FXML
    void returnback (ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);


        stage.show();
    }
    @FXML
    void editprofilepicture (ActionEvent event) throws Exception{
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);


        stage.show();
    }











}