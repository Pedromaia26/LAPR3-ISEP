package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US412Controller {

    private String data = "";
    private final Energy energy;

    public US412Controller(){
        energy = new Energy();
    }

    public void energyToSupply() throws IOException {
        data = energy.energyToSupply(12);
        FileOperation.writeToAFile("Output/US412.txt", data);
    }
}
