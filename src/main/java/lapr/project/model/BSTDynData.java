package lapr.project.model;

import oracle.jdbc.driver.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BSTDynData<E extends Comparable<E>> {

    List<ShipDynData> list = new ArrayList<>();


    /**
     * Nested static class for a binary search tree node.
     */

    protected static class Node<E> {
        private ShipDynData element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(ShipDynData e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public ShipDynData getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(ShipDynData e) {
            element = e;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------

    protected Node<E> root = null;     // root of the tree


    /* Constructs an empty binary search tree. */
    public BSTDynData() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
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
    public ShipDynData find(ShipDynData element) {
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
    protected Node<E> find(Node<E> node, ShipDynData element) {

        if (node == null || node.getElement().getBaseDateTime().compareTo(element.getBaseDateTime()) == 0)
            return node;
        if (element.getBaseDateTime().compareTo(node.getElement().getBaseDateTime()) > 0) {
            return find(node.getRight(), element);
        }
        else {
            return find(node.getLeft(), element);
        }
    }

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /*
     * Inserts an element in the tree.
     */
    public void insert(ShipDynData element) {
        root = insert(element, root);
    }

    private Node<E> insert(ShipDynData element, Node<E> node) {
        if (node == null) {
            node = new Node<>(element, null, null);
            return node;
        }

        if (element.getBaseDateTime().before(node.getElement().getBaseDateTime()))
            node.left = insert(element, node.getLeft());
        else if (element.getBaseDateTime().after(node.getElement().getBaseDateTime()))
            node.right = insert(element, node.getRight());

        /* return the (unchanged) node pointer */
        return node;
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<ShipDynData> inOrder() {
        List<ShipDynData> snapshot = new ArrayList<>();
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
    private void inOrderSubtree(Node<E> node, List<ShipDynData> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    public ShipDynData departure(){

        ShipDynData dep = null;

        if (root!=null) {
            dep = checkDeparture(root);
            // System.out.print("Departure: ");
            // System.out.println(dep.getBaseDateTime());
        }

        return dep;
    }


    public ShipDynData checkDeparture(Node<E> node){

        if (node.getLeft()==null)
            return node.getElement();

        return checkDeparture(node.getLeft());

    }

    public ShipDynData arrival(){

        ShipDynData dep = null;

        if (root!=null) {
            dep = checkArrival(root);
            //  System.out.print("Arrival: ");
            //  System.out.println(dep.getBaseDateTime());
        }

        return dep;
    }

    public ShipDynData checkArrival(Node<E> node) {

        if (node.getRight() == null)
            return node.getElement();

        return checkArrival(node.getRight());
    }


    public double travelledDistance (double lat1, double lng1, double lat2, double lng2){

        double dist = distFrom(lat1, lng1, lat2, lng2);

        return dist;

    }

    public double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }


    public double inorderCalculateDistance() {
        Iterable<ShipDynData> messages = inOrder();

        Iterator<ShipDynData> iterator = messages.iterator();

        ShipDynData aux = iterator.next();

        double totalDistance = 0;
        while (iterator.hasNext()){
            ShipDynData naux= iterator.next();
            if(aux.getLatitude().equals("NA") || aux.getLongitude().equals("NA") || naux.getLatitude().equals("NA") || naux.getLongitude().equals("NA")){
                totalDistance=totalDistance+0;
            }
            else {
                totalDistance = totalDistance + travelledDistance(Float.parseFloat(aux.getLatitude()), Float.parseFloat(aux.getLongitude()), Float.parseFloat(naux.getLatitude()), Float.parseFloat(naux.getLongitude()));
            }
            aux=naux;
        }
        return totalDistance;
    }


    public ShipDynData searchSpecificDate(Date date){

        ShipDynData sdd;
        sdd = searchSpecificDatePeriod(root, date);
        return sdd;

    }

    public ShipDynData searchSpecificDatePeriod(Node<E> node, Date date) {

        if (node==null){
            return null;
        }

        if(date.compareTo(node.getElement().getBaseDateTime())<0){
            searchSpecificDatePeriod(node.getLeft(), date);
        }

        if(date.compareTo(node.getElement().getBaseDateTime())==0) {
            return node.getElement();
        }

        return searchSpecificDatePeriod(node.getRight(),date);

    }

    public List<ShipDynData> searchSpecificDatePeriodcall(Date date1, Date date2){

        searchSpecificDatePeriod(root, date1, date2);
        return list;
    }

    public void searchSpecificDatePeriod(Node<E> node, Date date1, Date date2) {

        if(node == null){
            return;
        }
        if(date1.before(node.getElement().getBaseDateTime())){
            searchSpecificDatePeriod(node.getLeft(),date1,date2);
        }

        if(date1.compareTo(node.getElement().getBaseDateTime())<=0 && date2.compareTo(node.getElement().getBaseDateTime())>=0){
            list.add(node.getElement());
        }

        searchSpecificDatePeriod(node.getRight(),date1,date2);

    }
    
//#########################################################################





    public String toString(){
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb){
        if(root==null)
            return;
        toStringRec(root.getLeft(), level+1, sb);
        if (level!=0){
            sb.append(root.getElement()+"\n");
        }
        else
            sb.append(root.getElement()+"\n");

        toStringRec(root.getRight(), level+1, sb);
    }

}







