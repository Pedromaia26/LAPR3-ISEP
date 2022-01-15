package lapr.project.controller;

import lapr.project.model.Country;
import lapr.project.model.GraphElement;
import lapr.project.model.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathControllerTest {

    @Test
    void shortestPath() throws IOException {
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
        App.getInstance().getCompany().setMatrixGraph(matrix);
        ShortestPathController spc = new ShortestPathController();
        spc.shortestPath("Paris", "Berlim", 3);
    }

    @Test
    void shortestPathMaritimePathFail() throws IOException {
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        Country country6 = new Country("America","PE","PER","Peru",28.22f,"Lima",-12.05f,-77.05f);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        App.getInstance().getCompany().setMatrixGraph(matrix);
        ShortestPathController spc = new ShortestPathController();
        spc.shortestPath("Paris", "Berlim", 2);
    }

    @Test
    void shortestPathLandPathFail() throws IOException {
        Country country1 = new Country("America","PE","PER","Peru",28.22f,"Callao",-12.05f,-77.05f);
        Country country2 = new Country("America","CO","COL","Colombia",46.86f,"Bogota",4.6f,-74.083333f);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        Object[][] m = {{10d, null}, {1d, null}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        App.getInstance().getCompany().setMatrixGraph(matrix);
        ShortestPathController spc = new ShortestPathController();
        spc.shortestPath("Callao", "Bogota", 1);
    }

    @Test
    void shortestPathLandAndSeaPathFail() throws IOException {
        Country country1 = new Country("America","PE","PER","Peru",28.22f,"Callao",-12.05f,-77.05f);
        Country country2 = new Country("America","CO","COL","Colombia",46.86f,"Bogota",4.6f,-74.083333f);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        Object[][] m = {{10d, null}, {1d, null}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        App.getInstance().getCompany().setMatrixGraph(matrix);
        ShortestPathController spc = new ShortestPathController();
        spc.shortestPath("Callao", "Bogota", 3);
    }
}