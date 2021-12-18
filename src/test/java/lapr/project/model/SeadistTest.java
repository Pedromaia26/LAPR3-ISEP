package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeadistTest {

    Seadist seadist = new Seadist("Portugal", 212121, "Leixoes", "Espanha", 121212, "Mallorca", 200);

    @Test
    void getFromCountryName() {
        String expected = "Portugal";
        assertEquals(expected, seadist.getFromCountryName());
    }

    @Test
    void getFromPortId() {
        int expected = 212121;
        assertEquals(expected, seadist.getFromPortId());
    }

    @Test
    void getFromPortName() {
        String expected = "Leixoes";
        assertEquals(expected, seadist.getFromPortName());
    }

    @Test
    void getToCountryName() {
        String expected = "Espanha";
        assertEquals(expected, seadist.getToCountryName());
    }

    @Test
    void getToPortId() {
        int expected = 121212;
        assertEquals(expected, seadist.getToPortId());
    }

    @Test
    void getToPortName() {
        String expected = "Mallorca";
        assertEquals(expected, seadist.getToPortName());
    }

    @Test
    void getSeaDistance() {
        int expected = 200;
        assertEquals(expected, seadist.getSeaDistance());
    }
}