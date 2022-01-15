package lapr.project.controller;

import lapr.project.data.ImportCountriesBordersSeadists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

class NearestPortControllerTest {



    @Test
    void getClosestPort() throws ParseException, IOException {
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromCSVCountry("countries.csv");
        ImportShipsController importShipsController = new ImportShipsController();
        importShipsController.importFromCSV("sships.csv");
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("sports.csv");
        String file = "Input/US202.txt";
        NearestPortController npc = new NearestPortController();
        npc.getClosestPort(file);
    }
}