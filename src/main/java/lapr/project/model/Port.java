package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.CountryStore;

public class Port {

    private int code;
    private String name;
    private Country country;
    private float latitude;
    private float longitude;

    public Port(String code, String name, String continent, String country, String latitude, String longitude) {
        this.code = Integer.parseInt(code);
        this.name = name;
        this.country = returnCountry(country,continent);
        this.latitude = Float.parseFloat(latitude);
        this.longitude = Float.parseFloat(longitude);
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
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
