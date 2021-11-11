package lapr.project.controller;

import org.junit.Test;
import lapr.project.model.*;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TopNshipsPerKmControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");

    @Test
    public void TopNshipsPerKmControllertest() throws ParseException, IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        controller2.printNshipsMostKm("TestFiles/test106_positive");
    }

    @Test
    public void TopNshipsPerKmControllerTestApp(){
        BSTShip bst = new BSTShip();
        bst.insert(ship);
        bst.insert(ship1);
        bst.insert(ship2);
        App.getInstance().getCompany().setBstShips(bst);
        TopNshipsPerKmController controller = new TopNshipsPerKmController();
        assertEquals(App.getInstance().getCompany().getBstShips(), controller.shipBst);
    }

    @Test
    public void TopNshipsPerKmControllerTestWBST(){
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
    public void shipsAndKm() throws ParseException {
        BSTShip bst = new BSTShip();
        bst.insert(ship);
        bst.insert(ship1);
        bst.insert(ship2);
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bstd = new BSTDynData();
        bstd.insert(sdd);
        ship.setBstDynData(bstd);
        App.getInstance().getCompany().setBstShips(bst);
        TopNshipsPerKmController controller = new TopNshipsPerKmController();
        controller.shipsAndKm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void TopNshipsPerKmControllertest_null() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        controller2.printNshipsMostKm("TestFiles/test106_null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TopNshipsPerKmControllertest_negative() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        controller2.printNshipsMostKm("TestFiles/test106_negative");
    }

    @Test
    public void printNshipsMostKm() {
    }

    @Test
    public void sortByValue() {
    }
}