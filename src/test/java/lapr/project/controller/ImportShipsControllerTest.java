package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportShipsControllerTest {

    ImportShipsController controller = new ImportShipsController();

    @Test
    void importFromCSV() throws IOException {
        Company c = new Company();
        String file = "sships.csv";
        controller.importFromCSV(file, c);
    }
}