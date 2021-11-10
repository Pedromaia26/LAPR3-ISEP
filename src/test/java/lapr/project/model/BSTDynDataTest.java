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
}