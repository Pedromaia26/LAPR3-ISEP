package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company c = new Company();
    BSTShip bst = new AVLShip();

    @Test
    void getBstShips() {
        c.setBstShips(bst);
        assertEquals(bst,c.getBstShips());
    }

    @Test
    void setBstShips() {
    }
}