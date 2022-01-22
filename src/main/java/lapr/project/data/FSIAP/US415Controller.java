package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US415Controller {
    private Company c;
    private String data = "";
    private Energy energy;

    public US415Controller(int temp, String tripTime, int sun1, int sun2){
        c = App.getInstance().getCompany();
        energy = new Energy(temp, tripTime, sun1, sun2);
    }

    public US415Controller(Company c, int temp, String tripTime, int sun1, int sun2){
        this.c = c;
        energy = new Energy(temp, tripTime, sun1, sun2);
    }

    public void auxiliaryPowerEquipment() throws IOException {
        data = energy.auxiliaryPowerEquipment();
        FileOperation.writeToAFile("Output/US415.txt", data);
    }
}
