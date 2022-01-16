package lapr.project.controller;

import lapr.project.data.Algorithms;
import lapr.project.model.Company;
import lapr.project.model.GraphElement;
import lapr.project.model.MatrixGraph;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestPathController {

    private Company company;
    private MatrixGraph mg;

    public ShortestPathController() {
        this.company = App.getInstance().getCompany();
        this.mg = App.getInstance().getCompany().getMatrixGraph();
    }

    public ShortestPathController(Company company, MatrixGraph matrixGraph) {
        this.company = company;
        this.mg = matrixGraph;
    }

    public void shortestPath(String departure, String arrival, int path) throws IOException {
        String out = "";
        List<Integer> shortestPathList;

        shortestPathList = Algorithms.dijkstra(mg, departure, arrival, path);


        out += "Shortest path from " + departure + " to " + arrival + ":\n\n";

        if (!shortestPathList.isEmpty()){
            for (int i: shortestPathList) {
                GraphElement pl = (GraphElement) mg.vertex(i);
                if (pl.getDesignation().equals(arrival)){
                    out+=(pl.getDesignation());
                }else {
                    out += (pl.getDesignation() + " -> ");
                }
            }
        }else
            if (path == 1){
                out += "Could not reach the destination by land path!";
            }else if (path == 2){
                out +=  "Could not reach the destination by maritime path!";
            }else
                out +=  "Could not reach the destination by land/sea path!";



        FileOperation.writeToAFile("Output/US402.txt", out);


    }

}
