package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.FSIAP.Energy;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class US413Controller {

    private String data = "";
    private final Energy energy;

    public US413Controller(int temp, String tripTime){
        energy = new Energy(temp, tripTime);
    }

    public US413Controller(int temp, String tripTime, double area){
        energy = new Energy(temp, tripTime);
    }

    public void energyToSupply() throws IOException {
        data = energy.energyToSupply(13);
        FileOperation.writeToAFile("Output/US413.txt", data);
    }
}
