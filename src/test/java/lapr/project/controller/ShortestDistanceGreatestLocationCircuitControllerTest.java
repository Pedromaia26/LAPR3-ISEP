package lapr.project.controller;

import lapr.project.model.Country;
import lapr.project.model.Graph;
import lapr.project.model.GraphElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestDistanceGreatestLocationCircuitControllerTest {

    @Test
    void calculateCircuit() {
        Country country1 = new Country("Europe", "Portugal", "PT", "PRT", 10121746, "Lisboa", 20, 40);
        Country country2 = new Country("Europe", "Portugal", "PT", "PRT", 10121746, "Lisboa", 20, 40);
        Country country3 = new Country("Europe", "Portugal", "PT", "PRT", 10121746, "Lisboa", 20, 40);
        Country country4 = new Country("Europe", "Portugal", "PT", "PRT", 10121746, "Lisboa", 20, 40);
        Country country5 = new Country("Europe", "Portugal", "PT", "PRT", 10121746, "Lisboa", 20, 40);
        GraphElement graphElement1 = new GraphElement(country1);
        GraphElement graphElement2 = new GraphElement(country2);
        GraphElement graphElement3 = new GraphElement(country3);
        GraphElement graphElement4 = new GraphElement(country4);
        GraphElement graphElement5 = new GraphElement(country5);
    }
}