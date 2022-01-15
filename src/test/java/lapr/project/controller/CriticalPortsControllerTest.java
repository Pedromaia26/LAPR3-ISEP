package lapr.project.controller;

import lapr.project.data.ImportCountriesBordersSeadists;
import lapr.project.model.Country;
import lapr.project.model.GraphElement;
import lapr.project.model.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

class CriticalPortsControllerTest {

    @Test
    void centrality() throws IOException {
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Funchal", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Brest", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        App.getInstance().getCompany().setMatrixGraph(matrix);
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromCSVCountry("countries.csv");
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("bports.csv");
        CriticalPortsController cpc = new CriticalPortsController();
        cpc.centrality(5);
    }
}