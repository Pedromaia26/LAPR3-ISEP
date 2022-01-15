package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.*;

public class CriticalPortsController {


    private Company company;
    private MatrixGraph mg;

    public CriticalPortsController() {
        this.company = App.getInstance().getCompany();
        this.mg = App.getInstance().getCompany().getMatrixGraph();
    }

    public CriticalPortsController(Company company, MatrixGraph matrixGraph) {
        this.company = company;
        this.mg = matrixGraph;
    }

    public void centrality(int n) throws IOException {
        KDTPort kdtPort = App.getInstance().getCompany().getKdtPorts();
        String out = "";
        int num = 0;
        int c;
        int count [] = new int[mg.numVertices()];
        List<Integer> places;
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        String o = null, d = null;
        for (int i = 0; i < mg.numVertices(); i++) {
            for (int j = 0; j < mg.numVertices(); j++) {
                if (i != j) {
                    GraphElement ge1 = (GraphElement) mg.vertex(i);
                    GraphElement ge2 = (GraphElement) mg.vertex(j);
                    o = ge1.getDesignation();
                    d = ge2.getDesignation();
                    places = mg.dijkstra(mg, o, d, 3);
                    for (int k = 0; k < places.size(); k++) {
                        GraphElement ge3 = (GraphElement) mg.vertex(places.get(k));
                        c = mg.key(ge3);
                        count[c]++;
                        map.put(ge3.getDesignation(), count[c]);
                    }
                }

            }
            }

        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        out += String.format("Ports with greater centrality (number of shortest paths that pass through it): \n\n");

        for (String key : sortedMap.keySet()) {
            for (Port port: (List<Port> )kdtPort.inOrder()){
                if (key.equals(port.getName())) {
                    num++;
                    out += (num + ". " + key + ": " + sortedMap.get(key) + "\n");


                }
            }
            if (num == n || num == sortedMap.size())
            break;
        }

        FileOperation.writeToAFile("Output/US401", out);
    }
}
