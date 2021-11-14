package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportShipsControllerTest {

    ImportShipsController controller = new ImportShipsController();

    @Test
    void importFromCSV() throws IOException {
        String file = "sships.csv";
        controller.importFromCSV(file);
    }

    @Test
    void importFromCSV1() throws IOException {
        String file = "TestFiles/test105_1";
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.importFromCSV(file);
        });
        assertEquals("Invalid mmsi", thrown.getMessage());
    }
}