package lapr.project.model;

public class Border {

    private final Country countryname1;
    private final Country Countryname2;

    public Border(Country countryname1, Country countryname2) {
        this.countryname1 = countryname1;
        Countryname2 = countryname2;
    }

    public Country getCountryname1() {
        return countryname1;
    }

    public Country getCountryname2() {
        return Countryname2;
    }
}
