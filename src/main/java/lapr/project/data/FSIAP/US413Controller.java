package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US413Controller {

    private Company c;
    private String data = "";
    private Energy energy;

    public US413Controller(int temp, String tripTime){
        c = App.getInstance().getCompany();
        energy = new Energy(temp, tripTime);
    }

    public US413Controller(Company c, int temp, String tripTime){
        this.c = c;
        energy = new Energy(temp, tripTime);
    }

    public void energyToSupply(String file) throws IOException {
        data = energy.energyToSupply(file);
        FileOperation.writeToAFile("Output/US413.txt", data);
    }
}
