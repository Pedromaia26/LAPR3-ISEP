package lapr.project.model;

public class Company {
    /**
     * BST that contains the ships
     */
    private BSTShip bstShips;

    private KDTPort kdtPorts;

    private CountryStore countryStore;

    public Company()
    {
        this.bstShips = new AVLShip();
        this.kdtPorts = new KDTPort();
        this.countryStore = new CountryStore();
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

    public KDTPort getKdtPorts() {
        return kdtPorts;
    }

    public void setKdtPorts(KDTPort kdtPorts) {
        this.kdtPorts = kdtPorts;
    }

    public CountryStore getCountryStore() {
        return countryStore;
    }

    public void setCountryStore(CountryStore countryStore) {
        this.countryStore = countryStore;
    }
}
