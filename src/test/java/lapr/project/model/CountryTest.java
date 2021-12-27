package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    Country country = new Country("America", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);

    @Test
    void getContinent() {
        String expected = "America";
        assertEquals(expected, country.getContinent());
    }

    @Test
    void getName() {
        String expected = "Portugal";
        assertEquals(expected, country.getName());
    }

    @Test
    void testToString() {
        String expected = "Country{continent='America', name='Portugal', alpha2_code='PT', alpha3_code='PRT', population=0.5, capital='Lisboa', latitude=20.0, longitude=40.0}";
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
        Country country2 = new Country("Europa", "PT", "PRT", "Portugal", 10121746, "Lisboa", 20, 40);
        assertNotEquals(country2, country);
    }

    @Test
    void testEqualsDifferentName() {
        Country country2 = new Country("Europe", "Spain", "SP", "SPN", 46556472, "Madrid", 30, 40);
        assertNotEquals(country2, country);
    }

    @Test
    void testEqualsTrue() {
        Country country2 = new Country("America", "PT", "PRT", "Portugal", 10121746, "Lisboa", 20, 40);
        assertEquals(country2, country);
    }

    @Test
    void getAlpha2_code() {
        String expected = "PT";
        assertEquals(expected, country.getAlpha2_code());
    }

    @Test
    void getAlpha3_code() {
        String expected = "PRT";
        assertEquals(expected, country.getAlpha3_code());
    }

    @Test
    void getPopulation() {
        Float expected = 0.5f;
        assertEquals(expected, country.getPopulation());
    }

    @Test
    void getCapital() {
        String expected = "Lisboa";
        assertEquals(expected, country.getCapital());
    }

    @Test
    void getLongitude() {
        Float expected = 40f;
        assertEquals(expected, country.getLongitude());
    }

    @Test
    void getLatitude() {
        Float expected = 20f;
        assertEquals(expected, country.getLatitude());
    }
}