package lapr.project.demo;

import lapr.project.controller.*;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class Demonstration {

    @Test
    void Demonstration() throws IOException, ParseException {
        ImportShipsController importShipsController = new ImportShipsController();
        importShipsController.importFromCSV("sships.csv");
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("sports.csv");
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
    }
}
