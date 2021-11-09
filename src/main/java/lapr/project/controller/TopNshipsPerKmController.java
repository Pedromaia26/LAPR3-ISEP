package lapr.project.controller;

import lapr.project.model.BSTDynData;
import lapr.project.model.BSTShip;
import lapr.project.model.Ship;

import java.util.*;

public class TopNshipsPerKmController {

    BSTDynData dataBst = new BSTDynData();
    BSTShip shipBst = new BSTShip();

    public Map<Ship, Double> shipsAndKm() {

        double totalKm = 0;
        Map<Ship, Double> map = new HashMap<>();

        Iterable<Ship> ships = shipBst.inOrder();
        Iterable<BSTDynData> viagens = dataBst.inOrder();
        for (Ship a : ships) {
            dataBst = a.getBstDynData();
            for (BSTDynData v : viagens) {
                totalKm = v.inorderCalculateDistance();
            }
            map.put(a, totalKm);
            totalKm = 0;
        }
        return map;
    }

    public void printNshipsMostKm(int n, HashMap<Ship, Double> map){
        Map<Ship, Double> orderMap = sortByValue(map);
        int i =0;
            for (Map.Entry<Ship, Double> me : orderMap.entrySet()) {
                while(i<n) {
                System.out.println("Key = " + me.getKey() + ", Value = " + me.getValue());
                i++;
                }
            }
    }

    public static HashMap<Ship, Double> sortByValue(HashMap<Ship, Double> hm)
    {
        // creating list from elements of HashMap
        List<Map.Entry<Ship, Double>> list = new LinkedList<Map.Entry<Ship, Double>>(hm.entrySet());
        // sorting list
        Collections.sort(list, new Comparator<Map.Entry<Ship, Double>>()
        {
            public int compare(Map.Entry<Ship, Double> o1, Map.Entry<Ship, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<Ship, Double> ha = new LinkedHashMap<Ship, Double>();
        for(Map.Entry<Ship, Double> me : list)
        {
            ha.put(me.getKey(), me.getValue());
        }
        return ha;
    }

}
