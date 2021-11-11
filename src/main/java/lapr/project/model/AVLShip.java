package lapr.project.model;

public class AVLShip extends BSTShip<Ship>{

    private int balanceFactor(Node<Ship> node){
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<Ship> rightRotation(Node<Ship> node){
        Node<Ship> leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        return leftson;
    }

    private Node<Ship> leftRotation(Node<Ship> node){
        Node<Ship> rightson = node.getRight();
        node.setRight(rightson.getLeft());
        rightson.setLeft(node);
        return rightson;
    }

    private Node<Ship> twoRotations(Node<Ship> node){
        if(balanceFactor(node) < -1) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        }
        if(balanceFactor(node) > 1) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;

    }

    private Node<Ship> balanceNode(Node<Ship> node) {
        if(balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if(balanceFactor(node) > 1) {
            if(balanceFactor(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        return node;

    }

    @Override
    public void insert(Ship element){
        root = insert(element, root);
    }

    private Node<Ship> insert(Ship element, Node<Ship> node){
        if(node == null)
            return new Node(element, null, null);

        if(element.getMmsi() == node.getElement().getMmsi()) {
            node.setElement(element);
        } else {
            if(node.getElement().getMmsi()>(element.getMmsi())) {
                node.setLeft(insert(element, node.getLeft()));
                node = balanceNode(node);
            } else {
                node.setRight(insert(element, node.getRight()));
                node = balanceNode(node);
            }
        }
        return node;

    }

    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVLShip second = (AVLShip) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<Ship> root1, Node<Ship> root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else
                return false;
        }
        else return false;
    }
}
