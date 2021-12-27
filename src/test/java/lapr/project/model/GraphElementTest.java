package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphElementTest {

    Country country = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 38.722311f, -9.139335f);
    GraphElement graphElement2 = new GraphElement(country);

    @Test
    void getDesignation() {
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertEquals("Leixoes", graphElement1.getDesignation());
        assertEquals("Lisboa", graphElement2.getDesignation());
    }

    @Test
    void getLatitude() {
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertEquals(41.177648f, graphElement1.getLatitude());
        assertEquals(38.722311f, graphElement2.getLatitude());
    }

    @Test
    void getLongitude() {
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertEquals(-8.700474f, graphElement1.getLongitude());
        assertEquals(-9.139335f, graphElement2.getLongitude());
    }

    @Test
    void getCountry(){
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertEquals("Portugal", graphElement1.getCountry());
    }

    @Test
    void testEqualsDifferentClasses(){
        int a = 10;
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertFalse(graphElement1.equals(a));
    }

    @Test
    void testEqualsDifferentNull(){
        GraphElement ge = null;
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        assertNotEquals(ge, graphElement1);
    }

    @Test
    void testEqualsDifferentDesignation(){
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        Port port2 = new Port("12345", "Setubal", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement2 = new GraphElement(port2);
        assertNotEquals(port2, port);
    }

    @Test
    void testEqualsDifferentCountry(){
        Country country2 = new Country("Europa", "PT", "PRT", "Espanha", 0.5f, "Lisboa", 38.722311f, -9.139335f);
        App.getInstance().getCompany().getCountryStore().addCountry(country);
        App.getInstance().getCompany().getCountryStore().addCountry(country2);
        Port port = new Port("12345", "Leixoes", "Europa", "Portugal", "41.177648", "-8.700474");
        GraphElement graphElement1 = new GraphElement(port);
        Port port2 = new Port("12345", "Leixoes", "Europa", "Espanha", "41.177648", "-8.700474");
        GraphElement graphElement2 = new GraphElement(port2);
        assertNotEquals(graphElement1, graphElement2);
    }
}