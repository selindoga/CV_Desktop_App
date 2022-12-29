package com.example.cv_desktop_app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private TableView<Person> attrTable = new TableView<>();

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn birthdayCol;

    @FXML
    private TableColumn eduInfoCol;

    @FXML
    private TextField CVName;

    @FXML
    private TextField attrName;

    @FXML
    private TextField valueText;

    @FXML
    private TextField tagText;

    private final ObservableList<Person> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attrTable.setEditable(true);

        attrTable.setItems(data);

    }

    @FXML
    void addNameToTable() {
        Person p=new Person();
        if (CVName.getText().length() != 0) {
            p.setName(CVName.getText());
            nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
            data.add(p);
            CVName.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }

    }

    @FXML
    void addAttributeToTable() {
        Person p=attrTable.getSelectionModel().getSelectedItem();
        if(attrName.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }
        else if(attrName.getText().equals("Birthday")) {
            p.setBirthday(valueText.getText());
            birthdayCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Birthday"));
        }
        else if(attrName.getText().equals("Education Info")) {
            p.setEducationInfo(valueText.getText());
            eduInfoCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Education Info"));
        }

        else {

        }
    }

    @FXML
    void createCV() {
        Person p=attrTable.getSelectionModel().getSelectedItem();
        MainController.data.add(p);

    }

    @FXML
    void selectCV() {
        TableView.TableViewSelectionModel<Person> cv = attrTable.getSelectionModel();
        if (cv != null) {
        }
    }



}

