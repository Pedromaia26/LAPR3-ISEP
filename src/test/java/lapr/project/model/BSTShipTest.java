package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class BSTShipTest {

    BSTShip bst = new BSTShip();

    @Test
    void rootIsNotNull() throws ParseException {

        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship);
        BSTShip.Node node = new BSTShip.Node(null, null, null);
        node.setElement(ship);
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
        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship);
        assertFalse(bst.isEmpty());
    }

    @Test
    void findIsTrue() throws ParseException {

        Ship ship1 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship2 = new Ship("123456789", "ship", "1002000000", "callSign", "A", "100", "500", "3.0");
        Ship ship3 = new Ship("123453789", "ship", "1003000000", "callSign", "A", "100", "500", "3.0");
        Ship ship4 = new Ship("123454789", "ship", "1004000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship1);
        bst.insert(ship2);
        bst.insert(ship3);
        bst.insert(ship4);
        Ship ship5 = bst.find(ship4);
        //assertEquals(sdd5, sdd4);
        assertEquals(ship5.toString(),ship4.toString());
    }

    @Test
    void findIsFalse() throws ParseException {
        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship);
        Ship ship2 = new Ship("123454789", "ship", "1002000000", "callSign", "A", "100", "500", "3.0");
        Ship ship3 = bst.find(ship2);
        assertNotEquals(ship2, ship3);
    }

    @Test
    void insert() {
        Ship ship1 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship2 = new Ship("123456789", "ship", "1002000000", "callSign", "A", "100", "500", "3.0");
        Ship ship3 = new Ship("123453789", "ship", "1003000000", "callSign", "A", "100", "500", "3.0");
        Ship ship4 = new Ship("123454789", "ship", "1004000000", "callSign", "A", "100", "500", "3.0");

        BSTShip<Ship> instance = new BSTShip<>();

        assertEquals(0, instance.size());
        instance.insert(ship1);
        assertEquals(1, instance.size());
        instance.insert(ship2);
        assertEquals(2, instance.size());
        instance.insert(ship3);
        assertEquals(3, instance.size());
        instance.insert(ship4);
        assertEquals(4, instance.size());
    }

    @Test
    void inOrder() {
        Ship ship1 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship1);
        String lExpected = "[Ships{mmsi='123455789'[]}\n]";
        assertEquals(lExpected, bst.inOrder().toString());
    }
}