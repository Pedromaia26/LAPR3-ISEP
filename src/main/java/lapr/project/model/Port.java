package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.CountryStore;

public class Port {

    private final int code;
    private final String name;
    private Country country;
    private final float latitude;
    private final float longitude;
    private final int docking_capacity;
    private final int docking_occupancy;

    public Port(String code, String name, String continent, String country, String latitude, String longitude) {
        this.code = Integer.parseInt(code);
        this.name = name;
        this.country = returnCountry(country,continent);
        this.latitude = Float.parseFloat(latitude);
        this.longitude = Float.parseFloat(longitude);
        this.docking_capacity = 10;
        this.docking_occupancy = 5;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country){
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getDocking_capacity(){
        return docking_capacity;
    }

    public int getDocking_occupancy(){
        return docking_occupancy;
    }

    @Override
    public String toString() {
        return "Port{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", country='" + country.getName() + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "}\n";
    }

    private Country returnCountry(String name, String continent){
        CountryStore store = App.getInstance().getCompany().getCountryStore();
        return store.getCountry(continent, name);
    }
}
