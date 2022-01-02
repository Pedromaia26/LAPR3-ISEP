package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.ClosenessPlaces;
import lapr.project.model.Country;

import java.util.*;

public class ClosenessPlacesStore {

    private Map<String, List<ClosenessPlaces>> map = new HashMap<>();
    private List<ClosenessPlaces> list;
    private String continent;

    public void addClosenessPlaces(ClosenessPlaces cp){
        list = new ArrayList<>();
        continent = getCs().getContinentByCountry(cp.getCountry());

        if (map.get(continent)!=null) {
            list = map.get(continent);
        }
        list.add(cp);
        Collections.sort(list, cp);
        map.put(continent, list);
    }

    public Map<String, List<ClosenessPlaces>> getMap() {
        return map;
    }

    public CountryStore getCs() {
        return App.getInstance().getCompany().getCountryStore();
    }



}
