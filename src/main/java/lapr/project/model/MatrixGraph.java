package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.ClosenessPlacesStore;
import lapr.project.data.CountryStore;
import lapr.project.utils.Distances;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 *
 * @author DEI-ISEP
 *
 */
public class MatrixGraph<V,E> extends CommonGraph<V,E> {

    public static final int INITIAL_CAPACITY = 10;
    public static final float RESIZE_FACTOR = 1.5F;

    Edge<V,E> [][] edgeMatrix;


    @SuppressWarnings("unchecked")
    public MatrixGraph(boolean directed, int initialCapacity) {
        super(directed);
        edgeMatrix = (Edge <V,E> [][])( new Edge<?, ?>[initialCapacity][initialCapacity]);
    }

    public MatrixGraph(boolean directed) {
        this(directed, INITIAL_CAPACITY);
    }

    public MatrixGraph(Graph <V,E> g) {
        this(g.isDirected(), g.numVertices());
        copy(g, this);
    }

    public MatrixGraph(boolean directed, ArrayList <V> vs, E [][] m) {
        this(directed, vs.size());
        numVerts = vs.size();
        vertices = new ArrayList<>(vs);
        for (int i = 0 ; i < numVerts ; i++)
            for (int j = 0 ; j < numVerts ; j++)
                if (j != i && m[i][j] != null)
                    addEdge(vertices.get(i), vertices.get(j),m[i][j]);
    }

