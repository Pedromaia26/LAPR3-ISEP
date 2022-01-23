package lapr.project.controller;

import lapr.project.data.Physics.VesselCenterOfMass;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class VesselCenterOfMassController {

    private StringBuilder data;

    public VesselCenterOfMassController() {
        data = new StringBuilder();
    }

    public void getCenterOfMass(String typeOfShip) throws IOException {
        try{
            VesselCenterOfMass centerOfMass = new VesselCenterOfMass(typeOfShip);
            data.append("--- Center of mass of the " + typeOfShip + " ---\n");
            data.append("X Value: " + String.format("%.2f",centerOfMass.getCenterOfMassX()) + "m\n" +
                    "Y Value: " + String.format("%.2f",centerOfMass.getCenterOfMassY()) + "m\n" +
                    "\n--- Vessel Measurements ---\n" +
                    "Vessel Length: " + String.format("%.2f",centerOfMass.getVesselLength()) + "m\n" +
                    "Vessel Width: " + String.format("%.2f",centerOfMass.getVesselWidth()) + "m\n" +
                    "Vessel Height: " + String.format("%.2f",centerOfMass.getVesselHeight()) + "m\n" +
                    "Vessel Tip Length: " + String.format("%.2f",centerOfMass.getVesselTipLength()) + "m\n" +
                    "Vessel Tip Height: " + String.format("%.2f",centerOfMass.getVesselTipHeight()) + "m\n" +
                    "Vessel Body Length: " + String.format("%.2f",centerOfMass.getVesselBodyLength()) + "m\n" +
                    "Vessel Body Height: " + String.format("%.2f",centerOfMass.getVesselBodyHeight()) + "m\n" +
                    "Vessel Tower Length: " + String.format("%.2f",centerOfMass.getVesselTowerLength()) + "m\n" +
                    "Vessel Tower Height: " + String.format("%.2f",centerOfMass.getVesselTowerHeight()) + "m\n" +
                    "Vessel Material Density: " + String.format("%.2f",centerOfMass.getMATERIAL_DENSITY()) + "g/m3\n");
        }
        catch (IllegalArgumentException e){
            data.append("The vessel type is invalid.\n");
        }
        FileOperation.writeToAFile("Output/US418.txt", data);
    }
}
