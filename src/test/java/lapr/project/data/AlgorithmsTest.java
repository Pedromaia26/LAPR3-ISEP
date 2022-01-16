package lapr.project.data;

import lapr.project.model.Country;
import lapr.project.model.GraphElement;
import lapr.project.model.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    void transitiveClosure() throws IOException {
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Spain", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "France", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Switzerland", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Germany", 0.5f, "Berlim", 35, 25);
        Country country6 = new Country("America", "CL", "CHL", "Chile", 16.8f, "Santiago", -33.45f, -70.666667f);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        listGraph.add(new GraphElement(country6));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d, 2d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromDatabaseCountries();
        icontroller.importFromDatabaseBorders();
        icontroller.importFromDatabaseSeadists();
        Algorithms.transitiveClosure(matrix);
    }

    @Test
    void dijkstra() {
        List list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
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
        list = Algorithms.dijkstra(matrix, "Paris", "Lisboa", 3);
        list2.add(2);
        list2.add(0);
        assertEquals(list, list2);
    }

    @Test
    void dijkstraPathNotFound() {
        List<Integer> list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
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
        list = Algorithms.dijkstra(matrix, "Paris", "Lisboa", 2);
        assertEquals(list, list2);
    }

    @Test
    void dijkstraLandPath() {
        List<Integer> list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
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
        list = Algorithms.dijkstra(matrix, "Paris", "Lisboa", 1);
        list2.add(2);
        list2.add(0);
        assertEquals(list, list2);
    }

    @Test
    void shortestPath() throws IOException {
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromCSVCountry("countries.csv");
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Spain", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "France", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Switzerland", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Germany", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        Algorithms.shortestPath(matrix.getEdgeMatrix(matrix));
    }

}