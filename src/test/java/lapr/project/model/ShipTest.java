package lapr.project.model;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");

    @Test(expected = IllegalArgumentException.class)
    public void ShipInstanceIllegalArgumentMMSIUnder(){
        Ship ship2 = new Ship("12345678", "ship", "1000000000", "callSign", "A", "100", "500", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShipInstanceIllegalArgumentIMOUnder(){
        Ship ship2 = new Ship("123456789", "ship", "100000000", "callSign", "A", "100", "500", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShipInstanceIllegalArgumentMMSIAbove(){
        Ship ship2 = new Ship("1234567890", "ship", "1000000000", "callSign", "A", "100", "500", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShipInstanceIllegalArgumentIMOAbove(){
        Ship ship2 = new Ship("123456789", "ship", "10000000000", "callSign", "A", "100", "500", "3");
    }

    @Test
    public void getShipName() {
        String nameExpected = "ship";
        String name = ship.getShipName();
        assertEquals(nameExpected, name);
    }

    @Test
    public void getImo() {
        String imoExpected = "1000000000";
        String imo = ship.getImo();
        assertEquals(imoExpected, imo);
    }

    @Test
    public void getCallSign() {
        String callSignExpected = "callSign";
        String callSign = ship.getCallSign();
        assertEquals(callSignExpected, callSign);
    }

    @Test
    public void getVesselType() {
        String vesselTypeExpected = "A";
        String vesselType = ship.getVesselType();
        assertEquals(vesselTypeExpected, vesselType);
    }

    @Test
    public void getLength() {
        int lenghtExpected = 100;
        int lenght = ship.getLength();
        assertEquals(lenghtExpected, lenght);
    }

    @Test
    public void getWidth() {
        int widthExpected = 500;
        int width = ship.getWidth();
        assertEquals(widthExpected, width);
    }

    @Test
    public void getDraft() {
        float draftExpected = 3;
        float draft = ship.getDraft();
        assertEquals(draftExpected, draft);
    }

    @Test
    public void getBstDynData() {
        BSTDynData bst = new BSTDynData();
        ship.setBstDynData(bst);
        assertEquals(bst, ship.getBstDynData());
    }

    @Test
    public void testEqualsDifferentClasses(){
        int a = 10;
        assertFalse(ship.equals(a));
    }

    @Test
    public void testEqualsDifferentNull(){
        Ship ship2 = null;
        assertFalse(ship.equals(ship2));
    }

    @Test
    public void testEqualsDifferentMMSI(){
        Ship ship2 = new Ship("123456779", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        assertFalse(ship.equals(ship2));
    }
}