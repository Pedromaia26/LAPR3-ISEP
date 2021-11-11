package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lapr.project.model.*;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TopNshipsPerKmControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");

    @Test
    void TopNshipsPerKmControllertest() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        controller2.printNshipsMostKm("TestFiles/test106_positive");
    }

    @Test
    void TopNshipsPerKmControllerTestApp(){
        BSTShip bst = new BSTShip();
        bst.insert(ship);
        bst.insert(ship1);
        bst.insert(ship2);
        App.getInstance().getCompany().setBstShips(bst);
        TopNshipsPerKmController controller = new TopNshipsPerKmController();
        assertEquals(App.getInstance().getCompany().getBstShips(), controller.shipBst);
    }

    @Test
    void TopNshipsPerKmControllerTestWBST(){
        Company c = new Company();
        BSTShip bst = new BSTShip();
        bst.insert(ship);
        bst.insert(ship1);
        bst.insert(ship2);
        c.setBstShips(bst);
        TopNshipsPerKmController controller = new TopNshipsPerKmController(c);
        assertEquals(c.getBstShips(), controller.shipBst);
    }

    @Test
    void TopNshipsPerKmControllertest_null() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> { controller2.printNshipsMostKm("TestFiles/test106_null"); });
        assertEquals("Parameter invalid", thrown.getMessage());
    }

    @Test
    void TopNshipsPerKmControllertest_negative() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> { controller2.printNshipsMostKm("TestFiles/test106_null"); });
        assertEquals("Parameter invalid", thrown.getMessage());
    }

    @Test
    public void printNshipsMostKm() {
    }

    @Test
    public void sortByValue() {
    }
}