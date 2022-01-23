package lapr.project.model;

import java.util.Objects;

public class Country {

    private final String continent;
    private final String name;
    private final String alpha2_code;
    private final String alpha3_code;
    private final float population;
    private final String capital;
    private final float longitude;
    private final float latitude;

    public Country(String continent, String alpha2_code, String alpha3_code, String name, float population, String capital, float latitude, float longitude) {
        this.continent = continent;
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
        this.population = population;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getContinent() {
        return continent;
    }

    public String getName() {
        return name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }

    public float getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Country{" +
                "continent='" + continent + '\'' +
                ", name='" + name + '\'' +
                ", alpha2_code='" + alpha2_code + '\'' +
                ", alpha3_code='" + alpha3_code + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(continent, country.continent) && Objects.equals(name, country.name);
    }
}
