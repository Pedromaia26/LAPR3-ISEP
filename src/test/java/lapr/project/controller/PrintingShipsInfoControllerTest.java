package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PrintingShipsInfoControllerTest {

    Company c = new Company();

    @Test
    void getShips() throws IOException {
        ImportShipsController controller = new ImportShipsController(c);
        controller.importFromCSV("sships.csv");
        PrintingShipsInfoController controller2 = new PrintingShipsInfoController(c);
        controller2.getShips();
    }

    @Test
    void getShips2() throws IOException {
        ImportShipsController controller = new ImportShipsController(c);
        controller.importFromCSV("TestFiles/test107_sameTravelledDistance");
        PrintingShipsInfoController controller2 = new PrintingShipsInfoController(c);
        controller2.getShips();
    }
}