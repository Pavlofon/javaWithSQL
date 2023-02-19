package org.example;

import org.example.dbservice.ClientDatabaseService;

public class Main {
    public static void main(String[] args) {

        ClientDatabaseService clientDatabaseService = new ClientDatabaseService();
        Client client1 = new Client(1111, "Stas23", "Aristov23", "Viktorovich23");
        clientDatabaseService.update(1111, client1);


    }

}