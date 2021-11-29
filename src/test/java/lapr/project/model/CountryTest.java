package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    Country country = new Country("Europe", "Portugal");

    @Test
    void getContinent() {
        String expected = "Europe";
        assertEquals(expected, country.getContinent());
    }

    @Test
    void getName() {
        String expected = "Portugal";
        assertEquals(expected, country.getName());
    }

    @Test
    void testToString() {
        String expected = "Country:continent='Europe', name='Portugal'\n";
        assertEquals(expected, country.toString());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(country, country);
    }

    @Test
    void testEqualsObjectNull() {
        assertNotEquals(null, country);
    }

    @Test
    void testEqualsDifferentClass() {
        int num = 0;
        assertNotEquals(num, country);
    }

    @Test
    void testEqualsDifferentContinent() {
        Country country2 = new Country("America", "Portugal");
        assertNotEquals(country2, country);
    }

    @Test
    void testEqualsDifferentName() {
        Country country2 = new Country("Europe", "Spain");
        assertNotEquals(country2, country);
    }

    @Test
    void testEqualsTrue() {
        Country country2 = new Country("Europe", "Portugal");
        assertEquals(country2, country);
    }
}