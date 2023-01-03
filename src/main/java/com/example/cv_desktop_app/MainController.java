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
import java.util.Locale;
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

        ArrayList<Person> resumes = DBConnection.getInstance().getResumeData();

        for(Person p: resumes){
            ArrayList<String> tags = DBConnection.getInstance().getTag(p.getName());
            String tags_text = "";
            for(String t: tags){
                tags_text += t + " ";
            }
            tags_text = tags_text.trim();

            TreeItem<String> newCV = new TreeItem<>(p.getName() + " - " + tags_text);
            rootNode.getChildren().add(newCV);
            TreeItem<String> newSurname = new TreeItem<>("Surname: " + p.getSurname());
            TreeItem<String> newBirthday = new TreeItem<>("Birthday: " + p.getBirthday());
            TreeItem<String> newEduInfo = new TreeItem<>("Education Info: " + p.getEducationInfo());
            TreeItem<String> newSkills = new TreeItem<>("Skills: " + p.getSkills());
            TreeItem<String> newExp = new TreeItem<>("Experience: " + p.getExperience());
            TreeItem<String> newPub = new TreeItem<>("Publications: " + p.getPublications());
            newCV.getChildren().addAll(newSurname,newBirthday,newEduInfo,newSkills,newExp,newPub);
        }
    }


    @FXML
    void addNameToTable() {

        if (CVName.getText().length() != 0) {
            //p=new Person(CVName.getText());
            p.setName(CVName.getText());
            DBConnection.getInstance().addResumeName2DB(CVName.getText());
            
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
        TreeItem<String> cv = mainView.getSelectionModel().getSelectedItem();
        int node_level = mainView.getTreeItemLevel(cv);
        if(node_level==1){
            if (cv == null) {
                return;
            }
            if (cv.getParent() != null) {
                boolean remove = cv.getParent().getChildren().remove(cv);
                if(remove){
                    String cv_view_text = cv.getValue();
                    String[] cv_parts = cv_view_text.split("-");
                    String cv_name = cv_parts[0];
                    cv_name = cv_name.trim();
                    DBConnection.getInstance().deleteResumeFromDB(cv_name);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Something went wrong:/");
                alert.setContentText("You cannot delete the root!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong:/");
            String level_name = "";
            if(node_level == 0){
                level_name = "root";
            } else if(node_level == 2){
                level_name = "attribute";
            }
            alert.setContentText("You cannot delete the " + level_name + "!");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteTag() {
        TreeItem<String> cv = mainView.getSelectionModel().getSelectedItem();
        int node_level = mainView.getTreeItemLevel(cv);
        if(node_level==1){
            if (cv == null) {
                return;
            }
            if (cv.getParent() != null) {
                String cv_view_text = cv.getValue();
                String[] cv_parts = cv_view_text.split("-");
                String cv_name = cv_parts[0];
                cv_name = cv_name.trim();
                cv.setValue(cv_name + " - ");
                DBConnection.getInstance().deleteTagFromDB(cv_name);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Something went wrong:/");
                alert.setContentText("You cannot delete the root!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong:/");
            String level_name = "";
            if(node_level == 0){
                level_name = "root";
            } else if(node_level == 2){
                level_name = "attribute";
            }
            alert.setContentText("You cannot delete the " + level_name + "!");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteAttribute() {
        TreeItem<String> cv = mainView.getSelectionModel().getSelectedItem();
        int node_level = mainView.getTreeItemLevel(cv);
        if(node_level==2){
            if (cv == null) {
                return;
            }
            if (cv.getParent() != null) {
                String parent_string = cv.getParent().getValue();
                String[] parent_string_split = parent_string.split("-");
                String cv_name = parent_string_split[0];
                cv_name = cv_name.trim();

                String attr_text = cv.getValue();
                String[] attr_text_splits = attr_text.split(":");
                String attr_name = attr_text_splits[0];
                attr_name = attr_name.trim();
                cv.setValue(attr_name);
                String db_attr_name = "";
                if(attr_name.toLowerCase().equals("surname")) {
                    db_attr_name = "surname";
                }
                else if(attr_name.toLowerCase().equals("birthday")) {
                    db_attr_name = "birthday";
                }
                else if(attr_name.toLowerCase(Locale.forLanguageTag("en")).equals("education info")) {
                    db_attr_name = "education";
                }
                else if(attr_name.toLowerCase().equals("skills")) {
                    db_attr_name = "skill";
                }
                else if(attr_name.toLowerCase().equals("experience")) {
                    db_attr_name = "experience";
                }
                else if(attr_name.toLowerCase().equals("publications")) {
                    db_attr_name =  "publication";
                }

                DBConnection.getInstance().updateData2DB(cv_name, db_attr_name, null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Something went wrong:/");
                alert.setContentText("You cannot delete the root!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong:/");
            String level_name = "";
            if(node_level == 0){
                level_name = "root";
            } else if(node_level == 1){
                level_name = "resume";
            }
            alert.setContentText("You cannot delete the " + level_name + "!");
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

            String[] row = cv.getValue().split(":");
            String attrName = row[0].trim();
            String attrVal = "";
            if(attrName.toLowerCase().equals("surname") || attrName.toLowerCase().equals("birthday")){
            } else{
                try{
                    attrVal = row[1].trim();
                } catch(Exception e){
                }
            }
            attrVal += " " + valueText.getText();
            attrVal = attrVal.trim();

            String attr_String = attrName+": "+attrVal;
            cv.setValue(attr_String);

            String parent_string = cv.getParent().getValue();
            String[] parent_string_split = parent_string.split("-");
            String cv_name = parent_string_split[0];
            cv_name = cv_name.trim();
            
            if(attrName.toLowerCase().equals("surname")) {
                p.setSurname(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "surname", attrVal);
            }
            else if(attrName.toLowerCase().equals("birthday")) {
                p.setBirthday(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "birthday", attrVal);
            }
            else if(attrName.toLowerCase(Locale.forLanguageTag("en")).equals("education info")) {
                p.setEducationInfo(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "education", attrVal);
            }
            else if(attrName.toLowerCase().equals("skills")) {
                p.setSkills(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "skill", attrVal);
            }
            else if(attrName.toLowerCase().equals("experience")) {
                p.setExperience(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "experience", attrVal);
            }
            else if(attrName.toLowerCase().equals("publications")) {
                p.setPublications(valueText.getText());
                DBConnection.getInstance().updateData2DB(cv_name, "publication", attrVal);
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
            String[] row = cv.getValue().split("-");
            String cvname = row[0].trim();
            String tags = "";
            try{
                tags = row[1].trim();
            } catch(Exception e){
            }
            tags += " " + tagText.getText();
            tags = tags.trim();

            String cv_main_string = cvname+" - "+tags;
            cv.setValue(cv_main_string);
            DBConnection.getInstance().addTag2DB(cvname, tagText.getText());
            tagText.clear();
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