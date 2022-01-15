package lapr.project.data;

import lapr.project.controller.ImportPortsController;
import lapr.project.controller.ImportShipsController;

import java.io.IOException;

public class ImportDataToDatabase {

    public ImportDataToDatabase() throws IOException {
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        ImportShipsController controller = new ImportShipsController();
        ImportPortsController ipc = new ImportPortsController();
        icontroller.importFromCSVCountry("countries.csv");
        icontroller.importFromCSVBorders("borders.csv");
        icontroller.importFromCSVSeadist("seadists.csv");
        icontroller.insertCountriesIntoDatabase();
        ipc.importFromCSV("bports.csv");
        ipc.insertIntoDatabase();
        icontroller.insertBordersIntoDatabase();
        icontroller.insertSeadistsIntoDatabase();
        String file = "sships.csv";
        controller.importFromCSV(file);
        controller.insertIntoDatabase();
    }
}
