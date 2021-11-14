package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ShipDynDataTest {

    ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");

    public ShipDynDataTest() throws ParseException {
    }


    @Test
    void getLatitude() {
        String expectedLatitude = "-66.97000";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    void getLongitude() {
        String expectedLongitude = "22.81780";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    void getSog() {
        float expectedsog = 14.4f;
        float sog = sdd.getSog();
        assertEquals(expectedsog, sog);
    }

    @Test
    void getCog() {
        float expectedcog = 11.2f;
        float cog = sdd.getCog();
        assertEquals(expectedcog, cog);
    }

    @Test
    void getHeading() {
        String expectedHeading = "347";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);
    }

    @Test
    void getBaseDateTime() throws ParseException {
        String expectedBaseDateTime = "31/12/2020 19:25";
        Date expectedBaseDateTime2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(expectedBaseDateTime);

        Date baseDateTime = sdd.getBaseDateTime();
        assertEquals(expectedBaseDateTime2, baseDateTime);
    }


    @Test
    void latitudeUnderNegative90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-96.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "NA";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    void latitudeEqualsNegative90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-90", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "-90";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    void latitudeOver90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "96.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "NA";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    void latitudeEquals90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "90", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "90";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    void longitudeUnderNegative180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "-192.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "NA";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    void longitudeEqualsNegative180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "-180",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "-180";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    void longitudeOver180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "200.13121",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "NA";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    void longitudeEquals180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "180",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "180";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    void cogUnder0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "-4.0", "347", "NA", "B");;
        double expectedCog = 356;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    void cogEquals0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "0", "347", "NA", "B");;
        double expectedCog = 0;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    void cogOver359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "374.0", "347", "NA", "B");;
        double expectedCog = 14;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    void cogEquals359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "359.0", "347", "NA", "B");;
        double expectedCog = 359;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    void headingUnder0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "-10", "NA", "B");;
        String expectedHeading = "NA";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    void headingEquals0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "0", "NA", "B");;
        String expectedHeading = "0";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    void headingOver359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "-10", "NA", "B");;
        String expectedHeading = "NA";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    void headingEquals359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "359", "NA", "B");;
        String expectedHeading = "359";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    void toStringTest() throws ParseException {
        ShipDynData sdd1 = new ShipDynData("31/12/2020 19:26", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd2 = new ShipDynData("31/12/2020 19:24", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd3 = new ShipDynData("31/12/2020 19:23", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd4 = new ShipDynData("31/12/2020 19:22", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd5 = new ShipDynData("31/12/2020 19:27", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        ShipDynData sdd6 = new ShipDynData("31/12/2020 19:28", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");
        BSTDynData<ShipDynData> bst = new BSTDynData<>();
        bst.insert(sdd);
        bst.insert(sdd1);
        bst.insert(sdd2);
        bst.insert(sdd3);
        bst.insert(sdd4);
        bst.insert(sdd5);
        bst.insert(sdd6);
        String expected = "31/12/2020 19:22 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:23 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:24 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:25 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:26 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:27 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n" +
                "31/12/2020 19:28 | LATITUDE: -66.97000; LONGITUDE: 22.81780\n";
        assertEquals(expected, bst.toString());
    }

}