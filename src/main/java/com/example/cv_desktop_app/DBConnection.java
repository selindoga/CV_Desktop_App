package com.example.cv_desktop_app;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DBConnection {
    private static DBConnection instance = null;

    private final String fileName;
    private Connection connection;

    private PreparedStatement insertNameResume, updateSurnameResume, updateBirthdayResume, 
        updateEducationResume, updateSkillResume, updateExperienceResume, updatePublicationResume,
        insertTagResume, selectResume, selectTag, deleteResume, deleteTag;
    

    DBConnection() {
        this.fileName = "info.db";
        File file = new File(fileName);
        boolean firstRun = !file.exists();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            if (firstRun) {
                Statement stat = connection.createStatement();

                stat.executeUpdate("CREATE TABLE RESUME(" +
                        "Name TEXT NOT NULL," +
                        "Surname TEXT," +
                        "Birthday TEXT ," +
                        "Education TEXT ," +
                        "Skill TEXT," +
                        "Experience TEXT ," +
                        "Publication TEXT )");


                stat.executeUpdate("CREATE TABLE TAG(" +
                        "Name TEXT NOT NULL," +
                        "Tag TEXT NOT NULL)");

            }

            System.out.println("DB INIT");
                insertNameResume = connection.prepareStatement("INSERT INTO RESUME (Name) VALUES (?)");
                updateSurnameResume = connection.prepareStatement("UPDATE RESUME SET Surname=? WHERE Name=?");
                updateBirthdayResume = connection.prepareStatement("UPDATE RESUME SET Birthday=? WHERE Name=?");
                updateEducationResume = connection.prepareStatement("UPDATE RESUME SET Education=? WHERE Name=?");
                updateSkillResume = connection.prepareStatement("UPDATE RESUME SET Skill=? WHERE Name=?");
                updateExperienceResume = connection.prepareStatement("UPDATE RESUME SET Experience=? WHERE Name=?");
                updatePublicationResume = connection.prepareStatement("UPDATE RESUME SET Publication=? WHERE Name=?");
                insertTagResume = connection.prepareStatement("INSERT INTO TAG (Name, Tag) VALUES (?, ?)");
                selectResume = connection.prepareStatement("SELECT * FROM RESUME");
                selectTag = connection.prepareStatement("SELECT Tag FROM TAG WHERE Name=?");
                deleteResume = connection.prepareStatement("DELETE FROM RESUME WHERE Name=?");
                deleteTag = connection.prepareStatement("DELETE FROM TAG WHERE Name=?");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }

    };

    public static DBConnection getInstance() {

        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    public void addResumeName2DB(String name) {
        try {
                insertNameResume.setString(1, name);
                insertNameResume.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData2DB(String name, String attr, String val) {
        try {
                System.out.println("updateData2DB");
                PreparedStatement selectedStatement = null;
                switch(attr){
                        case "surname":
                                selectedStatement = updateSurnameResume;
                                System.out.println("surname");
                                break;
                        case "birthday":
                                selectedStatement = updateBirthdayResume;
                                break;
                        case "education":
                                selectedStatement = updateEducationResume;
                                break;
                        case "skill":
                                selectedStatement = updateSkillResume;
                                break;
                        case "experience":
                                selectedStatement = updateExperienceResume;
                                break;
                        case "publication":
                                selectedStatement = updatePublicationResume;
                                break;

                }

                selectedStatement.setString(1, val);
                selectedStatement.setString(2, name);
                selectedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTag2DB(String name, String tag) {
        try {
                insertTagResume.setString(1, name);
                insertTagResume.setString(2, tag);
                insertTagResume.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getResumeData(){
        ArrayList<Person> all_resumes = new ArrayList<>();
        try {
                selectResume.execute();
                ResultSet rs = selectResume.executeQuery();

                while (rs.next()) {
                        String name = rs.getString(1);
                        String surname = rs.getString(2);
                        String birthday = rs.getString(3);
                        String education = rs.getString(4);
                        String skill = rs.getString(5);
                        String experience = rs.getString(6);
                        String publication = rs.getString(7);

                        Person resume = new Person(name, 
                                                   Objects.toString(surname, ""), 
                                                   new ArrayList<>(), 
                                                   Objects.toString(birthday, ""), 
                                                   Objects.toString(education, ""), 
                                                   Objects.toString(skill, ""), 
                                                   Objects.toString(experience, ""), 
                                                   Objects.toString(publication, ""));
                        all_resumes.add(resume);
                }
        } catch (SQLException e) {
                System.out.println(e);
        }

        return all_resumes;
    }

    public ArrayList<String> getTag(String name){
        ArrayList<String> tags = new ArrayList<>();
        try {
                selectTag.setString(1, name);
                selectTag.execute();
                ResultSet rs = selectTag.executeQuery();

                while (rs.next()) {
                        String tag = rs.getString(1);
                        tags.add(tag);
                }
        } catch (SQLException e) {
                e.printStackTrace();
        }

        return tags;
    }

        public void deleteResumeFromDB(String name) {
                try {
                        deleteResume.setString(1, name);
                        deleteResume.executeUpdate();
                        this.deleteTagFromDB(name);
                } catch (SQLException e) {
                e.printStackTrace();
                }
        }

        public void deleteTagFromDB(String name) {
                try {
                        deleteTag.setString(1, name);
                        deleteTag.executeUpdate();

                } catch (SQLException e) {
                e.printStackTrace();
                }
        }
}
