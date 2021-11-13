package lapr.project.controller;

import lapr.project.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.OAEPParameterSpec;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

class PairControllerTest {

    Company c = new Company();
    PairController pcontroller = new PairController(c);
    PairController pcontroller2 = new PairController();
    ImportShipsController icontroller = new ImportShipsController(c);
    ImportShipsController icontroller2 = new ImportShipsController();


   @Test
   void returnPair() throws IOException {
        String file = "sships.csv";
        icontroller.importFromCSV(file);
        pcontroller.pair();
    }


    @Test
    void listWithSize0() throws IOException {
        pcontroller.pair();
    }

    @Test
    void listWithSize1() throws IOException {
        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        BSTShip bst = new AVLShip();
        bst.insert(ship);
        App.getInstance().getCompany().setBstShips(bst);
        pcontroller.pair();
    }

    @Test
    void checkRequirements() throws IOException, ParseException {

       Company company = new Company();
       PairController pairController = new PairController(company);
        Ship ship1 = new Ship("210950000", "ship1", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5");
        Ship ship2 = new Ship("228339600", "ship2", "IMO9450648", "FLSU", "70", "334", "42", "15");
        ShipDynData sdd1 = new ShipDynData("31/12/2020 16:00", "42.69577", "-66.97808", "13.7", "-54.8", "357","NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 18:31", "43.22513", "-66.96725", "11.7", "5.5", "355", "40", "B");
        ShipDynData sdd3 = new ShipDynData("31/12/2020 00:00", "28.37458", "-88.88584", "11.8", "124.6", "128","79", "B");
        ShipDynData sdd4 = new ShipDynData("31/12/2020 03:56", "27.87869", "-88.22321", "11.7", "127.9", "128","79", "B");
        BSTDynData bstDynData = new BSTDynData();
        BSTDynData bstDynData2 = new BSTDynData();
        bstDynData.insert(sdd1);
        bstDynData.insert(sdd2);
        bstDynData2.insert(sdd3);
        bstDynData2.insert(sdd4);
        ship1.setBstDynData(bstDynData);
        ship2.setBstDynData(bstDynData2);
        BSTShip bst = new AVLShip();
        bst.insert(ship1);
        bst.insert(ship2);
        company.setBstShips(bst);
        pairController.pair();
    }

    @Test
    void dist1GreaterThan10000(){
       int dist = pcontroller.checkTravelledDistance(131232, 1231231);
       Assert.assertEquals(dist, 0);
    }

    @Test
    void dist1Equals10000(){
        int dist = pcontroller.checkTravelledDistance(10000, 9999);
        Assert.assertEquals(dist, 2);
    }

    @Test
    void dist1LessThan10000(){
        int dist = pcontroller.checkTravelledDistance(1000, 9999);
        Assert.assertEquals(dist, 1);
    }

    @Test
    void dist2GreaterThan10000(){
        int dist = pcontroller.checkTravelledDistance(1231231, 131232);
        Assert.assertEquals(dist, 0);
    }

    @Test
    void dist2Equals10000(){
        int dist = pcontroller.checkTravelledDistance(9999, 10000);
        Assert.assertEquals(dist, 1);
    }

    @Test
    void dist2LessThan10000(){
        int dist = pcontroller.checkTravelledDistance(12212, 1000);
        Assert.assertEquals(dist, 2);
    }


    @Test
    void checkNotMeetingRequirements() throws IOException, ParseException {
        ImportShipsController importShipsController = new ImportShipsController();
        String importFile = "TestFiles/test107_sameTravelledDistance";
        importShipsController.importFromCSV(importFile);
        PairController pairController = new PairController();

        pairController.pair();
    }
}