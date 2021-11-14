package lapr.project.ui;

import lapr.project.controller.ImportShipsController;
import lapr.project.controller.PairController;
import lapr.project.controller.PositionalMessagesController;
import lapr.project.model.Company;
import lapr.project.model.Ship;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
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






        /*if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, String.valueOf(value));
        }*/
    }
}

