package com.example.cv_desktop_app;

import java.sql.*;
import java.util.Scanner;


public class cvCreation {
    public static void main(String[] args) {

        System.out.println("make choice");
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        if (choice == 1) {
            //Class.forName("org.sqlite.JDBC");
            // Connect to the database
            String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                // Create the table with the "name" and "surname" columns
                String sql = "CREATE TABLE cv (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, surname TEXT NOT NULL)";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                // Add any additional columns and values
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Enter a column name (or enter to finish): ");
                    String column = scanner.nextLine();
                    if (column.isEmpty()) {
                        break;
                    }
                    System.out.print("Enter a data type (e.g. INTEGER, TEXT): ");
                    String type = scanner.nextLine();
                    sql = "ALTER TABLE cv ADD COLUMN " + column + " " + type;
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(sql);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // Insert a row into the table
                System.out.print("Enter a name: ");
                String name = scanner.nextLine();
                System.out.print("Enter a surname: ");
                String surname = scanner.nextLine();
                sql = "INSERT INTO cv(name, surname) VALUES(?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, surname);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {
            cvCreation.addValues();
        } else if (choice == 3) {
            cvCreation.deleteTable();
        }
        else if(choice==4){
            cvCreation.view();
        }
        else if(choice==5){

            System.out.println("Enter id");
            int id=input.nextInt();
            System.out.println("Enter the new name:");
            String newname = input.next();
            cvCreation.Edit(newname,id);
        }
        else if(choice==6){
            System.out.println("Enter id");
            int id=input.nextInt();
            cvCreation.Delete(id);
        }

    }

    public static void addValues() {
        String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";
        String newName;
        String newSurname;
        try (Connection conn = DriverManager.getConnection(url)) {

            Scanner input = new Scanner(System.in);
            newName = input.next();
            newSurname = input.next();

// Set the values for the prepared statement
            String sql = "UPDATE cv SET name = ?, surname = ? WHERE name=" + newName + "AND surname=" + newSurname + "";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, newSurname);


// Set the values for the prepared statement

// Execute the update
            pstmt.executeUpdate();

// Close the prepared statement and connection
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTable() {
        String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            // Create the table with the "name" and "surname" columns
            String sql = "DROP TABLE cv";
        }catch (SQLException e){
            e.getMessage();
        }
    }


    public static void view(){
        String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            // Create the table with the "name" and "surname" columns
            String sql = "SELECT* FROM cv";
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public static void Edit(String new_name, int id) {
        String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";

        try {
            String sql = "UPDATE cv SET name = ? WHERE id=?";
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, new_name);
            statement.setInt(2, id);
            statement.executeUpdate();

            sql = "SELECT * FROM cv WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int cvId = result.getInt("id");
                String name = result.getString("name");
                System.out.println(cvId + "|" + name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Delete(int id) {
        String url = "jdbc:sqlite:/C:\\Users\\dogal\\cvCreation\\database.db";
        String sql = "DELETE FROM cv WHERE id = ?" ;
        try{
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1,id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}