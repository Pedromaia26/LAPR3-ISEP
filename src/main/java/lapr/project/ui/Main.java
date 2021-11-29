package lapr.project.ui;

import lapr.project.controller.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        String file = "sships.csv";
        String file2 = "TestFiles/positionalMsg.txt";
        ImportShipsController controller = new ImportShipsController();
        PairController pController = new PairController();
        PositionalMessagesController pmController = new PositionalMessagesController();
        controller.importFromCSV(file);
        pController.pair();
        pmController.message(file2);
        ImportPortsController ipc = new ImportPortsController();
        ipc.importFromCSV("sports.csv");
        NearestPortController cpc = new NearestPortController();
        cpc.getClosestPort("Input/US202.txt");
    }
}

