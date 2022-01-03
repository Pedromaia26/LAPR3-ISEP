package lapr.project.demo;

import lapr.project.controller.*;
import lapr.project.data.*;
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
        App.getInstance().setCompany(new Company());
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController();
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
        TopNshipsPerKmController topNshipsPerKmController = new TopNshipsPerKmController();
        topNshipsPerKmController.printNshipsMostKm("Input/US106.txt");
        PairController pairController = new PairController();
        pairController.pair();
        NearestPortController nearestPortController = new NearestPortController();
        nearestPortController.getClosestPort("Input/US202.txt");
        BuildFreightNetworkController bcontroller = new BuildFreightNetworkController();
        bcontroller.ImportDataFromDatabase();
        bcontroller.BuildFreightNetwork(3);
        ColourMapController ccontroller = new ColourMapController();
        ccontroller.ColourMap();
        ClosenessPlacesController cpc = new ClosenessPlacesController();
        cpc.closenessPlacesByContinent(5);
    }

    @Test
    void US204() throws IOException, SQLException {
        US204_SQL sql = new US204_SQL();
        sql.demo(7222282);
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
        sql.demo(6155496, 5);
    }

    @Test
    void US305() throws IOException, SQLException {
        US305_SQL sql = new US305_SQL();
        sql.demo("client1", 3058855);
    }

    @Test
    void US306() throws IOException, SQLException {
        US306_SQL sql = new US306_SQL();
        sql.demo("27248");
    }

    @Test
    void US310() throws IOException, SQLException {
        US310_SQL sql = new US310_SQL();
        sql.demo("27248", 1, 2022);
    }

    @Test
    void US313() throws IOException, SQLException {
        CargoManifest_SQL writter = new CargoManifest_SQL();
        writter.demo(29);
    }
}
