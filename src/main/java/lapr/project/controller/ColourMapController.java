package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.*;

public class ColourMapController {

    Company company;

    public ColourMapController(Company c){
        this.company = c;
    }

    public ColourMapController(){
        this.company = App.getInstance().getCompany();
    }

    public ArrayList<GraphElement> getCountriesDegree(){
        MatrixGraph<GraphElement, Double> graph = company.getMatrixGraph();
        ArrayList<Integer> degrees = new ArrayList<>();
        ArrayList<GraphElement> elements = new ArrayList<>();
        for (GraphElement element : graph.vertices()){
            if (company.getCountryStore().getCountryByCapital(element.getDesignation()) != null) {
                degrees.add(graph.adjVertices(element).size());
                elements.add(element);
            }
        }
        for (int i = 0; i < elements.size()-1; i++) {
            for (int j = 0; j < elements.size() - i - 1; j++) {
                if (degrees.get(j) < degrees.get(j + 1)) {
                    int temp = degrees.get(j);
                    degrees.set(j, degrees.get(j + 1));
                    degrees.set(j+1, temp);
                    GraphElement temp2 = elements.get(j);
                    elements.set(j, elements.get(j + 1));
                    elements.set(j+1, temp2);
                }
            }
        }
        /*for (GraphElement element : elements){
            System.out.println(element.getCountry());
        }*/
        return elements;
    }


    public Map<GraphElement, Integer> ColourMap() throws IOException {
        MatrixGraph<GraphElement, Double> graph = company.getMatrixGraph();
        ArrayList<GraphElement> elements = getCountriesDegree();
        Map<GraphElement, Integer> map = new HashMap<>();
        List<GraphElement> listColoured = new ArrayList<>();
        int colour = 1;
        for (int i = 0; i < elements.size(); i++){
            if (map.get(elements.get(i)) == null) {
                map.put(elements.get(i), colour);
                listColoured.add(elements.get(i));
                for (GraphElement element : elements) {
                    if (graph.adjVertices(element).stream().noneMatch(elemento -> listColoured.contains(elemento)) && map.get(element) == null) {
                        map.put(element, colour);
                        listColoured.add(element);
                    }
                }
                colour++;
                listColoured.clear();
            }
        }
        StringBuilder data = new StringBuilder();
        int k = 0;
        data.append("---- Map Coloured ----\n");
        for (GraphElement element : map.keySet()){
            data.append(element.getCountry() + " - " + map.get(element) + "\n");
            k++;
        }
        FileOperation.writeToAFile("Output/US302", data);
        return map;
    }
}
