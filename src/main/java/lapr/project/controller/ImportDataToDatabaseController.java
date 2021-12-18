package lapr.project.controller;

import java.io.IOException;

public class ImportDataToDatabaseController {

    public ImportDataToDatabaseController() throws IOException {
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController();
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
