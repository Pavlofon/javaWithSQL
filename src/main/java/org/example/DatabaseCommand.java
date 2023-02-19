package org.example;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class DatabaseCommand {


    public static void createClient(Client client) {
        try {
            try (Connection connection = getConnection()) {

                Statement statement = connection.createStatement();
                int rows = statement.executeUpdate(
                        "INSERT Students(First_Name, Second_Name, Last_Name) VALUES ("
                                + "'" + client.getFirstName() + "'" + "," + "'" + client.getSecondName() + "'"
                                + "," + "'" + client.getLastName() + "'" + ")");
                System.out.printf("Added %d rows", rows);
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }


    public static void deleteClient(Client client){
        try {
            try (Connection connection = getConnection()) {

                Statement statement = connection.createStatement();
                int rows = statement.executeUpdate(
                        "DELETE FROM Students WHERE First_Name = "
                                + "'" + client.getFirstName() + "'" + "AND Second_Name = " + "'" + client.getSecondName() + "'"
                                + "AND Last_Name = " + "'" + client.getLastName() + "'" );
                System.out.printf("Deleted %d rows", rows);
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }


    public static Client getClient(int id){
        try {
            try (Connection connection = getConnection()) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Students WHERE ID = " + id  );
                resultSet.next();
                    Client client = new Client(
                            resultSet.getInt("ID"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Second_Name"),
                            resultSet.getString("Last_Name"));
                    connection.close();
                    return client;

            }

        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return new Client(0,null,null,null);
    }
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
