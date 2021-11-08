package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchShipDtoTest {

    Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
    SearchShipDto shipdto = new SearchShipDto(ship);

    @Test
    void testToString() {
        String expected = "Mmsi: 123456789 Ship Name: ship Imo: 1000000000 Call Sign: callSign Vessel Type: A Lenght: 100 Width: 500  Draft: 3.0";
        assertEquals(expected, shipdto.toString());
    }

    @Test
    void getMmsi() {
        int mmsi = shipdto.getMmsi();
        int expected = 123456789;
        assertEquals(expected, mmsi);
    }

    @Test
    void setMmsi() {
        int mmsi = 123123123;
        shipdto.setMmsi(mmsi);
        assertEquals(mmsi, shipdto.getMmsi());
    }

    @Test
    void getShipName() {
        String name = shipdto.getShipName();
        String expected = "ship";
        assertEquals(expected, name);
    }

    @Test
    void setShipName() {
        String name = "name";
        shipdto.setShipName(name);
        assertEquals(name, shipdto.getShipName());
    }

    @Test
    void getImo() {
        String imo = shipdto.getImo();
        String expected = "1000000000";
        assertEquals(expected, imo);
    }

    @Test
    void setImo() {
        String imo = "1000000000";
        shipdto.setImo(imo);
        assertEquals(imo, shipdto.getImo());
    }

    @Test
    void getCallSign() {
        String callSign = shipdto.getCallSign();
        String expected = "callSign";
        assertEquals(expected, callSign);
    }

    @Test
    void setCallSign() {
        String callSign = "cs";
        shipdto.setCallSign(callSign);
        assertEquals(callSign, shipdto.getCallSign());
    }

    @Test
    void getVesselType() {
        String vesselType = shipdto.getVesselType();
        String expected = "A";
        assertEquals(expected, vesselType);
    }

    @Test
    void setVesselType() {
        String vessel = "C";
        shipdto.setVesselType(vessel);
        assertEquals(vessel, shipdto.getVesselType());
    }

    @Test
    void getLenght() {
        int lenght = shipdto.getLenght();
        int expected = 100;
        assertEquals(expected, lenght);
    }

    @Test
    void setLenght() {
        int length = 150;
        shipdto.setLenght(length);
        assertEquals(length, shipdto.getLenght());
    }

    @Test
    void getWidth() {
        int width = shipdto.getWidth();
        int expected = 500;
        assertEquals(expected, width);
    }

    @Test
    void setWidth() {
        int width = 400;
        shipdto.setWidth(width);
        assertEquals(width, shipdto.getWidth());
    }

    @Test
    void getDraft() {
        float draft = shipdto.getDraft();
        float expected = 3;
        assertEquals(expected, draft);
    }

    @Test
    void setDraft() {
        float draft = 4;
        shipdto.setDraft(draft);
        assertEquals(draft, shipdto.getDraft());
    }
}