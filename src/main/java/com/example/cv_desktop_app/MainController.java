package com.example.cv_desktop_app;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    protected ArrayList<TreeItem> cvList = new ArrayList<>();

    @FXML
    public TreeView<String> mainView = new TreeView<>();

    public TreeItem<String> rootNode = new TreeItem<>("CVs");

    @FXML
    private TextField CVName;

    @FXML
    private TextField valueText;

    @FXML
    private TextField tagText;

    Person p=new Person();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainView.setEditable(true);

        mainView.setRoot(rootNode);

        rootNode.setExpanded(true);


    }


    @FXML
    void addNameToTable() {

        if (CVName.getText().length() != 0) {
            //p=new Person(CVName.getText());
            p.setName(CVName.getText());
            TreeItem<String> newCV = new TreeItem<>(CVName.getText());
            rootNode.getChildren().add(newCV);
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
    void createCV() {
        TreeItem<String> cv = mainView.getSelectionModel().getSelectedItem();

        if (cv.getParent() == rootNode) {
            cvList.add(cv);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();
        }
    }


    @FXML
    void deleteCV() {
        TreeItem c = mainView.getSelectionModel().getSelectedItem();
        if (c == null) {
            return;
        }
        if (c.getParent() != null) {
            boolean remove = c.getParent().getChildren().remove(c);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("You cannot delete the root!");
            alert.showAndWait();
        }
    }

    @FXML
    void addAttributeToTable() {
        TreeItem<String> cv=mainView.getSelectionModel().getSelectedItem();
        if(valueText.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }
        if(!cv.isLeaf()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();
        }

        else {

            cv.setValue(cv.getValue()+": "+valueText.getText());

            if(cv.getValue().toLowerCase().equals("surname")) {
                p.setSurname(valueText.getText());
            }
            else if(cv.getValue().toLowerCase().equals("birthday")) {
                p.setBirthday(valueText.getText());
            }
            else if(cv.getValue().toLowerCase().equals("education info")) {
                p.setEducationInfo(valueText.getText());
            }
            else if(cv.getValue().toLowerCase().equals("skills")) {
                p.setSkills(valueText.getText());
            }
            else if(cv.getValue().toLowerCase().equals("experience")) {
                p.setExperience(valueText.getText());
            }
            else if(cv.getValue().toLowerCase().equals("publications")) {
                p.setPublications(valueText.getText());
            }

        }


        //attrName.setText("");
        valueText.setText("");
    }

/*

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
*/

    @FXML
    void exit() {
        System.exit(0);
    }


    @FXML
    void showMoreDetails(ActionEvent event) throws Exception {
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
    void returnback(ActionEvent event) throws Exception {
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
    void editprofilepicture(ActionEvent event) throws Exception {
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
    void addTag() {
        TreeItem<String> cv = mainView.getSelectionModel().getSelectedItem();
        if (tagText.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please enter something.");
            alert.showAndWait();
        }

        if (cv.getParent() == rootNode) {
            cv.setValue(cv.getValue() + " tagged as " + tagText.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION!");
            alert.setHeaderText("Something went wrong:/");
            alert.setContentText("Please try another thing.");
            alert.showAndWait();


        }

    }

    @FXML
    void exportCV() {

    }

}