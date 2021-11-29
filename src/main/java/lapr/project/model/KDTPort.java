package lapr.project.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KDTPort<E> extends KDTree{
    public static class Node<Port> {
        private Port element;
        private KDTPort.Node<Port> left;
        private KDTPort.Node<Port> right;
        private Point2D.Double cords;
        public Node(Port value, double latitude, double longitude) {
            this.element = value;
            this.cords = new Point2D.Double(latitude,longitude);
            right = null;
            left = null;
        }

        public Port getPort() { return element; }
        public Point2D.Double getCords() { return cords; }
        public Double getX() { return cords.x; }
        public Double getY() { return cords.y; }
        public Node<Port> getLeft() { return left; }
        public Node<Port> getRight() { return right; }

        // update methods
        public void setElement(Port port) { this.element = port; }
        public void setLeft(Node<Port> leftChild) { left = leftChild; }
        public void setRight(Node<Port> rightChild) { right = rightChild; }
        public void setCords(Point2D cords) {
            this.cords.x=cords.getX();
            this.cords.y=cords.getY();
        }
    }

    private final Comparator<Node<Port>> compareX = new Comparator<Node<Port>>() {
        @Override
        public int compare(Node<Port> p1, Node<Port> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };


    private final Comparator<Node<Port>> compareY = new Comparator<Node<Port>>() {
        @Override
        public int compare(Node<Port> p1, Node<Port> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    protected Node<Port> root; // root of the tree

    /**
     * Constructs an empty 2D tree
     */
    public KDTPort(){
        root=null;
    }

    /**
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Port root() {
        return root.getPort();
    }

    /**
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return root==null;
    }

    public void insert(Port ports, double latitude, double longitude) {
        Node<Port> node = new Node<>(ports, latitude, longitude);
        if (root == null)
            root = node;
        else
            insert(node, root, true);
    }

    private void insert(Node<Port> node, Node<Port> currentNode, boolean divX) {
        if (node == null)
            return;
        if (node.getCords().equals(currentNode.getCords()))
            return;
        int compareResult = (divX ? compareX : compareY).compare(node, currentNode);
        if (compareResult == -1)
            if(currentNode.getLeft() == null) {
                currentNode.setLeft(node);
                currentNode.getLeft().setCords(node.getCords());
            }
            else
                insert(node, currentNode.getLeft(), !divX);
        else
        if(currentNode.getRight() == null) {
            currentNode.setRight(node);
            currentNode.getRight().setCords(node.getCords());
        }
        else
            insert(node, currentNode.getRight(), !divX);
    }

    public Port findNearestNeighbour(double latitude, double longitude) {
        return findNearestNeighbour(root, latitude, longitude,root, true);
    }

    private Port findNearestNeighbour(Node<Port> node, double x, double y,Node<Port> closestNode ,boolean divX) {

        if (node == null)
            return null;

        double d = Point2D.distanceSq(node.getCords().x, node.getCords().y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.getCords().x, closestNode.getCords().y, x, y);

        if (closestDist > d) {
            closestNode.setElement(node.getPort());
            closestNode.setCords(node.getCords());
        }

        double delta = divX ? x - node.getCords().x : y - node.getCords().y;
        double delta2 = delta * delta;

        Node<Port> node1 = delta < 0 ? node.getLeft() : node.getRight();
        Node<Port> node2 = delta < 0 ? node.getRight() : node.getLeft();

        findNearestNeighbour(node1,x,y,closestNode, !divX);

        if (delta2 < closestDist) {
            findNearestNeighbour(node2, x,y,closestNode,!divX);
        }

        return closestNode.getPort();
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<Port> inOrder() {
        List<Port> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(KDTPort.Node<Port> node, List<lapr.project.model.Port> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getPort());
        inOrderSubtree(node.getRight(), snapshot);
    }

}
