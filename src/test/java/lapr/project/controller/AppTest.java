package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.DatabaseOperations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void getUsername() {
        App.getInstance().setUsername("username");
        assertEquals("username", App.getInstance().getUsername());
    }

    @Test
    void getDatabaseConnection() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        assertEquals(databaseConnection, App.getInstance().getDatabaseConnection());
    }
}