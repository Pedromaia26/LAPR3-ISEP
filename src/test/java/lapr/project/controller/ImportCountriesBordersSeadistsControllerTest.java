package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportCountriesBordersSeadistsControllerTest {

    @Test
    void importFromCSV() throws IOException {
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController();
        icontroller.importFromCSVCountry("countries.csv");
        icontroller.importFromCSVBorders("borders.csv");
        icontroller.importFromCSVSeadist("seadists.csv");
    }

}