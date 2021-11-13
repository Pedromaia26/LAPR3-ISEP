package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintShipsInfoTest {

    PrintShipsInfo info = new PrintShipsInfo(123456789, 5, 120, 150);

    @Test
    void getNumberofMovements() {
        int expected = 5;
        assertEquals(expected, info.getNumberofMovements());
    }

    @Test
    void getRealdistance() {
        double expected = 150;
        assertEquals(expected, info.getRealdistance());
    }

    @Test
    void testToString() {
        String expected = "Ship: mmsi=123456789; numberofMovements=5; deltadistance=120.0; realdistance=150.0\n";
        assertEquals(expected, info.toString());
    }
}