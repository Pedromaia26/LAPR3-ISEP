package lapr.project.model;

import java.util.Comparator;

public class KDTree<T> {
    private final Comparator<KDTPort.Node<T>> cmpX = new Comparator<KDTPort.Node<T>>() {
        @Override
        public int compare(KDTPort.Node<T> p1, KDTPort.Node<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };
    private final Comparator<KDTPort.Node<T>> cmpY = new Comparator<KDTPort.Node<T>>() {
        @Override
        public int compare(KDTPort.Node<T> p1, KDTPort.Node<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };
    private KDTPort.Node<T> root;
}
