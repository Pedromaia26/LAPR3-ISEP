package lapr.project.controller;

import lapr.project.data.CountryStore;
import lapr.project.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClosenessPlacesControllerTest {

    @Test
    void closenessPlacesByContinent() throws IOException {
        Company c = new Company();
        ClosenessPlacesController cpc = new ClosenessPlacesController();
        App.getInstance().setCompany(c);
        Country country1 = new Country("Europe", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europe", "ES", "ESP", "Spain", 0.5f, "Madrid", 30, 20);
        c.getCountryStore().addCountry(country1);
        c.getCountryStore().addCountry(country2);
        System.out.println(App.getInstance().getCompany().getCountryStore().getCountry("Portugal"));
        String actual = "";
        Port p1 = new Port("13131", "Leixões", "Europe", "Portugal", "41.182648", "-8.702905f");
        Port p2 = new Port("11411", "Vigo", "Europe", "Spain", "41.15", "-8.61024");;
        GraphElement ge1 = new GraphElement(p1);
        GraphElement ge2 = new GraphElement(p2);
        ClosenessPlaces cp1 = new ClosenessPlaces(ge1, 1300d);
        ClosenessPlaces cp2 = new ClosenessPlaces(ge2, 1500d);
        List<ClosenessPlaces> cpList = new ArrayList<>();
        cpList.add(cp1);
        cpList.add(cp2);
        Map<String, List<ClosenessPlaces>> map = new HashMap<>();
        map.put((p1.getCountry().getContinent()), cpList);
        map.put((p2.getCountry().getContinent()), cpList);
        cpc.closenessPlacesByContinent(1);
        for (String continent : map.keySet()) {
            for (ClosenessPlaces cp : map.get(continent)) {
                actual += String.format("%s: %s - %.2f km\n", continent, cp.getPlace(), cp.getDistance());
            }
        }
        String expected = "Europe: Leixões - 1300,00 km\nEurope: Vigo - 1500,00 km\n";
        Assert.assertEquals(expected, actual);
    }
}