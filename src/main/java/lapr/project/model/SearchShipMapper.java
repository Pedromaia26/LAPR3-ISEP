package lapr.project.model;

public class SearchShipMapper{

    public SearchShipMapper() {
    }

    public static SearchShipDto toDto(Ship ship) {
        return new SearchShipDto(ship);
    }
}
