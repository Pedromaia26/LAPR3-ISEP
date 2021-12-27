package lapr.project.demo;

import lapr.project.controller.*;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class Demonstration {

    @Test
    void Demonstration() throws IOException, ParseException {
        Company c = new Company();
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController(c);
        icontroller.importFromCSVCountry("countries.csv");
        ImportShipsController importShipsController = new ImportShipsController(c);
        importShipsController.importFromCSV("sships.csv");
        ImportPortsController importPortsController = new ImportPortsController(c);
        importPortsController.importFromCSV("sports.csv");
        SearchShipController searchShipController = new SearchShipController(c);
        searchShipController.searchDetails("Input/US102.txt");
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController(c);
        positionalMessagesController.message("Input/US103.txt");
        searchShipController.makeSummary("Input/US104.txt");
        PrintingShipsInfoController printingShipsInfoController = new PrintingShipsInfoController(c);
        printingShipsInfoController.getShips();
        TopNshipsPerKmController topNshipsPerKmController = new TopNshipsPerKmController(c);
        topNshipsPerKmController.printNshipsMostKm("Input/US106.txt");
        PairController pairController = new PairController(c);
        pairController.pair();
        NearestPortController nearestPortController = new NearestPortController(c);
        nearestPortController.getClosestPort("Input/US202.txt");
        icontroller.BuildFreightNetwork(5);
    }

    @Test
    void US204() throws IOException, SQLException {
        CurrentSpecificationGivenContainerController controller = new CurrentSpecificationGivenContainerController();
    }

    @Test
    void US205() throws IOException, SQLException {
        ListOfContainersOffloadedController controller = new ListOfContainersOffloadedController();
    }

    @Test
    void US206() throws IOException, SQLException {
        ListOfContainersLoadedController controller = new ListOfContainersLoadedController();
    }

    @Test
    void US207() throws IOException, SQLException {
        NumberCargoManifestAvgContainerController controller = new NumberCargoManifestAvgContainerController();
    }

    @Test
    void US208() throws IOException, SQLException {
        OccupanceRateGivenShipCargoController controller = new OccupanceRateGivenShipCargoController();
    }

    @Test
    void US209() throws IOException, SQLException {
        OccupanceRateGivenShipDateController controller = new OccupanceRateGivenShipDateController();
    }

    @Test
    void US210() throws IOException, SQLException {
        ShipsAvailableNextMondayController controller = new ShipsAvailableNextMondayController();
    }

    @Test
    void US306() throws IOException, SQLException {
        OccupancyOfEachWarehouseController controller = new OccupancyOfEachWarehouseController();
    }
}
