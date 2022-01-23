package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US415Controller {
    private String data = "";
    private final Energy energy;

    public US415Controller(int temp, String tripTime, int sun1, int sun2){
        energy = new Energy(temp, tripTime, sun1, sun2);
    }

    public void auxiliaryPowerEquipment() throws IOException {
        data = energy.auxiliaryPowerEquipment();
        FileOperation.writeToAFile("Output/US415.txt", data);
    }
}
