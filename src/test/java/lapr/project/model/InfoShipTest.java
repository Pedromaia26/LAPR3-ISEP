package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InfoShipTest {

    Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
    Date date1 = new Date();
    Date date2 = new Date();

    @Test
    void getAverageSpeed() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        double expected = -1;
        assertEquals(expected, infoship.getAverageSpeed());
    }

    @Test
    void getTravelledDistance() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        double expected = 0;
        assertEquals(expected, infoship.getTravelledDistance());
    }

    @Test
    void getMmsi() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        int expected = 123456789;
        assertEquals(expected, infoship.getMmsi());
    }

    @Test
    void testCalculateAverageSpeedPossible() throws ParseException {
        String date_string1 = "31/12/2020 23:00";
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(date_string1);
        Date date2 = formatter.parse(date_string2);
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        assertEquals(14.4, infoship.calculateAverageSpeed(ship1, date2, date1));
    }

    @Test
    void testCalculateAverageSpeedOver() throws ParseException {
        String date_string1 = "31/12/2020 23:00";
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(date_string1);
        Date date2 = formatter.parse(date_string2);
        ShipDynData sdd = new ShipDynData("31/12/2020 23:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        assertEquals(-1, infoship.calculateAverageSpeed(ship1, date2, date1));
    }

    @Test
    void testCalculateAverageSpeedEqualsOver() throws ParseException {
        String date_string1 = "31/12/2020 23:00";
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(date_string1);
        Date date2 = formatter.parse(date_string2);
        ShipDynData sdd = new ShipDynData("31/12/2020 23:00", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        assertEquals(14.4, infoship.calculateAverageSpeed(ship1, date2, date1));
    }

    @Test
    void testCalculateAverageSpeedUnder() throws ParseException {
        String date_string1 = "31/12/2020 23:00";
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(date_string1);
        Date date2 = formatter.parse(date_string2);
        ShipDynData sdd = new ShipDynData("01/12/2020 00:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        assertEquals(-1, infoship.calculateAverageSpeed(ship1, date2, date1));
    }

    @Test
    void testCalculateAverageSpeedEqualsUnder() throws ParseException {
        String date_string1 = "31/12/2020 23:00";
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(date_string1);
        Date date2 = formatter.parse(date_string2);
        ShipDynData sdd = new ShipDynData("01/12/2020 01:00", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship = new InfoShip(ship1, date1,date2);
        assertEquals(14.4, infoship.calculateAverageSpeed(ship1, date2, date1));
    }

    @Test
    void compareTo2Over1() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship1 = new InfoShip(ship1, date1,date2);
        Ship ship2 = new Ship("223456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        ship2.setBstDynData(bst);
        InfoShip infoship2 = new InfoShip(ship2, date1,date2);
        int expected = 1;
        assertEquals(expected, infoship2.compareTo(infoship1));
    }

    @Test
    void compareTo1Over2() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship1 = new InfoShip(ship1, date1,date2);
        Ship ship2 = new Ship("113456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        ship2.setBstDynData(bst);
        InfoShip infoship2 = new InfoShip(ship2, date1,date2);
        int expected = -1;
        assertEquals(expected, infoship2.compareTo(infoship1));
    }

    @Test
    void compareTo1Equals2() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData bst = new BSTDynData();
        bst.insert(sdd);
        ship1.setBstDynData(bst);
        InfoShip infoship1 = new InfoShip(ship1, date1,date2);
        Ship ship2 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        ship2.setBstDynData(bst);
        InfoShip infoship2 = new InfoShip(ship2, date1,date2);
        int expected = 0;
        assertEquals(expected, infoship2.compareTo(infoship1));
    }
}