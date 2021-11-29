package lapr.project.model;

import lapr.project.controller.App;

public class Port {

    private int code;
    private String name;
    private Country country;
    private float latitude;
    private float longitude;

    public Port(String code, String name, String continent, String country, String latitude, String longitude) {
        this.code = Integer.parseInt(code);
        this.name = name;
        if (!returnCountry(continent, country)) throw new IllegalArgumentException("Invalid Country");
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
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "}\n";
    }

    private boolean returnCountry(String continent, String name){
        CountryStore store = App.getInstance().getCompany().getCountryStore();
        Country country = new Country(continent, name);
        if (store.addCountry(country) == null){
            return false;
        }
        this.country = store.addCountry(country);
        return true;
    }
}
