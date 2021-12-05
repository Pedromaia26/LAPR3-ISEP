package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportPortsControllerTest {

    @Test
    void importFromCSV() throws IOException {
        Company c = new Company();
        ImportPortsController controller = new ImportPortsController(c);
        controller.importFromCSV("sports.csv");
    }
}