package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.CountryStore;

import java.util.*;

public class ClosenessPlaces implements Comparator<ClosenessPlaces> {

    private String place, country;
    private double distance;

    public ClosenessPlaces(GraphElement element, Double shortestPath){
        this.country = element.getCountry();
        this.place = element.getDesignation();
        this.distance = shortestPath;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int compare(ClosenessPlaces cp1, ClosenessPlaces cp2) {
        if (cp1.getDistance() > cp2.getDistance())
            return 1;
        else if (cp1.getDistance() < cp2.getDistance()){
            return -1;
        }else
            return 0;
    }
}
