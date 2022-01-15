package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Edge;
import lapr.project.model.Graph;
import lapr.project.model.GraphElement;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceGreatestLocationCircuitController {

    private Company company;

    public ShortestDistanceGreatestLocationCircuitController(Company c){
        company = c;
    }

    public ShortestDistanceGreatestLocationCircuitController(){
        company = App.getInstance().getCompany();
    }

    public void getCircuit(String sourceDesignation) throws IOException {
        StringBuilder data = new StringBuilder();
        GraphElement element = null;
        GraphElement orig;
        GraphElement dest;
        double totalDistance = 0;
        for (GraphElement o : (List<GraphElement>) company.getMatrixGraph().vertices()){
            if (o.getDesignation().equals(sourceDesignation)){
                element = o;
            }
        }
        if (element == null){
            data.append("No element found with the designation.");
        }
        else{
            ArrayList<Edge> circuit = calculateCircuit(element);
            if (circuit != null){
                data.append("--- Circuit of " + element.getDesignation() + " ---\n");
                for (Edge edge : circuit){
                    orig = (GraphElement)edge.getVOrig();
                    dest = (GraphElement)edge.getVDest();
                    data.append(orig.getDesignation() + " -> " + dest.getDesignation() + "\n");
                    totalDistance += (double)edge.getDistance();
                }
                data.append("Total distance: " + String.format("%.2f", totalDistance) + "km\n");
            }
            else{
                data.append("Is not possible to calculate the circuit.");
            }
        }

        FileOperation.writeToAFile("Output/US403.txt", data);
    }

    public ArrayList<Edge> calculateCircuit(GraphElement element){
        ArrayList<Edge> circuit = new ArrayList<>();
        ArrayList<GraphElement> verticesTaken = new ArrayList<>();
        boolean flag = false;
        GraphElement takenElement = null;
        GraphElement actualElement = element;
        double mindist;
        verticesTaken.add(element);
        while (!flag){
            mindist = Double.POSITIVE_INFINITY;
            if (circuit.size() == 1 && verticesTaken.size() > 2){
                circuit.add(new Edge(circuit.get(0).getVDest(), circuit.get(0).getVOrig(), circuit.get(0).getDistance()));
                return circuit;
            }
            for (GraphElement e : (List<GraphElement>) company.getMatrixGraph().adjVertices(actualElement)){
                if (((double)company.getMatrixGraph().edge(actualElement, e).getDistance() < mindist && (!verticesTaken.contains(e) || (e.equals(element) && verticesTaken.size() > 2 && circuit.size() > 1)))){
                    mindist = (double)company.getMatrixGraph().edge(actualElement, e).getDistance();
                    takenElement = e;
                }
            }
            if (mindist != Double.POSITIVE_INFINITY) {
                verticesTaken.add(takenElement);
                circuit.add(company.getMatrixGraph().edge(actualElement, takenElement));
                actualElement = takenElement;
            }
            else{
                if (verticesTaken.indexOf(actualElement) == 0){
                    return null;
                }
                actualElement = verticesTaken.get(verticesTaken.indexOf(actualElement)-1);
                if (circuit.size() > 1){
                    circuit.remove(circuit.size()-1);
                }
            }

            if (takenElement.equals(element)){
                flag = true;
            }
        }
        return circuit;
    }

}
