package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US414Controller {
    private Company c;
    private String data = "";
    private Energy energy;

    public US414Controller(int temp, String tripTime, int sun1, int sun2){
        c = App.getInstance().getCompany();
        energy = new Energy(temp, tripTime, sun1, sun2);
    }

    public US414Controller(Company c, int temp, String tripTime, int sun1, int sun2){
        this.c = c;
        energy = new Energy(temp, tripTime, sun1, sun2);
    }

    public void exposedSidesEnergy() throws IOException {
        data = energy.exposedSidesEnergy();
        FileOperation.writeToAFile("Output/US414.txt", data);
    }
}
