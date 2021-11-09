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
    public void getBaseDateTime() throws ParseException {
        String expectedBaseDateTime = "31/12/2020 19:25";
        Date expectedBaseDateTime2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(expectedBaseDateTime);

        Date baseDateTime = sdd.getBaseDateTime();
        assertEquals(expectedBaseDateTime2, baseDateTime);
    }

}