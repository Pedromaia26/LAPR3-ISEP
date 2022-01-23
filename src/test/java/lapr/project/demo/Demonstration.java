package lapr.project.demo;

import lapr.project.controller.*;
import lapr.project.data.*;
import lapr.project.data.FSIAP.*;
import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

class Demonstration {

    @Test
    void Demonstration() throws IOException, ParseException {
        App.getInstance().setCompany(new Company());
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromCSVCountry("countries.csv");
        ImportShipsController importShipsController = new ImportShipsController();
        importShipsController.importFromCSV("sships.csv");
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("bports.csv");
        SearchShipController searchShipController = new SearchShipController();
        searchShipController.searchDetails("Input/US102.txt");
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        positionalMessagesController.message("Input/US103.txt");
        searchShipController.makeSummary("Input/US104.txt");
        PrintingShipsInfoController printingShipsInfoController = new PrintingShipsInfoController();
        printingShipsInfoController.getShips();
        TopNshipsPerKmController topNshipsPerKmController = new TopNshipsPerKmController();
        topNshipsPerKmController.printNshipsMostKm("Input/US106.txt");
        PairController pairController = new PairController();
        pairController.pair();
        NearestPortController nearestPortController = new NearestPortController();
        nearestPortController.getClosestPort("Input/US202.txt");
        BuildFreightNetworkController bcontroller = new BuildFreightNetworkController();
        icontroller.importFromDatabaseCountries();
        icontroller.importFromDatabaseBorders();
        icontroller.importFromDatabaseSeadists();
        bcontroller.BuildFreightNetwork(3);
        ColourMapController ccontroller = new ColourMapController();
        ccontroller.ColourMap();
        ClosenessPlacesController cpc = new ClosenessPlacesController();
        cpc.closenessPlacesByContinent(10);
        CriticalPortsController criticalPortsController = new CriticalPortsController();
        criticalPortsController.centrality(60);
        ShortestPathController shortestPathController = new ShortestPathController();
        shortestPathController.shortestPath("Athens", "Paris", 3);
        ShortestDistanceGreatestLocationCircuitController sdlccontroller = new ShortestDistanceGreatestLocationCircuitController();
        sdlccontroller.getCircuit("Halifax");
    }

    @Test
    void US204() throws IOException, SQLException {
        US204_SQL sql = new US204_SQL();
        sql.demo(3058855);
    }

    @Test
    void US205() throws IOException, SQLException {
        US205_SQL sql = new US205_SQL();
        sql.demo(212180000);
    }

    @Test
    void US206() throws IOException, SQLException {
        US206_SQL sql = new US206_SQL();
        sql.demo(212180000);
    }

    @Test
    void US207() throws IOException, SQLException {
        US207_SQL sql = new US207_SQL();
        sql.demo(212180000, 2021);
    }

    @Test
    void US208() throws IOException, SQLException {
        US208_SQL sql = new US208_SQL();
        sql.demo(212180000, 3);
    }

    @Test
    void US209() throws IOException, SQLException {
        US209_SQL sql = new US209_SQL();
        sql.demo(212180000, "2022-03-25 00:00:00");
    }

    @Test
    void US210() throws IOException, SQLException {
        US210_SQL sql = new US210_SQL();
        sql.demo();
    }

    @Test
    void US304() throws IOException, SQLException {
        US304_SQL sql = new US304_SQL();
        sql.demo(5984631, 2);
    }

    @Test
    void US305() throws IOException, SQLException {
        US305_SQL sql = new US305_SQL();
        sql.demo("client1", 3058855);
    }

    @Test
    void US306() throws IOException, SQLException {
        US306_SQL sql = new US306_SQL();
        sql.demo("18476");
    }

    @Test
    void US310() throws IOException, SQLException {
        US310_SQL sql = new US310_SQL();
        sql.demo("27248", 2, 2022);
    }

    @Test
    void US309() throws IOException, SQLException {
        US309_SQL sql = new US309_SQL();
        sql.demo(6, "210950000", "63215", 1);
    }
    @Test
    void US312() throws IOException, SQLException {
        US312_SQL sql = new US312_SQL();
        sql.demo(5323205, "client1");
    }

    @Test
    void US313() throws IOException, SQLException {
        CargoManifest_SQL writter = new CargoManifest_SQL();
        writter.demo(3);
    }

    @Test
    void US404() throws IOException, SQLException {
        US404_SQL sql = new US404_SQL();
        sql.demo();
    }
    @Test
    void US405() throws IOException, SQLException {
        US405_SQL sql = new US405_SQL();
        sql.demo("636092932", "2022-01-30 00:00:00", "2022-10-30 00:00:00");
    }
    @Test
    void US406() throws IOException, SQLException {
        US406_SQL sql = new US406_SQL();
        sql.demo("636091400", 0.66F);
    }
    @Test
    void US407() throws IOException, SQLException {
        US407_SQL sql = new US407_SQL();
        sql.demo("25350");
    }
    
    @Test
    void US412() throws IOException{
        ImportContainersController icc =  new ImportContainersController();
        icc.importFromTXT("Input/ContainerInfo");
        US412Controller us412Controller = new US412Controller();
        us412Controller.energyToSupply();
    }

    @Test
    void US413() throws IOException{
        ImportContainersController icc =  new ImportContainersController();
        icc.importFromTXT("Input/ContainerInfo");
        US413Controller us413Controller = new US413Controller(20, "2:30:00");
        us413Controller.energyToSupply();
    }

    @Test
    void US414() throws IOException{
        ImportContainersController icc =  new ImportContainersController();
        icc.importFromTXT("Input/ContainerInfo");
        US414Controller us414Controller = new US414Controller(20, "2:30:00", 2, 1);
        us414Controller.exposedSidesEnergy();
    }

   @Test
    void US415() throws IOException{
        ImportContainersController icc =  new ImportContainersController();
        icc.importFromTXT("Input/ContainerInfo");
       US415Controller us415Controller = new US415Controller(20, "2:30:00", 2, 1);
       us415Controller.auxiliaryPowerEquipment();
    }

    @Test
    void US418() throws IOException {
        VesselCenterOfMassController controller = new VesselCenterOfMassController();
        controller.getCenterOfMass("Container Ship");
    }

    @Test
    void US419() throws IOException {
        VesselContainersPositionController controller = new VesselContainersPositionController();
        controller.getPositionContainers(2000);
    }

    @Test
    void US420() throws IOException {
        VesselSinkingController controller = new VesselSinkingController();
        controller.getVesselSank("Container Ship", 1000);
    }
}