    @Override
    public Collection<V> adjVertices(V vert) {
        int index = key(vert);
        if (index == -1)
            return null;

        ArrayList<V> outVertices = new ArrayList<>();
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[index][i] != null)
                outVertices.add(vertices.get(i));
        return outVertices;
    }

    @Override
    public Collection<Edge<V, E>> edges() {

        Collection<Edge<V, E>> edges = new ArrayList<>();
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (edgeMatrix[i][j] != null){
                    edges.add(edgeMatrix[i][j]);
                }
            }

        }

        return edges;
    }

    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0))
            return null;

        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        if (vOrigKey >= numVerts && vDestKey >= numVerts)
            return null;
        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public int outDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[vertKey][i] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public int inDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        Collection<Edge<V, E>> outgoingEdges = new ArrayList<>();
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (edgeMatrix[i][j] != null && edgeMatrix[i][j].getVOrig() == vert) {
                    outgoingEdges.add(edgeMatrix[i][j]);
                }
            }
        }
        return outgoingEdges;
    }

    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        Collection <Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1)
            return ce;

        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                ce.add(edgeMatrix[i][vertKey]);
        return ce;
    }

    @Override
    public boolean addVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey != -1)
            return false;

        vertices.add(vert);
        numVerts++;
        resizeMatrix();
        return true;
    }

    /**
     * Resizes the matrix when a new vertex increases the length of ArrayList
     */
    private void resizeMatrix() {
        if(edgeMatrix.length < numVerts){
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Edge <V,E>[][] temp = (Edge <V,E>[][]) new Edge<?, ?> [newSize][newSize];
            for (int i = 0; i < edgeMatrix.length; i++)
                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);
            edgeMatrix = temp;
        }
    }

    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {
        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        edgeMatrix[vOrigKey][vDestKey] = new Edge<>(vOrig, vDest, weight);
        numEdges++;
        if (!isDirected) {
            edgeMatrix[vDestKey][vOrigKey] = new Edge<>(vDest, vOrig, weight);
            numEdges++;
        }
        return true;
    }

    @Override
    public boolean removeVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return false;

        // first let's remove edges from the vertex
        for (int i = 0; i < numVerts; i++)
            removeEdge(vertKey,i);
        if (isDirected) {
            // first let's remove edges to the vertex
            for (int i = 0; i < numVerts; i++)
                removeEdge(i, vertKey);
        }

        // remove shifts left all vertices after the one removed
        // It is necessary to collapse the edge matrix
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[i][j] = edgeMatrix[i + 1][j];
            }
        }
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[j][i] = edgeMatrix[j][i + 1];
            }
        }
        for (int j = 0; j < numVerts; j++) {
            edgeMatrix[j][numVerts - 1] = null;
            edgeMatrix[numVerts - 1][j] = null;
        }

        vertices.remove(vert);
        numVerts--;
        return true;
    }

    private void removeEdge(int vOrigKey, int vDestKey) {
        if (edgeMatrix[vOrigKey][vDestKey] != null) {
            edgeMatrix[vOrigKey][vDestKey] = null;
            numEdges--;
        }
        if (!isDirected && (edgeMatrix[vDestKey][vOrigKey] != null)) {
            edgeMatrix[vDestKey][vOrigKey] = null;
            numEdges--;
        }
    }

    @Override
    public boolean removeEdge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0) || (edgeMatrix[vOrigKey][vDestKey] == null))
            return false;

        removeEdge(vOrigKey,vDestKey);
        return true;
    }

    @Override
    public MatrixGraph<V, E> clone() {
        MatrixGraph<V, E> g = new MatrixGraph<>(this.isDirected, this.edgeMatrix.length);

        copy(this,g);

        return g;
    }

    public void transitiveClosure (MatrixGraph<GraphElement, Double> adjMatrix) {

        MatrixGraph<GraphElement, Double> cloneAdjMatrix = new MatrixGraph(adjMatrix);
        GraphElement orig, dest;
        Edge<V, E> adj1, adj2;
        double distance, d1, d2;

        for (int k = 0; k < adjMatrix.numVertices(); k++) {
            for (int i = 0; i < adjMatrix.numVertices(); i++) {
                if (i != k && cloneAdjMatrix.edgeMatrix[i][k] != null) {
                    adj1 = (Edge<V, E>) cloneAdjMatrix.edgeMatrix[i][k];
                    //System.out.println(edgeMatrix[i][k].getDistance());
                    for (int j = 0; j < cloneAdjMatrix.edgeMatrix.length; j++) {
                        if (i != j && k != j && cloneAdjMatrix.edgeMatrix[k][j] != null) {
                            adj2 = (Edge<V, E>) cloneAdjMatrix.edgeMatrix[k][j];

                            orig = (GraphElement) adj1.getVOrig();
                            dest = (GraphElement) adj2.getVDest();

                            d1 = (Double) adj1.getDistance();
                            d2 = (Double) adj2.getDistance();


                            if (cloneAdjMatrix.edgeMatrix[i][j]==null){
                                distance = d1 + d2;
                                cloneAdjMatrix.addEdge(orig, dest, distance);
                            }

                            if (cloneAdjMatrix.edgeMatrix[i][j] != null && d1 + d2 < cloneAdjMatrix.edgeMatrix[i][j].getDistance()) {
                                distance = d1 + d2;
                                cloneAdjMatrix.edgeMatrix[i][j].setDistance(distance);
                            }
                        }
                    }
                }
            }
        }

        //System.out.println(cloneAdjMatrix.edgeMatrix);
        shortestPath(cloneAdjMatrix.edgeMatrix);
    }

    public void shortestPath(Edge<GraphElement, Double>[][] edgeMatrix) {
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

    public List<Integer> dijkstra(Graph<V,E> graph, String o, String d, int opt){
        int key = -1;
        int key2 = -1;
        for (int i = 0; i < graph.numVertices(); i++){
            GraphElement g = (GraphElement) graph.vertex(i);
            if (g.getDesignation().equals(o)){
                key = i;
            }else if (g.getDesignation().equals(d)){
                key2 = i;
            }
        }
        V vOrig;
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
                        for (V vDest : graph.adjVertices(vOrig)) {
                            keyDest = graph.key(vDest);
                            if (!visited[keyDest] && dist[keyDest] > dist[key] + (Double) edge(vOrig, vDest).getDistance()) {
                                dist[keyDest] = dist[key] + (Double) edge(vOrig, vDest).getDistance();
                                path_array[keyDest] = key;
                            }
                        }
                    }
                    key = getVertMinDist(dist, visited);
                }
            case 2:
                while (key != -1) {
                    visited[key] = true;
                    vOrig = graph.vertex(key);
                    vertex = (GraphElement) vOrig;
                    for (Port kdt : (List<Port>) kdtPort.inOrder()) {
                        if (kdt.getName().equals(vertex.getDesignation())) {
                            for (V vDest : graph.adjVertices(vOrig)) {
                                keyDest = graph.key(vDest);
                                        if (!visited[keyDest] && dist[keyDest] > dist[key] + (Double) edge(vOrig, vDest).getDistance()) {
                                            dist[keyDest] = dist[key] + (Double) edge(vOrig, vDest).getDistance();
                                            path_array[keyDest] = key;
                                        }
                            }
                            break;
                        }
                    }
                    key = getVertMinDist(dist, visited);
                }
            case 3:
                while (key != -1){
                    if (key == key2){
                        break;
                    }
                    visited[key] = true;
                    vOrig = graph.vertex(key);
                    for (V vDest : graph.adjVertices(vOrig)) {
                        keyDest = graph.key(vDest);
                        if (!visited[keyDest] && dist[keyDest] > dist[key] + (Double) edge(vOrig, vDest).getDistance()) {
                            dist[keyDest] = dist[key] + (Double) edge(vOrig, vDest).getDistance();
                            path_array[keyDest] = key;
                            }
                        }

                    key = getVertMinDist(dist, visited);
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

    public int getVertMinDist(double dist[], boolean visited[])   {
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

    /**
     * Returns a string representation of the graph.
     * Matrix only represents existence of Edge
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /*sb.append("Vertices:\n");
        for (int i = 0 ; i < numVerts ; i++)
            sb.append(vertices.get(i)+"\n");*/

        sb.append("\nMatrix:\n");

        sb.append("  ");
        for (int i = 0 ; i < numVerts ; i++)
        {
            sb.append(" |  "+ i + " ");
        }
        sb.append("\n");

        // aligned only when vertices < 10
        for (int i = 0 ; i < numVerts ; i++)
        {
            sb.append(" "+ i + " ");
            for (int j = 0 ; j < numVerts ; j++)
                if(edgeMatrix[i][j] != null)
                    sb.append("|  X  ");
                else
                    sb.append("|     ");
            sb.append("\n");
        }

        sb.append("\nEdges:\n");

        for (int i = 0; i < numVerts ; i++)
            for (int j = 0 ; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("From " + i + " to " + j + "-> "+ edgeMatrix[i][j] + "\n");

        sb.append("\n");

        return sb.toString();
    }
}

