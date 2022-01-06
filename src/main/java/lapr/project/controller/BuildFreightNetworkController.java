package lapr.project.controller;

import lapr.project.data.MatrixGraph;
import lapr.project.model.*;
import lapr.project.utils.Distances;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildFreightNetworkController {

    private Company company;

    public BuildFreightNetworkController(Company company){
        this.company = company;
    }

    public BuildFreightNetworkController(){
        this.company = App.getInstance().getCompany();
    }

    public void BuildFreightNetwork(int n) throws IOException {
        List<GraphElement> listCapitals = new ArrayList<>();
        List<GraphElement> listPorts = new ArrayList<>();
        List<String> closestsPortesTaken = new ArrayList<>();
        double minDistance = 0;
        GraphElement elementProx2 = null;
        MatrixGraph<GraphElement, Double> graph = new MatrixGraph<>(true);
        for (Country country : company.getCountryStore().getCountries()){ //Adiciona capitais
            listCapitals.add(new GraphElement(country));
        }
        for (Port port : (List<Port>) company.getKdtPorts().inOrder()){ //Adiciona portos
            listPorts.add(new GraphElement(port));
        }

        for (GraphElement element: listCapitals){ //Liga todas as capitais cujos países fazem fronteiras
            for (Country country : company.getBorderStore().getBordersCountry(element.getCountry())){
                GraphElement element2 = new GraphElement(country);
                graph.addEdge(element, element2, Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude())/1000);
                graph.addEdge(element2, element, Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude())/1000);
            }
        }

        for (GraphElement element: listPorts) { //Liga os portos do mesmo país
            for (Port port : (List<Port>) company.getKdtPorts().inOrder()) {
                if (element.getCountry().equals(port.getCountry().getName()) && !element.getDesignation().equals(port.getName())) {
                    if (company.getSeadistStore().getSeadist(element.getDesignation(), port.getName()) != null) {
                        GraphElement element2 = new GraphElement(port);
                        graph.addEdge(element, element2, (double) company.getSeadistStore().getSeadist(element.getDesignation(), port.getName()).getSeaDistance());
                        graph.addEdge(element2, element, (double) company.getSeadistStore().getSeadist(element.getDesignation(), port.getName()).getSeaDistance());
                    }
                    else if (company.getSeadistStore().getSeadist(port.getName(), element.getDesignation()) != null) {
                        GraphElement element2 = new GraphElement(port);
                        graph.addEdge(element, element2, (double) company.getSeadistStore().getSeadist(port.getName(), element.getDesignation()).getSeaDistance());
                        graph.addEdge(element2, element, (double) company.getSeadistStore().getSeadist(port.getName(), element.getDesignation()).getSeaDistance());
                    }
                }
            }
        }

        for (GraphElement element : listCapitals){ //Liga o porto mais próximo à capital
            minDistance = 0;
            elementProx2 = null;
            for (GraphElement element2 : listPorts){
                if ((minDistance > Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude())/1000 || minDistance == 0) && element.getCountry().equals(element2.getCountry())){
                    minDistance = Distances.distFrom(element.getLatitude(), element.getLongitude(), element2.getLatitude(), element2.getLongitude())/1000;
                    elementProx2 = element2;
                }
            }
            if (elementProx2 != null) {
                graph.addEdge(element, elementProx2, minDistance);
                graph.addEdge(elementProx2, element, minDistance);
            }
        }

        for (GraphElement element: listPorts) { //Liga os n portos mais próximos de um porto de qualquer país
            closestsPortesTaken = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                minDistance = 0;
                for (GraphElement element2 : listPorts) {
                    if (!element.getCountry().equals(element2.getCountry())) {
                        if (company.getSeadistStore().getSeadist(element.getDesignation(), element2.getDesignation()) != null) {
                            if ((minDistance > company.getSeadistStore().getSeadist(element.getDesignation(), element2.getDesignation()).getSeaDistance() || minDistance == 0) && !closestsPortesTaken.contains(element2.getDesignation())) {
                                minDistance = company.getSeadistStore().getSeadist(element.getDesignation(), element2.getDesignation()).getSeaDistance();
                                elementProx2 = element2;
                            }
                        } else if (company.getSeadistStore().getSeadist(element2.getDesignation(), element.getDesignation()) != null) {
                            if ((minDistance > company.getSeadistStore().getSeadist(element2.getDesignation(), element.getDesignation()).getSeaDistance() || minDistance == 0) && !closestsPortesTaken.contains(element2.getDesignation())) {
                                minDistance = company.getSeadistStore().getSeadist(element2.getDesignation(), element.getDesignation()).getSeaDistance();
                                elementProx2 = element2;
                            }
                        }
                    }
                }
                graph.addEdge(element, elementProx2, minDistance);
                closestsPortesTaken.add(elementProx2.getDesignation());
            }
        }
        company.setMatrixGraph(graph);
        StringBuilder data = new StringBuilder();
        int i = 1;
        for (GraphElement element : (List<GraphElement>)graph.vertices()){
            data.append(i + " - " + element.getDesignation() + "\n");
            i++;
        }
        data.append(graph);
        FileOperation.writeToAFile("Output/US301", data);
        graph.transitiveClosure();

    }


}
