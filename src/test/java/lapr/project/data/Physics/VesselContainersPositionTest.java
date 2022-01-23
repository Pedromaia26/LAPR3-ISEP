package lapr.project.data.Physics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselContainersPositionTest {

    VesselContainersPosition vesselContainersPosition = new VesselContainersPosition(0);

    @Test
    void getContainerArea() {
        int expected = 73;
        assertEquals(expected, (int)vesselContainersPosition.getContainerArea());
    }

    @Test
    void getContainerVolume() {
        int expected = 38;
        assertEquals(expected, (int)vesselContainersPosition.getContainerVolume());
    }

    @Test
    void getCenterOfMassContainerX() {
        double expected = vesselContainersPosition.getContainerLenght()/2;
        assertEquals(expected, vesselContainersPosition.getCenterOfMassContainerX());
    }

    @Test
    void getCenterOfMassContainerY() {
        double expected = vesselContainersPosition.getContainerWidth()/2;
        assertEquals(expected, vesselContainersPosition.getCenterOfMassContainerY());
    }

    @Test
    void getCenterOfMassContainerZ() {
        double expected = vesselContainersPosition.getContainerHeight()/2;
        assertEquals(expected, vesselContainersPosition.getCenterOfMassContainerZ());
    }

    @Test
    void getContainerMass() {
        double expected = 298.74d;
        assertEquals(expected, vesselContainersPosition.getContainerMass());
    }

    @Test
    void getContainerLenght() {
        double expected = 6.06d;
        assertEquals(expected, vesselContainersPosition.getContainerLenght());
    }

    @Test
    void getContainerWidth() {
        double expected = 2.44d;
        assertEquals(expected, vesselContainersPosition.getContainerWidth());
    }

    @Test
    void getContainerHeight() {
        double expected = 2.59d;
        assertEquals(expected, vesselContainersPosition.getContainerHeight());
    }

    @Test
    void getVesselCenterOfMassX() {
        vesselContainersPosition.calculateCenterOfMassX();
        double expected = 195d;
        assertEquals(expected, vesselContainersPosition.getVesselCenterOfMassX());
    }

    @Test
    void getVesselCenterOfMassY() {
        vesselContainersPosition.calculateCenterOfMassY();
        int expected = 15;
        assertEquals(expected, (int)vesselContainersPosition.getVesselCenterOfMassY());
    }
}