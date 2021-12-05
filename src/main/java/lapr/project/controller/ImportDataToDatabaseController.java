package lapr.project.controller;

import java.io.IOException;

public class ImportDataToDatabaseController {

    public ImportDataToDatabaseController() throws IOException {
        ImportShipsController controller = new ImportShipsController();
        String file = "sships.csv";
        controller.importFromCSV(file);
        controller.insertIntoDatabase();
        ImportPortsController ipc = new ImportPortsController();
        ipc.importFromCSV("sports.csv");
        ipc.insertIntoDatabase();
    }
}
