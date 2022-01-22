package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.data.ContainerStore;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.DatabaseOperations;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportContainersController {
    private Company company;

    public ImportContainersController(Company company) {
        this.company = company;
    }

    public ImportContainersController() {
        this.company = App.getInstance().getCompany();
    }

    public void importFromTXT(String file) throws IOException {
        Container[][][] disp;
        String line = "";
        ContainerStore containerStore = company.getContainerStore();
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            line = br.readLine();
            while (line != null) {
                String[] info = line.split(" ");
                Container container = new Container(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), info[5], info[6], info[7], Double.parseDouble(info[8]), Double.parseDouble(info[9]), Double.parseDouble(info[10]), Double.parseDouble(info[11]), Double.parseDouble(info[12]), Double.parseDouble(info[13]), Integer.parseInt(info[14]), Double.parseDouble(info[15]));
                containerStore.addContainer(container);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        company.setContainerStore(containerStore);

        containerStore.fillArray();
    }
}
