package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class SearchShipMapper{

    private List<SearchShipDto> listShips;

    public SearchShipMapper(){
        this.listShips= new ArrayList<>();
    }

    public static SearchShipDto toDto(Ship ship) {
        return new SearchShipDto(ship);
    }
}
