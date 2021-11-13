package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class BSTDynDataTest {

    BSTDynData bst = new BSTDynData();
    List<ShipDynData> list = new ArrayList<>();

    @Test
    void rootIsNotNull() throws ParseException {

        ShipDynData sdd = new ShipDynData("01/01/2021 13:50", "1000", "1000", "30.0", "1000", "1000", "40", "B");
        bst.insert(sdd);
        BSTDynData.Node node = new BSTDynData.Node(null, null, null);
        node.setElement(sdd);
        node.setLeft(null);
        node.setRight(null);
        assertEquals(node.getElement(), bst.root().getElement());
    }

    @Test
    void rootIsNull() throws ParseException {

        assertNull(bst.root());
    }

    @Test
    void isEmpty() {

        assertTrue(bst.isEmpty());
    }

    @Test
    void isNotEmpty() throws ParseException {

        ShipDynData sdd = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "-50.0", "50", "40", "B");
        bst.insert(sdd);
        assertFalse(bst.isEmpty());
    }

    @Test
    void findIsTrue() throws ParseException {

        ShipDynData sdd1 = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd2 = new ShipDynData("02/01/2020 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd3 = new ShipDynData("03/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd4 = new ShipDynData("02/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd1);
        bst.insert(sdd2);
        bst.insert(sdd3);
        bst.insert(sdd4);
        ShipDynData sdd5 = bst.find(sdd4);
        assertEquals(sdd4.toString(),sdd5.toString());
    }

    @Test
    void findIsFalse() throws ParseException {

        ShipDynData sdd = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd);
        ShipDynData sdd2 = new ShipDynData("02/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd3 = bst.find(sdd);
        assertNotEquals(sdd2, sdd3);
    }

    @Test
    void insert() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd2 = new ShipDynData("02/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd3 = new ShipDynData("03/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd4 = new ShipDynData("04/01/2020 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");

        BSTDynData<ShipDynData> instance = new BSTDynData();

        assertEquals(0, instance.size());
        instance.insert(sdd1);
        assertEquals(1, instance.size());
        instance.insert(sdd2);
        assertEquals(2, instance.size());
        instance.insert(sdd3);
        assertEquals(3, instance.size());
        instance.insert(sdd4);
        assertEquals(4, instance.size());
    }

    @Test
    void inOrder() throws ParseException {
        ShipDynData sdd = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd);
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2021 13:50");
        assertEquals("[" + date + "\tLATITUDE: 50; LONGITUDE: 50]", bst.inOrder().toString());
    }

    @Test
    void searchSpecificDate() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        bst.insert(sdd);
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 19:25");

        ShipDynData actual = bst.searchSpecificDate(date);
        String actual2 = actual.toString();
        assertEquals( date + "\tLATITUDE: -66.97000; LONGITUDE: 22.81780", actual2);

    }

    @Test
    void searchSpecificDatePeriod() throws ParseException {

        ShipDynData sdd = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd3 = new ShipDynData("31/12/2020 19:45", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd4 = new ShipDynData("31/12/2020 20:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        list.add(sdd);
        list.add(sdd2);
        list.add(sdd3);
        list.add(sdd4);

        bst.insert(sdd);
        bst.insert(sdd2);
        bst.insert(sdd3);
        bst.insert(sdd4);

        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:24");
        Date date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 20:15");


        List<ShipDynData> newList = new ArrayList<>();
        newList.add(sdd2);
        newList.add(sdd3);


        List<ShipDynData> expected = newList;
        List<ShipDynData> actual = bst.searchSpecificDatePeriodcall(date, date2);
        assertEquals(expected, actual);
    }

    @Test
    void testToString() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        bst.insert(sdd);
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:10");
        String expected = date + "\tLATITUDE: -66.97000; LONGITUDE: 22.81780\n";
        assertEquals(expected, bst.toString());
    }

    @Test
    void testToStringEmpty(){
        assertEquals("", bst.toString());
    }

    @Test
    void testToStringTwoMoreElements() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 18:09", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd3 = new ShipDynData("31/12/2020 18:11", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");

        bst.insert(sdd1);
        bst.insert(sdd2);
        bst.insert(sdd3);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:10");
        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:09");
        Date date3 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:11");
        String expected = date1 + "\tLATITUDE: -66.97000; LONGITUDE: 22.81780\n";
        expected += date2 + "\tLATITUDE: -66.97000; LONGITUDE: 22.81780\n";
        expected += date3 + "\tLATITUDE: -66.97000; LONGITUDE: 22.81780\n";
        assertEquals(expected, bst.toString());
    }

    @Test
    void distFrom() {
        double lat1 = -66.97000;
        double lon1 = 22.81780;
        double lat2 = -66.98000;
        double lon2 = 22.81790;
        double expected = 1111.957763671875;
        assertEquals(expected, bst.distFrom(lat1,lon1,lat2,lon2));
    }

    @Test
    void distFromSameLocal() {
        double lat1 = -66.97000;
        double lon1 = 22.81780;
        double lat2 = -66.97000;
        double lon2 = 22.81780;
        double expected = 0;
        assertEquals(expected, bst.distFrom(lat1,lon1,lat2,lon2));
    }

    @Test
    void distFrom2Locals() {
        double lat1 = -66.98000;
        double lon1 = 22.81790;
        double lat2 = -66.97000;
        double lon2 = 22.81780;
        assertEquals(bst.distFrom(lat2,lon2,lat1,lon1), bst.distFrom(lat1,lon1,lat2,lon2));
    }

    @Test
    void inorderCalculateDistanceEquals0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        bst.insert(sdd);
        double expected = 0;
        assertEquals(expected, bst.inorderCalculateDistance(sdd.getBaseDateTime(), sdd.getBaseDateTime()));
    }

    @Test
    void inorderCalculateDistanceEquals02() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 18:10", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");

        bst.insert(sdd1);
        bst.insert(sdd2);
        double expected = 0;
        assertEquals(expected, bst.inorderCalculateDistance(sdd1.getBaseDateTime(), sdd2.getBaseDateTime()));
    }

    @Test
    void inorderCalculateDistanceNAElements() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("31/12/2020 18:10", "-91.97000", "219.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 18:10", "-92.97000", "220.81780",  "14.4", "11.2", "347", "NA", "B");

        bst.insert(sdd1);
        bst.insert(sdd2);
        double expected = 0;
        assertEquals(expected, bst.inorderCalculateDistance(sdd1.getBaseDateTime(), sdd2.getBaseDateTime()));
    }

    @Test
    void inorderCalculateDistance3Elements() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("31/12/2020 18:10", "-85.97000", "150.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 18:11", "-86.97000", "151.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd3 = new ShipDynData("31/12/2020 18:12", "-86.57000", "152.81780",  "14.4", "11.2", "347", "NA", "B");
        bst.insert(sdd1);
        bst.insert(sdd2);
        bst.insert(sdd3);
        double expected = 156316.8203125;
        assertEquals(expected, bst.inorderCalculateDistance(sdd1.getBaseDateTime(), sdd3.getBaseDateTime()));
    }

    @Test
    void searchSpecificDateUnder() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd1);
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date2 = formatter.parse(date_string2);
        assertNull(bst.searchSpecificDate(date2));
    }

    @Test
    void searchSpecificDateEquals() throws ParseException {
        BSTDynData bst1 = new BSTDynData();
        ShipDynData sdd1 = new ShipDynData("01/12/2020 01:00", "50", "50", "30.0", "50.0", "50", "40", "B");
        ShipDynData sdd2 = new ShipDynData("01/12/2020 01:02", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst1.insert(sdd2);
        bst1.insert(sdd1);
        String date_string2 = "01/12/2020 01:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date2 = formatter.parse(date_string2);
        assertEquals(sdd1, bst1.searchSpecificDate(date2));
    }

    @Test
    void searchSpecificDatePeriodOver() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("01/12/2020 01:00", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd1);
        List<ShipDynData> list = new ArrayList<>();
        list.add(sdd1);
        String date_string1 = "01/12/2020 01:00";
        String date_string2 = "01/12/2020 02:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date2 = formatter.parse(date_string2);
        Date date1 = formatter.parse(date_string1);
        assertEquals(bst.searchSpecificDatePeriodcall(date1, date2), list);
    }

    @Test
    void searchSpecificDatePeriodUnder() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("01/12/2020 02:00", "50", "50", "30.0", "50.0", "50", "40", "B");
        bst.insert(sdd1);
        List<ShipDynData> list = new ArrayList<>();
        list.add(sdd1);
        String date_string1 = "01/12/2020 01:00";
        String date_string2 = "01/12/2020 02:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date2 = formatter.parse(date_string2);
        Date date1 = formatter.parse(date_string1);
        assertEquals(bst.searchSpecificDatePeriodcall(date1, date2), list);
    }
}