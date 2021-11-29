package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    Port port = new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","-3.033333333");

    @Test
    void getCode() {
        int expected = 29002;
        assertEquals(expected, port.getCode());
    }

    @Test
    void getName() {
        String expected = "Liverpool";
        assertEquals(expected, port.getName());
    }

    @Test
    void getCountry() {
        Country expected = new Country("Europe", "United Kingdom");
        assertEquals(expected, port.getCountry());
    }

    @Test
    void getLatitude() {
        float expected = 53.46666667f;
        assertEquals(expected, port.getLatitude());
    }

    @Test
    void getLongitude() {
        float expected = -3.033333333f;
        assertEquals(expected, port.getLongitude());
    }

    @Test
    void testToString() {
        String expected = "Port{code=29002, name='Liverpool', country='Country:continent='Europe', name='United Kingdom'\n" + "', latitude=53.466667, longitude=-3.0333333}\n";
        assertEquals(expected, port.toString());
    }

    @Test
    void CountryWithEqualsNameDifferentContinent(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Port port1 = new Port("29002","Liverpool","America","United Kingdom","53.46666667","-3.033333333");
        });
        assertEquals("Invalid Country", thrown.getMessage());
    }
}