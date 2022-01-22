package lapr.project.controller;

import lapr.project.data.Physics.VesselContainersPosition;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class VesselContainersPositionController {

    private StringBuilder data;

    public VesselContainersPositionController() {
        data = new StringBuilder();
    }

    public void getPositionContainers(int numberOfContainers) throws IOException {
        VesselContainersPosition vesselContainersPosition = new VesselContainersPosition(numberOfContainers);
        data.append("--- Info of the container ---\n");
        data.append("Length: " + String.format("%.2f",vesselContainersPosition.getContainerLenght()) + "m\n" +
                "Width: " + String.format("%.2f",vesselContainersPosition.getContainerWidth()) + "m\n" +
                "Height: " + String.format("%.2f",vesselContainersPosition.getContainerHeight()) + "m\n" +
                "Mass: " + String.format("%.2f",vesselContainersPosition.getContainerMass()) + "kg\n" +
                "Area: " + String.format("%.2f",vesselContainersPosition.getContainerArea()) + "m2\n" +
                "Volume: " + String.format("%.2f",vesselContainersPosition.getContainerVolume()) + "m3\n" +
                "Center of mass in X: " + String.format("%.2f",vesselContainersPosition.getCenterOfMassContainerX()) + "m\n" +
                "Center of mass in Y: " + String.format("%.2f",vesselContainersPosition.getCenterOfMassContainerY()) + "m\n" +
                "Center of mass in Z: " + String.format("%.2f",vesselContainersPosition.getCenterOfMassContainerZ()) + "m\n" +
                "\n--- Vessel Center of Mass ---\n" +
                "X Value: " + String.format("%.2f",vesselContainersPosition.getVesselCenterOfMassX()) + "m\n" +
                "Y Value: " + String.format("%.2f",vesselContainersPosition.getVesselCenterOfMassY()) + "m\n");
        FileOperation.writeToAFile("Output/US419.txt", data);
    }
}
