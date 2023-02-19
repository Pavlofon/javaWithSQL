package org.example.dbservice;

import org.example.Client;
import org.example.connection.GetConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientDatabaseService implements DatabaseService<Client> {

    GetConnection getConnection = new GetConnection("src/main/resources/database.properties");

    @Override
    public boolean create(Client client) {
        try {
            try (Connection connection = getConnection.createConnection()) {


                Statement statement = connection.createStatement();
                int rows = statement.executeUpdate(
                        "INSERT Clients(ID, First_Name, Second_Name, Last_Name) VALUES (" + "'" + client.getId() + "'" + ","
                                + "'" + client.getFirstName() + "'" + "," + "'" + client.getSecondName() + "'"
                                + "," + "'" + client.getLastName() + "'" + ")");
                System.out.printf("Added %d rows", rows);
                connection.close();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Client read(int id) {
        try {
            try (Connection connection = getConnection.createConnection()) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE ID = " + id);
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
        return null;
    }

    @Override
    public Client update(int id, Client client) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            try (Connection connection = getConnection.createConnection()) {

                Statement statement = connection.createStatement();
                int rows = statement.executeUpdate("DELETE FROM Clients WHERE ID= " + id);
                System.out.printf("Deleted %d rows", rows);
                connection.close();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
            return false;
        }
    }

}
