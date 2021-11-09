package lapr.project.controller;

import org.junit.jupiter.api.Test;
import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TopNshipsPerKmControllerTest {

    Ship ship = new Ship("212180000", "SAITA I","IMO9643544","5BBA4","70","228","32","14.4");
    Ship ship1 = new Ship("228339600","CMA CGM ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
    Ship ship2 = new Ship("212351000","HYUNDAI SINGAPORE","IMO9305685","5BZP3","70","303","40","14.5");

    TopNshipsPerKmControllerTest() throws IOException {
    }

    @Test
    void shipsAndKm() {
    }

    @Test
    void printNshipsMostKm() {
    }

    @Test
    void sortByValue() {
    }
}