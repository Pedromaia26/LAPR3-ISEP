package lapr.project.model;

import java.util.Objects;

public class GraphElement {

    private String designation;
    private float latitude;
    private float longitude;
    private String country;

    public GraphElement(Country country) {
        this.designation = country.getCapital();
        this.latitude = country.getLatitude();
        this.longitude = country.getLongitude();
        this.country = country.getName();
    }

    public GraphElement(Port port) {
        this.designation = port.getName();
        this.latitude = port.getLatitude();
        this.longitude = port.getLongitude();
        this.country = port.getCountry().getName();
    }

    public String getDesignation() {
        return designation;
        
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphElement that = (GraphElement) o;
        return Objects.equals(designation, that.designation) && Objects.equals(country, that.country);
    }

    @Override
    public String toString() {
        return "GraphElement{" +
                "designation='" + designation + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
