package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.CountryStore;
import lapr.project.model.Edge;
import lapr.project.model.CommonGraph;
import lapr.project.model.Graph;

import java.util.*;


/**
 * @param <GraphElement> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class MapGraph<GraphElement, E> extends CommonGraph<GraphElement, E> {


    final private Map<GraphElement, MapVertex<GraphElement, E>> mapVertices;  // all the Vertices of the graph

    // Constructs an empty graph (either undirected or directed)
    public MapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    public MapGraph(Graph<GraphElement,E> g) {
        this(g.isDirected());
        copy(g, this);
    }

    @Override
    public boolean validVertex(GraphElement vert) { return (mapVertices.get(vert) != null);   }

    @Override
    public Collection<GraphElement> adjVertices(GraphElement vert) {

        Collection<GraphElement> adjVertices = new ArrayList<>();

        Collection<Edge<GraphElement, E>> outEdges = outgoingEdges(vert);
        Collection<Edge<GraphElement, E>> inEdges = incomingEdges(vert);

        for (Edge<GraphElement, E> edge: outEdges) {
            if (!adjVertices.contains(edge.getVDest()))
                adjVertices.add(edge.getVDest());

        }
        return  adjVertices;
    }

    @Override
    public Collection<Edge<GraphElement, E>> edges() {

        ArrayList<Edge<GraphElement, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<GraphElement, E> mv : mapVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    @Override
    public Edge<GraphElement, E> edge(GraphElement vOrig, GraphElement vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<GraphElement, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    @Override
    public Edge<GraphElement, E> edge(int vOrigKey, int vDestKey) {
        GraphElement vOrig = vertex(vOrigKey);
        GraphElement vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    @Override
    public int outDegree(GraphElement vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<GraphElement, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

    @Override
    public int inDegree(GraphElement vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (GraphElement otherVert : mapVertices.keySet())
            if (edge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    @Override
    public Collection<Edge<GraphElement, E>> outgoingEdges(GraphElement vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<GraphElement, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

    @Override
    public Collection<Edge<GraphElement, E>> incomingEdges(GraphElement vert) {

        Collection<Edge<GraphElement, E>> incomingEdges = new ArrayList<>();

        if (!validVertex(vert))
            return null;

        Map<GraphElement, MapVertex<GraphElement, E>> mv = mapVertices;

        for (MapVertex<GraphElement, E> vertex: mv.values()) {
            for (Edge<GraphElement, E> edge: vertex.getAllOutEdges()) {
                if(edge.getVDest().equals(vert)){
                    incomingEdges.add(edge);
                }
            }
        }

        return incomingEdges;
    }

    @Override
    public boolean addVertex(GraphElement vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (validVertex(vert))
            return false;

        MapVertex<GraphElement, E> mv = new MapVertex<>(vert);
        vertices.add(vert);
        mapVertices.put(vert, mv);
        numVerts++;

        return true;
    }

    @Override
    public boolean addEdge(GraphElement vOrig, GraphElement vDest, E weight) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        MapVertex<GraphElement, E> mvo = mapVertices.get(vOrig);
        MapVertex<GraphElement, E> mvd = mapVertices.get(vDest);

        Edge<GraphElement, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<GraphElement, E> otherEdge = new Edge<>( mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }

    @Override
    public boolean removeVertex(GraphElement vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<GraphElement, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        MapVertex<GraphElement, E> mv = mapVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVerts();
        mapVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }

    @Override
    public boolean removeEdge(GraphElement vOrig, GraphElement vDest) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<GraphElement, E> edge = edge(vOrig, vDest);

        if (edge == null)
            return false;

        MapVertex<GraphElement, E> mvo = mapVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                MapVertex<GraphElement, E> mvd = mapVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    //Returns a clone of the graph
    @Override
    public MapGraph<GraphElement, E> clone() {

        MapGraph<GraphElement, E> g = new MapGraph<>(this.isDirected);

        copy(this,g);

        return g;
    }

    //string representation
    @Override
    public String toString() {
        String s;
        if (numVerts == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVerts + " vertices, " + numEdges + " edges\n";
            for (MapVertex<GraphElement, E> mv : mapVertices.values())
                s += mv + "\n";
        }
        s = s.replace('.', ',');
        return s;
    }
}
