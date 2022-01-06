package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lapr.project.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TopNshipsPerKmControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");
    Ship ship3 = new Ship("212351001","HYUNDAI PORTUGAL","IMO9305684","5BZP2","70","303","40","14.5");
    BSTDynData bst1 = new BSTDynData();
    BSTDynData bst2 = new BSTDynData();
    BSTDynData bst3 = new BSTDynData();
    BSTDynData bst4 = new BSTDynData();

    @Test
    void TopNshipsPerKmControllertest() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        controller.importFromCSV("sships.csv");
        TopNshipsPerKmController controller2 = new TopNshipsPerKmController();
        controller2.printNshipsMostKm("TestFiles/test106_positive");
    }

    @Test
    void TopNshipsPerKmControllerTestApp(){
        BSTShip bst = new AVLShip();
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
        BSTShip bst = new AVLShip();
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
    public void sortByValue() throws ParseException {
        ShipDynData shipd1 = new ShipDynData("06/06/2020 00:00", "20.00", "40.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd2 = new ShipDynData("06/06/2020 00:00", "21.00", "43.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd3 = new ShipDynData("06/06/2020 00:00", "16.00", "23.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd4 = new ShipDynData("06/06/2020 00:00", "20.00", "40.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd5 = new ShipDynData("06/06/2020 00:00", "32.00", "23.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd6 = new ShipDynData("06/06/2020 00:00", "20.00", "44.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd7 = new ShipDynData("06/06/2020 00:00", "19.00", "41.00", "20", "23", "32", "4.0", "2");
        ShipDynData shipd8 = new ShipDynData("06/06/2020 00:00", "5.00", "30.00", "20", "23", "32", "4.0", "2");
        bst1.insert(shipd1);
        bst1.insert(shipd2);
        bst2.insert(shipd3);
        bst2.insert(shipd4);
        bst3.insert(shipd5);
        bst3.insert(shipd6);
        bst4.insert(shipd7);
        bst4.insert(shipd8);
        ship.setBstDynData(bst1);
        ship1.setBstDynData(bst2);
        ship2.setBstDynData(bst3);
        ship3.setBstDynData(bst4);
        InfoShip info1 = new InfoShip(ship, new Date("01/01/2020"), new Date("31/12/2020"));
        InfoShip info2 = new InfoShip(ship1, new Date("01/01/2020"), new Date("31/12/2020"));
        InfoShip info3 = new InfoShip(ship2, new Date("01/01/2020"), new Date("31/12/2020"));
        InfoShip info4 = new InfoShip(ship3, new Date("01/01/2020"), new Date("31/12/2020"));
        ArrayList<InfoShip> list = new ArrayList<>();
        ArrayList<InfoShip> orderedlist = new ArrayList<>();
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        orderedlist.add(info1);
        orderedlist.add(info2);
        orderedlist.add(info3);
        orderedlist.add(info4);
        HashMap<String, ArrayList<InfoShip>> map = new LinkedHashMap<>();
        map.put("70", list);
        HashMap<String, ArrayList<InfoShip>> map2 = new LinkedHashMap<>();
        map2.put("70", orderedlist);
        assertEquals(map2, TopNshipsPerKmController.sortByValue(map));
    }

    @Test
    public void ExceptionLineNull() throws IOException {
        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class, () -> {
            TopNshipsPerKmController controller = new TopNshipsPerKmController();
            controller.printNshipsMostKm("Input/null.txt");
        });
        Assertions.assertEquals("Input\\null.txt (O sistema não conseguiu localizar o ficheiro especificado)", thrown.getMessage());
    }
}