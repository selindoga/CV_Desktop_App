package com.example.cv_desktop_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private final TableView<Person> mainTable = new TableView<>();

    @FXML
    private TableColumn nameCol;

    public static final ObservableList<Person> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainTable.setEditable(true);

        mainTable.setItems(data);

        nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));

    }


    @FXML
    void delete() {
        data.remove(mainTable.getSelectionModel().getSelectedItem());
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


}
