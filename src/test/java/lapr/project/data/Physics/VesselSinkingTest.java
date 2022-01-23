package lapr.project.data.Physics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselSinkingTest {

    VesselSinking vesselSinking = new VesselSinking("Container Ship", 100);

    @Test
    void getForceWithoutCargo() {
        double expected = 3.91404E13;
        assertEquals(expected, vesselSinking.getForceWithoutCargo());
    }

    @Test
    void getForceWithCargo() {
        double expected = 3.914045E13;
        assertEquals(expected, vesselSinking.getForceWithCargo());
    }

    @Test
    void getVesselPlacedMass() {
        double expected = 500*100;
        assertEquals(expected, vesselSinking.getVesselPlacedMass());
    }

    @Test
    void getVesselBaseArea() {
        double expected = 19500d;
        assertEquals(expected, vesselSinking.getVesselBaseArea());
    }

    @Test
    void getPressureWOCargo() {
        double expected = 2007200d;
        assertEquals(expected, vesselSinking.getPressureWOCargo());
    }

    @Test
    void getPressureWCargo() {
        int expected = 2007202;
        assertEquals(expected, (int)vesselSinking.getPressureWCargo());
    }

    @Test
    void getVesselHeightBeforerSink() {
        int expected = 15;
        assertEquals(expected, (int)vesselSinking.getVesselHeightBeforerSink());
    }

    @Test
    void getVesselSinking() {
        int expected = 15;
        assertEquals(expected, (int)vesselSinking.getVesselHeightBeforerSink());
    }

    @Test
    void getSeaWaterDensity() {
        double expected = 1030d;
        assertEquals(expected, vesselSinking.getSeaWaterDensity());
    }

    @Test
    void getVesselMass() {
        double expected = 50000d;
        assertEquals(expected, vesselSinking.getVesselMass());
    }

    @Test
    void getDraft() {
        double expected = 15d;
        assertEquals(expected, vesselSinking.getDraft());
    }

    @Test
    void getContainerMass() {
        double expected = 500d;
        assertEquals(expected, vesselSinking.getContainerMass());
    }

    @Test
    void getGravitationForce() {
        double expected = 10d;
        assertEquals(expected, vesselSinking.getGravitationForce());
    }

    @Test
    void getArea() {
        double expected = 19500d;
        assertEquals(expected, vesselSinking.getArea());
    }

    @Test
    void getPressureCargo() {
        int expected = 2007202;
        assertEquals(expected, (int)vesselSinking.getPressureCargo());
    }

    @Test
    void getPressureNoCargo() {
        int expected = 2007200;
        assertEquals(expected, (int)vesselSinking.getPressureNoCargo());
    }

    @Test
    void getVesselHeight() {
        int expected = 15;
        assertEquals(expected, (int)vesselSinking.getVesselHeight());
    }
}