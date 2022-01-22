package lapr.project.controller;

import lapr.project.data.Physics.VesselCenterOfMass;
import lapr.project.data.Physics.VesselContainersPosition;
import lapr.project.data.Physics.VesselSinking;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class VesselSinkingController {

    private StringBuilder data;

    public VesselSinkingController() {
        data = new StringBuilder();
    }

    public void getVesselSank(String typeOfShip, int numberOfContainers) throws IOException {
        VesselSinking vesselSinking = new VesselSinking(typeOfShip, numberOfContainers);
        data.append("--- " + typeOfShip + " Sinking - " + numberOfContainers + " containers ---\n");
        data.append("Container Mass: " + String.format("%.2f",vesselSinking.getContainerMass()) + "kg\n" +
                "Sea water density considered: " + String.format("%.2f",vesselSinking.getSeaWaterDensity()) + "g/cm3\n" +
                "Gravitational force considered: " + String.format("%.2f",vesselSinking.getGravitationForce()) + "m/s2\n" +
                "Vessel area of contact: " + String.format("%.2f",vesselSinking.getArea()) + "m2\n" +
                "Vessel Placed Mass: " + String.format("%.2f",vesselSinking.getVesselMass()) + "kg\n" +
                "Pressure exerted by the vessel on the water without cargo: " + String.format("%.2f",vesselSinking.getPressureNoCargo()) + "Pa\n" +
                "Pressure exerted by the vessel on the water with cargo: " + String.format("%.2f",vesselSinking.getPressureCargo()) + "Pa\n" +
                "Height before sinking: " + String.format("%.2f",vesselSinking.getVesselHeight()) + "m\n" +
                "Height After sinking: " + String.format("%.2f",vesselSinking.getDraft()) + "m\n" +
                "Difference in height that the vessel has suffered: " + String.format("%.2f",vesselSinking.getVesselSinking()) + "m\n");
        FileOperation.writeToAFile("Output/US420.txt", data);
    }

}
