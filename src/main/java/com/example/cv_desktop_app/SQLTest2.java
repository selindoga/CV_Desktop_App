/*
package com.example.cv_desktop_app;


import java.sql.*;
import java.util.Scanner;

public class SQLTest2 {
    public static void main(String[] args) {

        for (int i = 0; i < 6; i++) {
            Scanner input = new Scanner(System.in);
            int choice;
            System.out.println("View for enter 1 . Add for enter 2 . Delete for enter 3. Edit for enter 4 and choose the value.");
            choice = input.nextInt();

            if (choice == 1) {
                String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl);
                    String Sql = "SELECT * FROM cv";

                    Statement statement = connection.createStatement();

                    ResultSet result = statement.executeQuery(Sql);


                    while (result.next()) {

                        String name = result.getString("name");
                        String email = result.getString("email");

                        System.out.println(name + "|" + email);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            } else if (choice == 2) {

                String name;
                String email;
                System.out.println("This is a test method. The name and the email you enter will be stored in database");
                //SqliteTest.insert("","");
                name = input.next();
                email = input.next();
                SQLTest2.Insert(name, email);

            } else if (choice == 3) {

                String deleteName;
                System.out.println("Enter the name of the person of the cv you want to delete.");
                deleteName = input.next();

                SQLTest2.Delete(deleteName);
                try {
                    String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
                    Connection connection = DriverManager.getConnection(jdbcUrl);
                    String Sql = "DELETE FROM cv WHERE name = ?";
                    PreparedStatement statement = connection.prepareStatement(Sql);
                    statement.setString(1, deleteName);
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (choice == 4)
            {
                String name = "";
                int id;
                String new_name;
                String email = "";
                System.out.println("Enter the name of the cv that you want to edit.");
                name =input.next();
                System.out.println("What do you want it to be changed?");
                new_name = input.next();

               // SqliteTest.Edit(new_name);

                try {
                    String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
                    Connection connection = DriverManager.getConnection(jdbcUrl);
                    String sql = "UPDATE cv SET name =? WHERE new_name IN (SELECT name FROM cv)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1,new_name);
                    statement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }




    public static void Edit(String new_name) {
        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
        String sql = "UPDATE cv SET name =? WHERE name=?";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, new_name);
            pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void Insert(String name, String email) {
        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
        String sql = "INSERT INTO cv(name,email) VALUES(?,?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Delete(String name) {
        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\dogal\\se302-2\\se 302\\src\\cv.db";
        String sql = "DELETE FROM cv WHERE name = ?";
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1,name);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}


 */