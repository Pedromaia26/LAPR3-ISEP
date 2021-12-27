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
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] borderData = line.split(splitBy);
                Border border = new Border(borderData[0], borderData[1].trim());
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

    public void BuildFreightNetwork(int n) throws IOException {
        importFromDatabaseCountries();
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("sports.csv");
        importFromDatabaseBorders();
        importFromDatabaseSeadists();
        createGraph(n);
    }

    public void createGraph(int n){
        List<GraphElement> listCapitals = new ArrayList<>();
        List<GraphElement> listPorts = new ArrayList<>();
        List<GraphElement> closestsPortesTaken = new ArrayList<>();
        double minDistance = 0;
        GraphElement elementProx1 = null;
        GraphElement elementProx2 = null;
        MapGraph graph = new MapGraph(false);
        for (Country country : company.getCountryStore().getCountries()){ //Adiciona capitais
            listCapitals.add(new GraphElement(country));
        }
        for (Port port : (List<Port>) company.getKdtPorts().inOrder()){ //Adiciona portos
            listPorts.add(new GraphElement(port));
        }

        for (GraphElement element: listCapitals){ //Liga todas as capitais cujos países fazem fronteiras
            graph.addVertex(element);
            for (Country country : company.getBorderStore().getBordersCountry(element.getCountry())){
                GraphElement element2 = new GraphElement(country);
                graph.addEdge(element, element2, Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude()));
            }
        }

        for (GraphElement element: listPorts){ //Liga os portos do mesmo país
            if (!graph.vertices().contains(element)) graph.addVertex(element);
            for (Port port : (List<Port>) company.getKdtPorts().inOrder()){
                if (element.getCountry().equals(port.getCountry().getName()) && !element.getDesignation().equals(port.getName())){
                    GraphElement element2 = new GraphElement(port);
                    graph.addEdge(element, element2, Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude()));
                }
            }
        }

        for (GraphElement element : listCapitals){ //Liga o porto mais próximo à capital
            minDistance = 1000000000;
            for (GraphElement element2 : listPorts){
                if (minDistance > Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude()) && element.getCountry().equals(element2.getCountry())){
                    minDistance = Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude());
                    elementProx1 = element;
                    elementProx2 = element2;
                }
            }
            if (elementProx1 != null && elementProx2 != null) {
                graph.addEdge(elementProx1, elementProx2, minDistance);
            }
        }

        for (GraphElement element: listPorts){ //Liga os n portos mais próximos de um porto de qualquer país
            closestsPortesTaken = new ArrayList<>();
            for (int i = 0; i < n; i++){
                minDistance = 1000000000;
                for (GraphElement element2 : listPorts){
                    if (!element.getCountry().equals(element2.getCountry())){
                        if (minDistance > Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude()) && !closestsPortesTaken.contains(element2)){
                            minDistance = Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude());
                            elementProx1 = element;
                            elementProx2 = element2;
                        }
                    }
                }
                graph.addEdge(elementProx1, elementProx2, minDistance);
                closestsPortesTaken.add(elementProx2);
            }
        }
        MatrixGraph matrix = new MatrixGraph(graph);
        /*int i = 1;
        for (GraphElement element : (List<GraphElement>)matrix.vertices()){
            System.out.println(i + "----" + element.getDesignation() + "----");
            i++;
        }
        System.out.println(matrix);*/
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
            company.getBorderStore().save(App.getInstance().getDatabaseConnection(), border);
        }
    }

    public void insertSeadistsIntoDatabase(){
        for (Seadist seadist : company.getSeadistStore().getSeadists()){
            company.getSeadistStore().save(App.getInstance().getDatabaseConnection(), seadist);
        }
    }
}
