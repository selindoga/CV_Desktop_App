package com.example.cv_desktop_app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private TreeView<String> treeView = new TreeView<>();

    @FXML
    private TextField CVName;

    @FXML
    private TextField attrName;

    @FXML
    private TextField valueText;

    @FXML
    private TextField tagText;

    TreeItem<String> rootNode1 = new TreeItem<>("CVs");
    HashMap<String,String> inputs=new HashMap<>();

    MainController main=new MainController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setEditable(true);

        treeView.setRoot(rootNode1);

        rootNode1.setExpanded(true);

    }

    @FXML
    void addNameToTable() {

        if (CVName.getText().length() != 0) {
            //Person p=new Person(CVName.getText());
            TreeItem<String> newCV = new TreeItem<>(CVName.getText());
            rootNode1.getChildren().add(newCV);
            TreeItem<String> newSurname = new TreeItem<>("Surname");
            TreeItem<String> newBirthday = new TreeItem<>("Birthday");
            TreeItem<String> newEduInfo = new TreeItem<>("Education Info");
            TreeItem<String> newSkills = new TreeItem<>("Skills");
            TreeItem<String> newExp = new TreeItem<>("Experience");
            TreeItem<String> newPub = new TreeItem<>("Publications");
            newCV.getChildren().addAll(newSurname,newBirthday,newEduInfo,newSkills,newExp,newPub);

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
        TreeItem<String> cv=treeView.getSelectionModel().getSelectedItem();
        if(valueText.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }
        if(cv.isLeaf()==false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();
        }

        else {
            //inputs.put(attrName.getText(), valueText.getText());
            cv.setValue(cv.getValue()+": "+valueText.getText());

        }


        attrName.setText("");
        valueText.setText("");
    }

    @FXML void addTag() {
        TreeItem<String> cv=treeView.getSelectionModel().getSelectedItem();
        if(tagText.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }

        if(cv.getParent()==rootNode1) {
            cv.setValue(cv.getValue()+" tagged as "+tagText.getText());
        }

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();


        }


        tagText.setText("");
    }


    @FXML
    void createCV() {
        TreeItem<String> cv = treeView.getSelectionModel().getSelectedItem();

        if (cv.getParent() == rootNode1) {
            main.rootNode.getChildren().add(cv);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();
        }
    }


}
