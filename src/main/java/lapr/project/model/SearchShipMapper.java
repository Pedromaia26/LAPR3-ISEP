package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class SearchShipMapper{

    public static SearchShipDto toDto(Ship ship) {
        return new SearchShipDto(ship);
    }
}
