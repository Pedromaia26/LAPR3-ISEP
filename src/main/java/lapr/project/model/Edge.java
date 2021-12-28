package lapr.project.model;

import java.util.Objects;

/**
 * @param <GraphElement> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class Edge<GraphElement, E> {
    final private GraphElement vOrig;        // vertex origin
    final private GraphElement vDest;        // vertex destination
    private E distance;        // Edge weight


    public Edge(GraphElement vOrig, GraphElement vDest, E distance) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.distance = distance;
    }

    public GraphElement getVOrig() {
        return vOrig;
    }

    public GraphElement getVDest() {
        return vDest;
    }

    public E getDistance() {
        return distance;
    }

    public void setDistance(E distance) {
        this.distance = distance;
    }


    @Override
    public String toString() {
        return String.format("%s -> %s\nDistance: %.2f", vOrig, vDest, distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") Edge<GraphElement, E> edge = (Edge<GraphElement, E>) o;
        return  vOrig.equals(edge.vOrig) &&
                vDest.equals(edge.vDest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vOrig, vDest);
    }
}