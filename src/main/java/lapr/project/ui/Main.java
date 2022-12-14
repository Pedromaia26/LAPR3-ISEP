package lapr.project.ui;

import lapr.project.data.ImportDataToDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

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

        /*try
        {
            Login login = new Login();

            login.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }*/
        ImportDataToDatabase controller = new ImportDataToDatabase();
    }
}

