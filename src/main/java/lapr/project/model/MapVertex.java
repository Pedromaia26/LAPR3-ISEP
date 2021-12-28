package lapr.project.model;

import lapr.project.model.Edge;

import java.util.*;

/**
 * @param <GraphElement>
 * @param <E>
 * @author DEI-ESINF
 */
public class MapVertex<GraphElement, E> {

    final private GraphElement element;                            // Vertex information
    final private Map<GraphElement, Edge<GraphElement, E>> outVerts;    // Adjacent vertices

    public MapVertex(GraphElement vert) {
        if (vert == null) throw new RuntimeException("Vertice information cannot be null!");
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    public GraphElement getElement() {
        return element;
    }

    public void addAdjVert(GraphElement vAdj, Edge<GraphElement, E> edge) {
        outVerts.put(vAdj, edge);
    }

    public void remAdjVert(GraphElement vAdj) {
        outVerts.remove(vAdj);
    }

    public Edge<GraphElement, E> getEdge(GraphElement vAdj) {
        return outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return outVerts.size();
    }

    public Collection<GraphElement> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    public Collection<Edge<GraphElement, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    @Override
    public String toString() {
        String st = element + ": \n";
        if (!outVerts.isEmpty())
            for (GraphElement vert : outVerts.keySet())
                st += outVerts.get(vert);

        return st;
    }
}

