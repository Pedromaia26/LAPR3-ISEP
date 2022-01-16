package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithms {

    public static void shortestPath(Edge<GraphElement, Double>[][] edgeMatrix) {
        double shortestPath = 0;
        CountryStore cs = App.getInstance().getCompany().getCountryStore();
        GraphElement place = null;
        int counter = 0;
        ClosenessPlacesStore cps = App.getInstance().getCompany().getClosenessPlaceStore();
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (edgeMatrix[i][j] != null) {
                    place = edgeMatrix[i][j].getVOrig();
                    if (cs.getContinentByCountry(edgeMatrix[i][j].getVOrig().getCountry()).equals(cs.getContinentByCountry(edgeMatrix[i][j].getVDest().getCountry()))) {
                        shortestPath = shortestPath + edgeMatrix[i][j].getDistance();
                        counter++;
                    }
                }
            }
            if (place != null && counter != 0) {
                ClosenessPlaces cp = new ClosenessPlaces(place, shortestPath / (counter));
                cps.addClosenessPlaces(cp);
                shortestPath = 0;
                counter = 0;
            }
        }
    }

    public static void transitiveClosure(MatrixGraph<GraphElement, Double> adjMatrix) {

        MatrixGraph<GraphElement, Double> cloneAdjMatrix = new MatrixGraph(adjMatrix);
        GraphElement orig, dest;
        Edge<GraphElement, Double> adj1, adj2;
        double distance, d1, d2;

        Edge<GraphElement,Double>[][] edgeMatrix = cloneAdjMatrix.getEdgeMatrix(cloneAdjMatrix);

        for (int k = 0; k < adjMatrix.numVertices(); k++) {
            for (int i = 0; i < adjMatrix.numVertices(); i++) {
                if (i != k && edgeMatrix[i][k] != null) {
                    adj1 = edgeMatrix[i][k];
                    //System.out.println(edgeMatrix[i][k].getDistance());
                    for (int j = 0; j < edgeMatrix.length; j++) {
                        if (i != j && k != j && edgeMatrix[k][j] != null) {
                            adj2 = edgeMatrix[k][j];

                            orig = (GraphElement) adj1.getVOrig();
                            dest = (GraphElement) adj2.getVDest();

                            d1 = (Double) adj1.getDistance();
                            d2 = (Double) adj2.getDistance();


                            if (edgeMatrix[i][j]==null){
                                distance = d1 + d2;
                                cloneAdjMatrix.addEdge(orig, dest, distance);
                            }

                            if (edgeMatrix[i][j] != null && d1 + d2 < edgeMatrix[i][j].getDistance()) {
                                distance = d1 + d2;
                                edgeMatrix[i][j].setDistance(distance);
                            }
                        }
                    }
                }
            }
        }

        //System.out.println(cloneAdjMatrix.edgeMatrix);
        Algorithms.shortestPath(edgeMatrix);
    }

    public static List<Integer> dijkstra(Graph<GraphElement,Double> graph, String o, String d, int opt){
        int key = -1;
        int key2 = -1;
        for (int i = 0; i < graph.numVertices(); i++){
            GraphElement g = graph.vertex(i);
            if (g.getDesignation().equals(o)){
                key = i;
            }else if (g.getDesignation().equals(d)){
                key2 = i;
            }
        }
        GraphElement vOrig;
        int keyDest;
        int orig = key;
        GraphElement vertex;
        CountryStore cs = App.getInstance().getCompany().getCountryStore();
        KDTPort kdtPort = App.getInstance().getCompany().getKdtPorts();
        double inf = Double.POSITIVE_INFINITY;
        List <Integer> list = new ArrayList<>();
        boolean visited[] = new boolean[graph.numVertices()];
        double [] dist = new double[graph.numVertices()];
        int [] path_array = new int[graph.numVertices()];
        for (int i = 0; i < graph.numVertices(); i++) {
            dist[i] = inf;
            visited[i] = false;
        }
        dist[key] = 0;


        switch (opt) {
            case 1:
                int selected = key;
                while (key != -1) {
                    visited[key] = true;
                    vOrig = graph.vertex(key);
                    vertex = (GraphElement) vOrig;
                    if (cs.getCountryByCapital(vertex.getDesignation()) != null || key == orig || selected == key2) {
                        for (GraphElement vDest : graph.adjVertices(vOrig)) {
                            keyDest = graph.key(vDest);
                            if (!visited[keyDest] && dist[keyDest] > dist[key] + graph.edge(vOrig, vDest).getDistance()) {
                                dist[keyDest] = dist[key] + (Double) graph.edge(vOrig, vDest).getDistance();
                                path_array[keyDest] = key;
                            }
                        }
                    }
                    key = getVertMinDist(dist, visited, graph.numVertices());
                }
            case 2:
                while (key != -1) {
                    visited[key] = true;
                    vOrig = graph.vertex(key);
                    vertex = vOrig;
                    for (Port kdt : (List<Port>) kdtPort.inOrder()) {
                        if (kdt.getName().equals(vertex.getDesignation())) {
                            for (GraphElement vDest : graph.adjVertices(vOrig)) {
                                keyDest = graph.key(vDest);
                                if (!visited[keyDest] && dist[keyDest] > dist[key] + graph.edge(vOrig, vDest).getDistance()) {
                                    dist[keyDest] = dist[key] + graph.edge(vOrig, vDest).getDistance();
                                    path_array[keyDest] = key;
                                }
                            }
                            break;
                        }
                    }
                    key = getVertMinDist(dist, visited, graph.numVertices());
                }
            case 3:
                while (key != -1){
                    if (key == key2){
                        break;
                    }
                    visited[key] = true;
                    vOrig = graph.vertex(key);
                    for (GraphElement vDest : graph.adjVertices(vOrig)) {
                        keyDest = graph.key(vDest);
                        if (!visited[keyDest] && dist[keyDest] > dist[key] + graph.edge(vOrig, vDest).getDistance()) {
                            dist[keyDest] = dist[key] + graph.edge(vOrig, vDest).getDistance();
                            path_array[keyDest] = key;
                        }
                    }

                    key = getVertMinDist(dist, visited, graph.numVertices());
                }
        }
        GraphElement source = (GraphElement) graph.vertex(orig);
        GraphElement destination = (GraphElement) graph.vertex(key2);
        if (dist[key2]==inf){
            //System.out.println("Could not reach the destination by the selected path!");
        }else {
            //System.out.printf("Distance from %s to %s: %.2f km.\n", source.getDesignation(), destination.getDesignation(), dist[key2]);
            //System.out.print("\nSHORTEST PATH: ");
            int local = key2;
            do {
                list.add(local);
                local = path_array[local];

            } while (local != orig);
            list.add(orig);

            Collections.reverse(list);
                    /*for (int i: list) {
                        GraphElement pl = (GraphElement) graph.vertex(i);
                       if (pl.getDesignation().equals(destination.getDesignation())){
                            System.out.println(pl.getDesignation());
                       }else {
                           System.out.print(pl.getDesignation() + " -> ");
                       }
                    }*/
        }
        return list;
    }

    public static int getVertMinDist(double dist[], boolean visited[], int numVerts)   {
        double min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < numVerts; v++) {
            if (visited[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }
}
