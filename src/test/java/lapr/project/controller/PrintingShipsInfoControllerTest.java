package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PrintingShipsInfoControllerTest {

    @Test
    void getShips() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        PrintingShipsInfoController controller2 = new PrintingShipsInfoController();
        controller2.getShips();
    }
}