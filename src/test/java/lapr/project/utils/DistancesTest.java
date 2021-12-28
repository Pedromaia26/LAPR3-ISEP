package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistancesTest {

    @Test
    void distFrom() {
        double lat1 = -66.97000;
        double lon1 = 22.81780;
        double lat2 = -66.98000;
        double lon2 = 22.81790;
        double expected = 1111.957763671875;
        assertEquals(expected, Distances.distFrom(lat1,lon1,lat2,lon2));
    }

    @Test
    void distFromSameLocal() {
        double lat1 = -66.97000;
        double lon1 = 22.81780;
        double lat2 = -66.97000;
        double lon2 = 22.81780;
        double expected = 0;
        assertEquals(expected, Distances.distFrom(lat1,lon1,lat2,lon2));
    }

    @Test
    void distFrom2Locals() {
        double lat1 = -66.98000;
        double lon1 = 22.81790;
        double lat2 = -66.97000;
        double lon2 = 22.81780;
        assertEquals(Distances.distFrom(lat2,lon2,lat1,lon1), Distances.distFrom(lat1,lon1,lat2,lon2));
    }

}