package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorderTest {

    Border border = new Border("Portugal", "Espanha");

    @Test
    void getCountryname1() {
        assertEquals("Portugal", border.getCountryname1());
    }

    @Test
    void getCountryname2() {
        assertEquals("Espanha", border.getCountryname2());
    }
}