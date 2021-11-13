package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ShipDynDataTest {

    ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-66.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");

    public ShipDynDataTest() throws ParseException {
    }


    @Test
    public void getLatitude() {
        String expectedLatitude = "-66.97000";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    public void getLongitude() {
        String expectedLongitude = "22.81780";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    public void getSog() {
        float expectedsog = 14.4f;
        float sog = sdd.getSog();
        assertEquals(expectedsog, sog);
    }

    @Test
    public void getCog() {
        float expectedcog = 11.2f;
        float cog = sdd.getCog();
        assertEquals(expectedcog, cog);
    }

    @Test
    public void getHeading() {
        String expectedHeading = "347";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);
    }

    @Test
    public void getBaseDateTime() throws ParseException {
        String expectedBaseDateTime = "31/12/2020 19:25";
        Date expectedBaseDateTime2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(expectedBaseDateTime);

        Date baseDateTime = sdd.getBaseDateTime();
        assertEquals(expectedBaseDateTime2, baseDateTime);
    }


    @Test
    public void latitudeUnderNegative90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-96.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "NA";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    public void latitudeEqualsNegative90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "-90", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "-90";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    public void latitudeOver90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "96.97000", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "NA";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    public void latitudeEquals90() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "90", "22.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLatitude = "90";
        String latitude = sdd.getLatitude();
        assertEquals(expectedLatitude, latitude);
    }

    @Test
    public void longitudeUnderNegative180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "-192.81780",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "NA";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    public void longitudeEqualsNegative180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "-180",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "-180";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    public void longitudeOver180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "200.13121",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "NA";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    public void longitudeEquals180() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "180",  "14.4", "11.2", "347", "NA", "B");;
        String expectedLongitude = "180";
        String longitude = sdd.getLongitude();
        assertEquals(expectedLongitude, longitude);
    }

    @Test
    public void cogUnder0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "-4.0", "347", "NA", "B");;
        double expectedCog = 356;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    public void cogEquals0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "0", "347", "NA", "B");;
        double expectedCog = 0;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    public void cogOver359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "374.0", "347", "NA", "B");;
        double expectedCog = 14;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    public void cogEquals359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "359.0", "347", "NA", "B");;
        double expectedCog = 359;
        double cog = sdd.getCog();
        assertEquals(expectedCog, cog);
    }

    @Test
    public void headingUnder0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "-10", "NA", "B");;
        String expectedHeading = "NA";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    public void headingEquals0() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "0", "NA", "B");;
        String expectedHeading = "0";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    public void headingOver359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "-10", "NA", "B");;
        String expectedHeading = "NA";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

    @Test
    public void headingEquals359() throws ParseException {
        ShipDynData sdd = new ShipDynData("31/12/2020 19:25", "76.97000", "22.13121",  "14.4", "11.2", "359", "NA", "B");;
        String expectedHeading = "359";
        String heading = sdd.getHeading();
        assertEquals(expectedHeading, heading);

    }

}