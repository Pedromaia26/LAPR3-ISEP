package lapr.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
    @Test
    void ShipInstanceIllegalArgumentMMSIUnder(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship2 = new Ship("12345678", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        });
        assertEquals("Invalid mmsi", thrown.getMessage());

    }

    @Test
    void ShipInstanceIllegalArgumentIMOUnder(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship2 = new Ship("123456789", "ship", "100000000", "callSign", "A", "100", "500", "3");
        });
        assertEquals("Invalid imo: should be imo more 7 characters", thrown.getMessage());
    }

    @Test
    void ShipInstanceIllegalArgumentMMSIAbove(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship2 = new Ship("1234567890", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        });
        assertEquals("Invalid mmsi", thrown.getMessage());
    }

    @Test
    void ShipInstanceIllegalArgumentIMOAbove(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship2 = new Ship("123456789", "ship", "10000000000", "callSign", "A", "100", "500", "3");
        });
        assertEquals("Invalid imo: should be imo more 7 characters", thrown.getMessage());
    }

    @Test
    void getShipName() {
        String nameExpected = "ship";
        String name = ship.getShipName();
        assertEquals(nameExpected, name);
    }

    @Test
    void getImo() {
        String imoExpected = "1000000000";
        String imo = ship.getImo();
        assertEquals(imoExpected, imo);
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
    void getGenerators(){
        assertEquals(0,ship.getGenerators());
    }

    @Test
    void getGenertorPowerOutput(){
        assertEquals(0,ship.getGenertorPowerOutput());
    }

    @Test
    void getCapacity(){
        assertEquals(1000,ship.getCapacity());
    }

    @Test
    void testEqualsDifferentClasses(){
        int a = 10;
        assertFalse(ship.equals(a));
    }

    @Test
    void testEqualsDifferentNull(){
        Ship ship2 = null;
        assertFalse(ship.equals(ship2));
    }

    @Test
    void testEqualsDifferentMMSI(){
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        assertFalse(ship.equals(ship2));
    }
}