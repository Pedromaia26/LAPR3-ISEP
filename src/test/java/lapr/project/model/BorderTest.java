package lapr.project.model;

import lapr.project.data.CountryStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorderTest {
    Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20f, 29f);
    Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.7f, "Madrid", 21f, 28f);
    Border border = new Border(country1, country2);

    @Test
    void getCountryname1() {
        assertEquals("Portugal", border.getCountryname1().getName());
    }

    @Test
    void getCountryname2() {
        assertEquals("Espanha", border.getCountryname2().getName());
    }
}