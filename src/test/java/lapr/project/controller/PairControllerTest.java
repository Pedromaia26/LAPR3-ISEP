package lapr.project.controller;

import lapr.project.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    void pair() throws IOException, ParseException {
        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123434289", "ship2", "1045000000", "callSign", "A", "100", "500", "3");
        ShipDynData sdd1 = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd2 = new ShipDynData("02/01/2020 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd3 = new ShipDynData("03/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd4 = new ShipDynData("02/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        BSTDynData bstDynData = new BSTDynData();
        BSTDynData bstDynData2 = new BSTDynData();
        bstDynData.insert(sdd1);
        bstDynData.insert(sdd2);
        bstDynData2.insert(sdd3);
        bstDynData2.insert(sdd4);
        ship.setBstDynData(bstDynData);
        ship2.setBstDynData(bstDynData2);
        BSTShip bst = new AVLShip();
        bst.insert(ship);
        bst.insert(ship2);
        App.getInstance().getCompany().setBstShips(bst);
        pcontroller.pair();
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
        int dist = pcontroller.checkTravelledDistance(9999, 1000);
        Assert.assertEquals(dist, 1);
    }

    @Test
    void checkRequirements(){

    }



}