package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class CountryStore {

    private List<Country> Countries = new ArrayList<>();

    public boolean validateCountry(Country country){
        if (country == null)
            return false;
        return getCountry(country);
    }

    public Country addCountry(Country country){
        if (!validateCountry(country)){
            return null;
        }
        else if (!this.Countries.contains(country))
            this.Countries.add(country);
        return country;
    }

    public boolean getCountry(Country country){
        for (Country c : Countries){
            if (country.equals(c)){
                return true;
            }
            else if (!country.getContinent().equals(c.getContinent()) && country.getName().equals(c.getName())){
                return false;
            }
        }
        return true;
    }

    public List<Country> getCountries() {
        return Countries;
    }
}
