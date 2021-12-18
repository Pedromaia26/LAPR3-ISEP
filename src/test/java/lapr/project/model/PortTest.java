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
        App.getInstance().getCompany().getCountryStore().addCountry(new Country("Europe", "UK", "UKG", "United Kingdom", 0.5f, "Londres", 10.0f, 10.0f));
        Port port = new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","-3.033333333");
        Country expected = new Country("Europe", "UK", "UKG", "United Kingdom", 0.5f, "Londres", 10.0f, 10.0f);
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
        App.getInstance().getCompany().getCountryStore().addCountry(new Country("Europe", "UK", "UKG", "United Kingdom", 0.5f, "Londres", 10.0f, 10.0f));
        Port port = new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","-3.033333333");
        String expected = "Port{code=29002, name='Liverpool', country='United Kingdom', latitude=53.466667, longitude=-3.0333333}\n";
        assertEquals(expected, port.toString());
    }
}