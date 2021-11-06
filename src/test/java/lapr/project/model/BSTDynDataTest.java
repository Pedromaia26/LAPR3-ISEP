package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class BSTDynDataTest {

    BSTDynData bst = new BSTDynData();

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
        //assertEquals(sdd5, sdd4);
        assertEquals(sdd5.toString(),sdd4.toString());
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
        String lExpected = "[50;50]";
        assertEquals(lExpected, bst.inOrder().toString());
    }

    @Test
    void testToString() {
    }
}