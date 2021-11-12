package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PairControllerTest {

    PairController pcontroller = new PairController();
    ImportShipsController icontroller = new ImportShipsController();

    @Test
    void returnPair() throws IOException {
        String file = "sships.csv";
        icontroller.importFromCSV(file);
        pcontroller.pair();
    }


}