package lapr.project.model;

import java.util.Objects;

public class Country {

    private String continent;
    private String name;

    public Country(String continent, String name) {
        this.continent = continent;
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country:" +
                "continent='" + continent + '\'' +
                ", name='" + name + '\'' +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(continent, country.continent) && Objects.equals(name, country.name);
    }
}
