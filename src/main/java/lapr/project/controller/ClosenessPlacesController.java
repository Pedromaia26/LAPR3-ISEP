package lapr.project.controller;

import lapr.project.data.ClosenessPlacesStore;
import lapr.project.model.ClosenessPlaces;
import lapr.project.model.Company;
import lapr.project.utils.FileOperation;

import java.io.IOException;

public class ClosenessPlacesController {

    private Company company;
    private ClosenessPlacesStore cps = App.getInstance().getCompany().getClosenessPlaceStore();
    private String data;

    public ClosenessPlacesController(Company company){
        this.company = company;
        this.data = new String();
    }

    public ClosenessPlacesController(){
        this.company = App.getInstance().getCompany();
        this.data = new String();
    }

    public void closenessPlacesByContinent(int n) throws IOException {

        int counter = 0;
        for (String continent : cps.getMap().keySet()){
            for (ClosenessPlaces cp : cps.getMap().get(continent)){
                String newData = String.format("%s: %s - %.2f km\n", continent, cp.getPlace(), cp.getDistance());
                data =  data + newData;
                counter++;
                if (counter == n){
                    counter = 0;
                    break;
                }
            }
        }
        FileOperation.writeToAFile("Output/closenessPlaces.txt", data);
    }
}
