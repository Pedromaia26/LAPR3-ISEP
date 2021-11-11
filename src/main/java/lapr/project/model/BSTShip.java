package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BSTShip<E> {

    /**
     * Nested static class for a binary search tree node.
     */

    protected static class Node<Ship> {
        private Ship element;          // an element stored at this node
        private Node<Ship> left;       // a reference to the left child (if any)
        private Node<Ship> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(Ship e, Node<Ship> leftChild, Node<Ship> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public Ship getElement() {
            return element;
        }

        public Node<Ship> getLeft() {
            return left;
        }

        public Node<Ship> getRight() {
            return right;
        }

        // update methods
        public void setElement(Ship e) {
            element = e;
        }

        public void setLeft(Node<Ship> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<Ship> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------

    protected Node<Ship> root = null;     // root of the tree


    /* Constructs an empty binary search tree. */
    public BSTShip() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<Ship> root() {
        return root;
    }

    public int height(){
        return height(root);
    }

    protected int height(Node<Ship> node){
        if(node == null)
            return -1;

        int lDepth = height(node.getLeft());
        int rDepth = height(node.getRight());

        if(lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

    public Ship smallestElement(){
        return smallestElement(root);
    }

    protected Ship smallestElement(Node<Ship> node) {
        if (node == null)
            return null;

        Node<Ship> auxNode;

        for (auxNode = node; auxNode.getLeft() != null; auxNode = auxNode.getLeft());

        return auxNode.getElement();
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
     * Finds an element in the tree.
     */
    public Ship find(Ship element) {
        if (find(root, element) == null) return null;
        return find(root, element).getElement();
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     * <p>
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<Ship> find(Node<Ship> node, Ship element) {
        if (node == null || node.getElement().getMmsi() == element.getMmsi()) {
            return node;
        }
        if (element.getMmsi() > node.getElement().getMmsi()) {
            return find(node.getRight(), element);
        }
        return find(node.getLeft(), element);
    }

    /*
     * Inserts an element in the tree.
     */
    public abstract void insert(Ship element);

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(BSTShip.Node<Ship> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<Ship> inOrder() {
        List<Ship> snapshot = new ArrayList<>();
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
    private void inOrderSubtree(Node<Ship> node, List<lapr.project.model.Ship> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }
}
//#########################################################################

    /*
    public String toString(){
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb){
        if(root==null)
            return;
        toStringRec(root.getRight(), level+1, sb);
        if (level!=0){
            for(int i=0;i<level-1;i++)
                sb.append("|\t");
            sb.append("|-------"+root.getElement()+"\n");
            sb.append(""+root.getElement().getBstDynData() + "\n");
        }
        else {
            sb.append(root.getElement() + "\n");
            sb.append("" + root.getElement().getBstDynData() + "\n");
        }
        toStringRec(root.getLeft(), level+1, sb);
    }

} //----------- end of BST class -----------
*/







