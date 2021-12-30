package lapr.project.model;

import java.util.Objects;

/**
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class Edge<V, E> {
    final private V vOrig;        // vertex origin
    final private V vDest;        // vertex destination
    private E distance;        // Edge weight


    public Edge(V vOrig, V vDest, E distance) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.distance = distance;
    }

    public V getVOrig() {
        return vOrig;
    }

    public V getVDest() {
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