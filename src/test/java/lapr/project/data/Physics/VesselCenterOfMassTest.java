package lapr.project.data.Physics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselCenterOfMassTest {

    VesselCenterOfMass centerOfMass = new VesselCenterOfMass("Container Ship");

    @Test
    void getVesselLength() {
        double expected = 390;
        assertEquals(expected, centerOfMass.getVesselLength());
    }

    @Test
    void getVesselWidth() {
        double expected = 50;
        assertEquals(expected, centerOfMass.getVesselWidth());
    }

    @Test
    void getVesselHeight() {
        double expected = 30;
        assertEquals(expected, centerOfMass.getVesselHeight());
    }

    @Test
    void getVesselTipLength() {
        double expected = 30;
        assertEquals(expected, centerOfMass.getVesselTipLength());
    }

    @Test
    void getVesselTipHeight() {
        double expected = 30;
        assertEquals(expected, centerOfMass.getVesselTipHeight());
    }

    @Test
    void getVesselBodyLength() {
        double expected = 330;
        assertEquals(expected, centerOfMass.getVesselBodyLength());
    }

    @Test
    void getVesselBodyHeight() {
        double expected = 30;
        assertEquals(expected, centerOfMass.getVesselBodyHeight());
    }

    @Test
    void getVesselTowerLength() {
        double expected = 20;
        assertEquals(expected, centerOfMass.getVesselTowerLength());
    }

    @Test
    void getVesselTowerHeight() {
        double expected = 5;
        assertEquals(expected, centerOfMass.getVesselTowerHeight());
    }

    @Test
    void getMATERIAL_DENSITY() {
        double expected = 7800;
        assertEquals(expected, centerOfMass.getMATERIAL_DENSITY());
    }

    @Test
    void getMass() {
        double expected = 78000;
        assertEquals(expected, centerOfMass.getMass(10));
    }

    @Test
    void getMass0() {
        double expected = 0;
        assertEquals(expected, centerOfMass.getMass(0));
    }

    @Test
    void getTipVolume() {
        double expected = 900;
        assertEquals(expected, centerOfMass.getTipVolume());
    }

    @Test
    void getBodyVolume() {
        double expected = 495000;
        assertEquals(expected, centerOfMass.getBodyVolume());
    }

    @Test
    void getTowerVolume() {
        double expected = 5000;
        assertEquals(expected, centerOfMass.getTowerVolume());
    }

    @Test
    void getCenterOfMassXTower0() {
        centerOfMass.calculateCenterOfMassX(0);
        double expected = centerOfMass.getVesselLength()/2;
        assertEquals(expected, centerOfMass.getCenterOfMassX());
    }

    @Test
    void getCenterOfMassXTower() {
        centerOfMass.calculateCenterOfMassX(centerOfMass.getVesselLength()/2);
        double expected = centerOfMass.getVesselLength()/2;
        assertEquals(expected, centerOfMass.getCenterOfMassX());
    }

    @Test
    void getCenterOfMassY() {
        centerOfMass.calculateCenterOfMassY();
        int expected = 15;
        assertEquals(expected, (int)centerOfMass.getCenterOfMassY());
    }
}