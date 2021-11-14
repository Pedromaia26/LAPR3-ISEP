package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchShipControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");
    Company company = new Company();
    BSTShip bstship = company.getBstShips();

    SearchShipControllerTest() throws IOException {

    }

    @Test
    void shipSearchByMmsi() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByMmsi(212180000);
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByMmsiDontExists() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByMmsi(212180001);
        assertNull(ship1);
    }

    @Test
    void shipSearchByImo() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByImo("IMO9643544");
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByImoDontExists() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByImo("IMO9643543");
        assertNull(ship1);
    }

    @Test
    void shipSearchByCallSign() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByCallSign("5BBA4");
        Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
        Ship expected = bstship.find(ship);
        assertEquals(expected, ship1);
    }

    @Test
    void shipSearchByCallSignDontExists() {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship ship1 = controller.ShipSearchByCallSign("5BBA3");
        assertNull(ship1);
    }

    @Test
    void makeSummary() throws IOException, ParseException {
        ShipDynData data = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data1 = new ShipDynData("31/12/2020 19:42","24.34750","-85.12250","10.5","110.9","115", "3", "A");
        ShipDynData data2 = new ShipDynData("31/12/2020 19:43","24.34775","-85.12400","9.5","130.9","110", "NA", "A");
        ShipDynData data3 = new ShipDynData("31/12/2020 19:40","24.34800","-85.12550","12","103.4","119", "NA", "A");
        ShipDynData data4 = new ShipDynData("31/12/2020 19:20","24.34900","-85.12523","11.7","130.9","117", "NA", "A");
        ShipDynData data5 = new ShipDynData("31/12/2020 20:08","24.34750","-85.12523","12","119.5","115", "NA", "A");
        BSTDynData bstdyndata = new BSTDynData();
        bstdyndata.insert(data);
        bstdyndata.insert(data1);
        ship.setBstDynData(bstdyndata);
        BSTShip bstship = new AVLShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        company.setBstShips(bstship);
        SearchShipController controller = new SearchShipController(company);
        controller.makeSummary("TestFiles/test104.txt");
    }

    @Test
    void searchDetails() throws IOException {
        BSTShip bstship = new AVLShip();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        company.setBstShips(bstship);
        SearchShipController controller = new SearchShipController(company);
        controller.searchDetails("TestFiles/test104.txt");
    }

    @Test
    void identifyTheShipImo() throws IOException {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship shipt = controller.IdentifyTheShip("TestFiles/test104_imo");
        assertEquals(ship, shipt);
    }

    @Test
    void identifyTheShipCallSign() throws IOException {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship shipt = controller.IdentifyTheShip("TestFiles/test104_callSign");
        assertEquals(ship, shipt);
    }

    @Test
    void makeSummaryNull() throws IOException, ParseException {
        SearchShipController controller = new SearchShipController();
        controller.makeSummary("TestFiles/test104_null");
    }

    @Test
    void searchDeatilsNull() throws IOException, ParseException {
        SearchShipController controller = new SearchShipController();
        controller.searchDetails("TestFiles/test104_null");
    }

    @Test
    void makeSummaryEmpty() throws IOException, ParseException {
        SearchShipController controller = new SearchShipController();
        controller.makeSummary("TestFiles/test104_empty");
    }

    @Test
    void searchDeatilsEmpty() throws IOException, ParseException {
        SearchShipController controller = new SearchShipController();
        controller.searchDetails("TestFiles/test104_empty");
    }

    @Test
    void identifyTheShipNull() throws IOException {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship shipt = controller.IdentifyTheShip("TestFiles/test104_null");
        assertNull(shipt);
    }

    @Test
    void identifyTheShipEmpty() throws IOException {
        BSTShip bstship = company.getBstShips();
        bstship.insert(ship);
        bstship.insert(ship1);
        bstship.insert(ship2);
        SearchShipController controller = new SearchShipController(company);
        Ship shipt = controller.IdentifyTheShip("TestFiles/test104_empty");
        assertNull(shipt);
    }

    @Test
    void getmaxCog() throws ParseException {
        SearchShipController controller = new SearchShipController();
        ShipDynData data1 = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data2 = new ShipDynData("31/12/2020 19:38","24.34573","-85.12394","10.7","118.9","117", "NA", "A");
        ShipDynData data3 = new ShipDynData("31/12/2020 19:39","24.34573","-85.12394","12.7","120.9","117", "NA", "A");
        BSTDynData bst = new BSTDynData();
        bst.insert(data1);
        bst.insert(data2);
        bst.insert(data3);
        ship.setBstDynData(bst);
        Iterable<ShipDynData> list = ship.getBstDynData().inOrder();
        assertEquals(120.9f, controller.getmaxCog(list));
    }

    @Test
    void getmaxSog() throws ParseException {
        SearchShipController controller = new SearchShipController();
        ShipDynData data1 = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data2 = new ShipDynData("31/12/2020 19:38","24.34573","-85.12394","10.7","118.9","117", "NA", "A");
        ShipDynData data3 = new ShipDynData("31/12/2020 19:39","24.34573","-85.12394","12.7","120.9","117", "NA", "A");
        BSTDynData bst = new BSTDynData();
        bst.insert(data1);
        bst.insert(data2);
        bst.insert(data3);
        ship.setBstDynData(bst);
        Iterable<ShipDynData> list = ship.getBstDynData().inOrder();
        assertEquals(12.7f, controller.getmaxSog(list));
    }

    @Test
    void getsumSog() throws ParseException {
        SearchShipController controller = new SearchShipController();
        ShipDynData data1 = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data2 = new ShipDynData("31/12/2020 19:38","24.34573","-85.12394","10.7","118.9","117", "NA", "A");
        ShipDynData data3 = new ShipDynData("31/12/2020 19:39","24.34573","-85.12394","12.7","120.9","117", "NA", "A");
        BSTDynData bst = new BSTDynData();
        bst.insert(data1);
        bst.insert(data2);
        bst.insert(data3);
        ship.setBstDynData(bst);
        Iterable<ShipDynData> list = ship.getBstDynData().inOrder();
        assertEquals(35.1f, controller.getsumSog(list));
    }

    @Test
    void getsumCog() throws ParseException {
        SearchShipController controller = new SearchShipController();
        ShipDynData data1 = new ShipDynData("31/12/2020 19:37","24.34573","-85.12394","11.7","119.9","117", "NA", "A");
        ShipDynData data2 = new ShipDynData("31/12/2020 19:38","24.34573","-85.12394","10.7","118.9","117", "NA", "A");
        ShipDynData data3 = new ShipDynData("31/12/2020 19:39","24.34573","-85.12394","12.7","120.9","117", "NA", "A");
        BSTDynData bst = new BSTDynData();
        bst.insert(data1);
        bst.insert(data2);
        bst.insert(data3);
        ship.setBstDynData(bst);
        Iterable<ShipDynData> list = ship.getBstDynData().inOrder();
        assertEquals(359.7f, controller.getsumCog(list));
    }
}