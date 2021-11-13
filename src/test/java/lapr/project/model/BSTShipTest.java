package lapr.project.model;

import org.junit.jupiter.api.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.text.ParseException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BSTShipTest {

    BSTShip bst = new AVLShip();

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
        assertEquals(ship5,ship4);
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

        BSTShip<Ship> instance = new AVLShip();

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
        String lExpected = "[Ships{mmsi='123455789'}]";
        assertEquals(lExpected, bst.inOrder().toString());
    }

    @Test
    void heightEmpty() {
        int expected = -1;
        assertEquals(expected, bst.height());
    }

    @Test
    void heightOneElement() {
        Ship ship1 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship1);
        int expected = 0;
        assertEquals(expected, bst.height());
    }

    @Test
    void heightTwoMoreElement() {
        Ship ship1 = new Ship("123455749", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship2 = new Ship("123455779", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship3 = new Ship("123455729", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship4 = new Ship("123455719", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship5 = new Ship("123455739", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship6 = new Ship("123455769", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship7 = new Ship("123455799", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship8 = new Ship("123455759", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship9 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship10 = new Ship("123455699", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship1);
        bst.insert(ship2);
        bst.insert(ship3);
        bst.insert(ship4);
        bst.insert(ship5);
        bst.insert(ship6);
        bst.insert(ship7);
        bst.insert(ship8);
        bst.insert(ship9);
        bst.insert(ship10);
        int expected = 3;
        assertEquals(expected, bst.height());
    }

    @Test
    void smallestElement(){
        Ship ship1 = new Ship("123455769", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship2 = new Ship("123455779", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship3 = new Ship("123455789", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        Ship ship4 = new Ship("123455799", "ship", "1001000000", "callSign", "A", "100", "500", "3.0");
        bst.insert(ship1);
        bst.insert(ship2);
        bst.insert(ship3);
        bst.insert(ship4);
        Ship expected = ship1;
        assertEquals(ship1, bst.smallestElement());
    }
}