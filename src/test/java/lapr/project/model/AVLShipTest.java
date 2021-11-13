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
    @Test
    void testEqualsAOneRootNull(){
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst2.insert(ship1);
        bst2.insert(ship2);
        assertNotEquals(bst1, bst2);
    }

    @Test
    void testNotEqualsAVL(){
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship3 = new Ship("123456799", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        bst1.insert(ship2);
        bst2.insert(ship1);
        bst2.insert(ship3);
        assertNotEquals(bst1, bst2);
    }

    @Test
    void testNotEqualsAVLB(){
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship3 = new Ship("123456799", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship4 = new Ship("123456759", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship5 = new Ship("123456749", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship6 = new Ship("123456729", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship7 = new Ship("123456739", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship8 = new Ship("123456769", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship9 = new Ship("123456719", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        bst1.insert(ship2);
        bst1.insert(ship3);
        bst1.insert(ship4);
        bst1.insert(ship5);
        bst1.insert(ship6);
        bst1.insert(ship7);
        bst1.insert(ship8);
        bst1.insert(ship9);
        bst2.insert(ship1);
        bst2.insert(ship2);
        bst2.insert(ship3);
        bst2.insert(ship4);
        bst2.insert(ship5);
        bst2.insert(ship6);
        bst2.insert(ship7);
        bst2.insert(ship8);
        assertNotEquals(bst1, bst2);
    }

    @Test
    void testNotEqualsAVLC(){
        BSTShip<Ship> shipBST = new AVLShip();

        BSTDynData<ShipDynData> instance = new BSTDynData<>();
        Ship ship = new Ship("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship2 = new Ship("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship3 = new Ship("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship4 = new Ship("555555556", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship5 = new Ship("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship6 = new Ship("555555558", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship7 = new Ship("555555559", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship8 = new Ship("555555553", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        Ship ship9 = new Ship("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3");

        ship.setBstDynData(instance);
        ship2.setBstDynData(instance);
        ship3.setBstDynData(instance);
        ship4.setBstDynData(instance);
        ship5.setBstDynData(instance);
        ship6.setBstDynData(instance);
        ship7.setBstDynData(instance);
        ship8.setBstDynData(instance);
        ship9.setBstDynData(instance);

        shipBST.insert(ship);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        shipBST.insert(ship4);
        shipBST.insert(ship5);
        shipBST.insert(ship6);
        shipBST.insert(ship7);
        shipBST.insert(ship8);
        shipBST.insert(ship9);

        assertEquals(shipBST.size(),7);
    }

    @Test
    void testInsertEqualsShips(){
        Ship ship1 = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        bst1.insert(ship1);
        assertEquals(ship1, bst1.find(ship1));
    }
}