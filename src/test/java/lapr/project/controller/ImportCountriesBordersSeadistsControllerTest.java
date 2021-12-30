package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportCountriesBordersSeadistsControllerTest {

    Company c = App.getInstance().getCompany();
    ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController(c);
    Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
    Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
    Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
    Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
    Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);

    @Test
    void importFromCSV() throws IOException {
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController();
        icontroller.importFromCSVCountry("countries.csv");
        icontroller.importFromCSVBorders("borders.csv");
        icontroller.importFromCSVSeadist("seadists.csv");
    }


    @Test
    void importFromDatabaseCountries() {
        icontroller.importFromDatabaseCountries();
    }

    @Test
    void importFromDatabaseBorders() {
        icontroller.importFromDatabaseBorders();
    }
}