package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionalMessagesControllerTest {

    @Test
    void messagePeriod() throws ParseException, IOException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "sships.csv";
        importShipsController.importFromCSV(importFile);
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        String file = "TestFiles/positionalMsg.txt";
        positionalMessagesController.message(file);
    }

    @Test
    void messageDate() throws ParseException, IOException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "sships.csv";
        importShipsController.importFromCSV(importFile);
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        String file = "TestFiles/positionalMsgDate.txt";
        positionalMessagesController.message(file);
    }

    @Test
    void messageInexistentMMSIDate() throws ParseException, IOException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "sships.csv";
        importShipsController.importFromCSV(importFile);
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        String file = "TestFiles/positionalMsgWrongMMSIDate";
        positionalMessagesController.message(file);

    }

    @Test
    void messageInexistentMMSIPeriod() throws ParseException, IOException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "sships.csv";
        importShipsController.importFromCSV(importFile);
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        String file = "TestFiles/positionalMsgWrongMMSIPeriod";
        positionalMessagesController.message(file);
    }

    @Test
    void message3Dates() throws IOException, ParseException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "sships.csv";
        importShipsController.importFromCSV(importFile);
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        String file = "TestFiles/positionalMsg3Dates";
        positionalMessagesController.message(file);
    }


}