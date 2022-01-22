package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US412Controller {

    private Company c;
    private String data = "";
    private Energy energy;

    public US412Controller(){
        c = App.getInstance().getCompany();
        energy = new Energy();
    }

    public US412Controller(Company c){
        this.c = c;
        energy = new Energy();
    }

    public void energyToSupply(String file) throws IOException {
        data = energy.energyToSupply(file);
        FileOperation.writeToAFile("Output/US412.txt", data);
    }
}
