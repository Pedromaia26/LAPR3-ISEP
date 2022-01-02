package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosenessPlacesTest {

    @Test
    void getPlace() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        String place = cp.getPlace();

        Assert.assertEquals("Nicosia", place);

    }

    @Test
    void setPlace() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        cp.setPlace("Porto");
        String place = cp.getPlace();

        Assert.assertEquals("Porto", place);
    }

    @Test
    void getDistance() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        double distance = cp.getDistance();

        Assert.assertEquals(2000d, distance, 0.0d);
    }

    @Test
    void setDistance() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        cp.setDistance(3000d);
        double distance = cp.getDistance();

        Assert.assertEquals(3000d, distance,0.0d);
    }

    @Test
    void getCountry() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        String country = cp.getCountry();

        Assert.assertEquals("Cyprus", country);
    }

    @Test
    void setCountry() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp = new ClosenessPlaces(graphElement, 2000d);
        cp.setCountry("England");
        String country = cp.getCountry();

        Assert.assertEquals("England", country);
    }

    @Test
    void compare() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp1 = new ClosenessPlaces(graphElement, 2000d);
        ClosenessPlaces cp2 = new ClosenessPlaces(graphElement, 2000d);
        int compare = cp1.compare(cp1, cp2);

        Assert.assertEquals(0, compare);

    }

    @Test
    void compareC1distanceGreaterThanC2distance() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp1 = new ClosenessPlaces(graphElement, 3000d);
        ClosenessPlaces cp2 = new ClosenessPlaces(graphElement, 2000d);
        int compare = cp1.compare(cp1, cp2);

        Assert.assertEquals(1, compare);

    }

    @Test
    void compareC2distanceGreaterThanC1distance() {
        Country c = new Country("Europe", "CY", "CYP", "Cyprus", 0.85f, "Nicosia", 35.166668f, 33.36667f);
        GraphElement graphElement = new GraphElement(c);
        ClosenessPlaces cp1 = new ClosenessPlaces(graphElement, 2000d);
        ClosenessPlaces cp2 = new ClosenessPlaces(graphElement, 3000d);
        int compare = cp1.compare(cp1, cp2);

        Assert.assertEquals(-1, compare);

    }
}