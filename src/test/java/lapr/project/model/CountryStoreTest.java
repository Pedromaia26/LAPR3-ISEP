package lapr.project.model;

import lapr.project.data.CountryStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryStoreTest {

    CountryStore store = new CountryStore();
    Country country = new Country("Europe", "Portugal");

    @Test
    void addCountry() {
        assertEquals(country, store.addCountry(country));
    }

    @Test
    void addCountryNull() {
        assertNull(store.addCountry(null));
    }

    @Test
    void addCountryContained() {
        store.addCountry(country);
        assertEquals(country, store.addCountry(country));
    }

    @Test
    void getCountries() {
        store.addCountry(country);
        List<Country> list = new ArrayList<>();
        list.add(country);
        assertEquals(list, store.getCountries());
    }
}