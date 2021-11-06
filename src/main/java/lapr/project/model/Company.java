package lapr.project.model;

public class Company {
    /**
     * BST that contains the ships
     */
    private BSTShip bstShips;

    public Company()
    {
        this.bstShips = new BSTShip();
    }

    /**
     * Returns the authFacade.
     * @return the authFacade.
     */
    public BSTShip getBstShips() {
        return bstShips;
    }

    public void setBstShips(BSTShip shipBST) {
        bstShips = shipBST;
    }
}
