package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class SearchShipControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");

    SearchShipControllerTest() throws IOException {
    }

    @Test
    void shipSearchByMmsi() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByMmsi(212180000);
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByMmsiDontExists() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByMmsi(212180001);
        assertNull(ship1);
    }

    @Test
    void shipSearchByImo() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByImo("IMO9643544");
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByImoDontExists() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByImo("IMO9643543");
        assertNull(ship1);
    }

    @Test
    void shipSearchByCallSign() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByCallSign("5BBA4");
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByCallSignDontExists() {
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        Ship ship1 = controller.ShipSearchByCallSign("5BBA3");
        assertNull(ship1);
    }

    @Test
    void getSearchShipData() {

    }

    @Test
    void searchDeatils() throws IOException, ParseException {
        ShipDynData data = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data1 = new ShipDynData("31/12/2020 19:42","24.34750","-85.12250","10.5","110.9","115", "NA", "A");
        BSTDynData bstdyndata = new BSTDynData();
        bstdyndata.insert(data);
        bstdyndata.insert(data1);
        ship.setBstDynData(bstdyndata);
        BSTShip bstship = new BSTShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(bstship);
        controller.searchDeatils("TestFiles/test104.txt");
    }
}