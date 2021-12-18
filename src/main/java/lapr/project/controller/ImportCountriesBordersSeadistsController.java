package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.DatabaseOperations;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportCountriesBordersSeadistsController {
    private Company company;
    public ImportCountriesBordersSeadistsController(Company company){
        this.company = company;
    }

    public ImportCountriesBordersSeadistsController(){
        this.company = App.getInstance().getCompany();
    }

    public void importFromCSVCountry(String file) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] countryData = line.split(splitBy);
                Country country = new Country(countryData[0], countryData[1], countryData[2], countryData[3], Float.parseFloat(countryData[4]), countryData[5], Float.parseFloat(countryData[6]), Float.parseFloat(countryData[7]));
                App.getInstance().getCompany().getCountryStore().addCountry(country);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void importFromCSVBorders(String file) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] borderData = line.split(splitBy);
                Border border = new Border(borderData[0], borderData[1].trim());
                App.getInstance().getCompany().getBorderStore().addBorder(border);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void importFromCSVSeadist(String file) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] seadistData = line.split(splitBy);
                Seadist seadist = new Seadist(seadistData[0], Integer.parseInt(seadistData[1]), seadistData[2], seadistData[3], Integer.parseInt(seadistData[4]), seadistData[5], Integer.parseInt(seadistData[6]));
                App.getInstance().getCompany().getSeadistStore().addSeadist(seadist);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void insertCountriesIntoDatabase(){
        DatabaseOperations databaseOperations = new DatabaseOperations();
        for (Country country : App.getInstance().getCompany().getCountryStore().getCountries()){
            App.getInstance().getCompany().getCountryStore().save(App.getInstance().getDatabaseConnection(), country);
        }
    }

    public void insertBordersIntoDatabase(){
        for (Border border : App.getInstance().getCompany().getBorderStore().getBorders()){
            App.getInstance().getCompany().getBorderStore().save(App.getInstance().getDatabaseConnection(), border);
        }
    }

    public void insertSeadistsIntoDatabase(){
        for (Seadist seadist : App.getInstance().getCompany().getSeadistStore().getSeadists()){
            App.getInstance().getCompany().getSeadistStore().save(App.getInstance().getDatabaseConnection(), seadist);
        }
    }
}
