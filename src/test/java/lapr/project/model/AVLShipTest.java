package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLShipTest {

    BSTShip bst1 = new AVLShip();
    BSTShip bst2 = new AVLShip();

    @Test
    void testEqualsAVL() {
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        bst1.insert(ship2);
        bst2.insert(ship1);
        bst2.insert(ship2);
        assertEquals(bst1, bst1);
    }

    @Test
    void testEqualsDifferentClasses() {
        int a = 0;
        assertNotEquals(bst1, a);
    }

    @Test
    void testEqualsOneNull() {
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        bst1.insert(ship2);
        assertNotEquals(bst1, null);
    }

    @Test
    void testEqualsBothRootsNull() {
        assertEquals(bst1, bst2);
    }

    @Test
    void testEqualsOneRootNull(){
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        bst1.insert(ship2);
        assertNotEquals(bst1, bst2);
    }
}