package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintingShipsInfoControllerTest {

    Company c = new Company();

    @Test
    void getShips() throws IOException {
        ImportShipsController controller = new ImportShipsController(c);
        controller.importFromCSV("bships.csv");
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

    @Test
    void organizeAsc() throws IOException {
        PrintingShipsInfoController controller = new PrintingShipsInfoController(c);
        List<PrintShipsInfo> list = new ArrayList<>();
        List<PrintShipsInfo> orderedlist = new ArrayList<>();
        PrintShipsInfo info1 = new PrintShipsInfo(123456789, 2, 20000, 40000);
        PrintShipsInfo info2 = new PrintShipsInfo(123456789, 3, 20000, 40000);
        PrintShipsInfo info3 = new PrintShipsInfo(123456789, 1, 20000, 40000);
        PrintShipsInfo info4 = new PrintShipsInfo(123456789, 5, 20000, 40000);
        PrintShipsInfo info5 = new PrintShipsInfo(123456789, 8, 20000, 40000);
        PrintShipsInfo info6 = new PrintShipsInfo(123456789, 5, 20000, 40000);
        PrintShipsInfo info8 = new PrintShipsInfo(123456789, 1, 20000, 40000);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        list.add(info6);
        list.add(info8);
        orderedlist.add(info3);
        orderedlist.add(info8);
        orderedlist.add(info1);
        orderedlist.add(info2);
        orderedlist.add(info4);
        orderedlist.add(info6);
        orderedlist.add(info5);
        list = controller.organizeAsc(list);
        assertEquals(orderedlist, list);
    }

    @Test
    void organizeDesc() throws IOException {
        PrintingShipsInfoController controller = new PrintingShipsInfoController(c);
        List<PrintShipsInfo> list = new ArrayList<>();
        List<PrintShipsInfo> orderedlist = new ArrayList<>();
        PrintShipsInfo info1 = new PrintShipsInfo(123456789, 2, 20000, 40000);
        PrintShipsInfo info2 = new PrintShipsInfo(123456789, 3, 20000, 41000);
        PrintShipsInfo info3 = new PrintShipsInfo(123456789, 1, 20000, 42000);
        PrintShipsInfo info4 = new PrintShipsInfo(123456789, 5, 20000, 39000);
        PrintShipsInfo info5 = new PrintShipsInfo(123456789, 8, 20000, 35000);
        PrintShipsInfo info6 = new PrintShipsInfo(123456789, 5, 20000, 40000);
        PrintShipsInfo info8 = new PrintShipsInfo(123456789, 1, 20000, 39000);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        list.add(info6);
        list.add(info8);
        orderedlist.add(info3);
        orderedlist.add(info2);
        orderedlist.add(info1);
        orderedlist.add(info6);
        orderedlist.add(info4);
        orderedlist.add(info8);
        orderedlist.add(info5);

        list = controller.organizeDesc(list);
        assertEquals(orderedlist, list);
    }
}