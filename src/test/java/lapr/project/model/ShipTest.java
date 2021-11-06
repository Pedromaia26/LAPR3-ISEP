package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    Ship ship = new Ship("123456", "ship", "1000", "callSign", "A", "100", "500", "3");

    @Test
    void setDraft() {
        float draft = 5;
        ship.setDraft(draft);
        assertEquals(draft, ship.getDraft());
    }

    @Test
    void getShipName() {
        String nameExpected = "ship";
        String name = ship.getShipName();
        assertEquals(nameExpected, name);
    }

    @Test
    void getImo() {
        String imoExpected = "1000";
        String imo = ship.getImo();
        assertEquals(imoExpected, imo);
    }

    @Test
    void getGenerators() {
        int generatorExpected = 0;
        int generator = ship.getGenerators();
        assertEquals(generatorExpected, generator);
    }

    @Test
    void getGenertorPowerOutput() {
        int generatorPowerOutputExpected = 0;
        int generatorPowerOutput = ship.getGenertorPowerOutput();
        assertEquals(generatorPowerOutputExpected, generatorPowerOutput);
    }

    @Test
    void getCallSign() {
        String callSignExpected = "callSign";
        String callSign = ship.getCallSign();
        assertEquals(callSignExpected, callSign);
    }

    @Test
    void getVesselType() {
        String vesselTypeExpected = "A";
        String vesselType = ship.getVesselType();
        assertEquals(vesselTypeExpected, vesselType);
    }

    @Test
    void getLength() {
        int lenghtExpected = 100;
        int lenght = ship.getLength();
        assertEquals(lenghtExpected, lenght);
    }

    @Test
    void getWidth() {
        int widthExpected = 500;
        int width = ship.getWidth();
        assertEquals(widthExpected, width);
    }

    @Test
    void getCapacity() {
        int capacityExpected = 0;
        int capacity = ship.getCapacity();
        assertEquals(capacityExpected, capacity);
    }

    @Test
    void getDraft() {
        float draftExpected = 3;
        float draft = ship.getDraft();
        assertEquals(draftExpected, draft);
    }

    @Test
    void getBstDynData() {
        BSTDynData bst = new BSTDynData();
        ship.setBstDynData(bst);
        assertEquals(bst, ship.getBstDynData());
    }

    @Test
    void setBstDynData() {
    }
}