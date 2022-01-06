package lapr.project.model;

import lapr.project.data.BorderStore;
import lapr.project.data.ClosenessPlacesStore;
import lapr.project.data.CountryStore;
import lapr.project.data.SeadistStore;

public class Company {
    /**
     * BST that contains the ships
     */
    private BSTShip bstShips;

    private KDTPort kdtPorts;

    private MatrixGraph freightNetwork;

    private CountryStore countryStore;

    private BorderStore borderStore;

    private SeadistStore seadistStore;

    private ClosenessPlacesStore closenessPlacesStore;

    public Company()
    {
        this.bstShips = new AVLShip();
        this.kdtPorts = new KDTPort();
        this.countryStore = new CountryStore();
        this.borderStore = new BorderStore();
        this.seadistStore = new SeadistStore();
        this.freightNetwork = new MatrixGraph(false);
        this.closenessPlacesStore = new ClosenessPlacesStore();
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

    public BorderStore getBorderStore() {
        return borderStore;
    }

    public void setBorderStore(BorderStore borderStore) {
        this.borderStore = borderStore;
    }

    public SeadistStore getSeadistStore() {
        return seadistStore;
    }

    public void setSeadistStore(SeadistStore seadistStore) {
        this.seadistStore = seadistStore;
    }

    public MatrixGraph getMatrixGraph() {
        return freightNetwork;
    }

    public void setMatrixGraph(MatrixGraph matrixGraph) {
        this.freightNetwork = matrixGraph;
    }

    public ClosenessPlacesStore getClosenessPlaceStore() {
        return closenessPlacesStore;
    }
}
