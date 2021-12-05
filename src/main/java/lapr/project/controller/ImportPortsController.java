package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.DatabaseOperations;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ImportPortsController {
    private Company company;
    public ImportPortsController(Company company){
        this.company = company;
    }

    public ImportPortsController(){
        this.company = App.getInstance().getCompany();
    }

    public void importFromCSV(String file) throws IOException {
        String line = "";
        KDTPort portKDT = company.getKdtPorts();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] portData = line.split(splitBy);
                Port port = new Port(portData[2], portData[3], portData[0], portData[1], portData[4], portData[5]);
                portKDT.insert(port, Double.parseDouble(portData[4]), Double.parseDouble(portData[5]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
        company.setKdtPorts(portKDT);
        DatabaseOperations databaseOperations = new DatabaseOperations();
        for (Country country : App.getInstance().getCompany().getCountryStore().getCountries()){
            App.getInstance().getCompany().getCountryStore().save(App.getInstance().getDatabaseConnection(), country);
        }

        for(Port port : (List<Port>) App.getInstance().getCompany().getKdtPorts().inOrder()){
            databaseOperations.saveLocation(App.getInstance().getDatabaseConnection(), port);
            databaseOperations.savePort(App.getInstance().getDatabaseConnection(), port);
        }
    }
}
