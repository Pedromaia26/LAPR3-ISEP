package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.DatabaseOperations;
import lapr.project.model.*;
import lapr.project.utils.Distances;

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
                company.getCountryStore().addCountry(country);
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
        Country country1 = null;
        Country country2 = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] borderData = line.split(splitBy);
                country1 = company.getCountryStore().getCountry(borderData[0]);
                country2 = company.getCountryStore().getCountry(borderData[1].trim());
                Border border = new Border(country1, country2);
                company.getBorderStore().addBorder(border);
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
                company.getSeadistStore().addSeadist(seadist);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void importFromDatabaseCountries(){
        DatabaseOperations databaseOperations = new DatabaseOperations();
        databaseOperations.importContriesFromDatabase();
    }

    public void importFromDatabaseBorders(){
        DatabaseOperations databaseOperations = new DatabaseOperations();
        databaseOperations.importBordersFromDatabase();

    }

    public void importFromDatabaseSeadists(){
        DatabaseOperations databaseOperations = new DatabaseOperations();
        databaseOperations.importSeadistsFromDatabase();

    }

    public void insertCountriesIntoDatabase(){
        for (Country country : company.getCountryStore().getCountries()){
            company.getCountryStore().save(App.getInstance().getDatabaseConnection(), country);
        }
    }

    public void insertBordersIntoDatabase(){
        for (Border border : company.getBorderStore().getBorders()){
            System.out.println(border.getCountryname1().getName());
            company.getBorderStore().save(App.getInstance().getDatabaseConnection(), border);
        }
    }

    public void insertSeadistsIntoDatabase(){
        for (Seadist seadist : company.getSeadistStore().getSeadists()){
            company.getSeadistStore().save(App.getInstance().getDatabaseConnection(), seadist);
        }
    }
}
