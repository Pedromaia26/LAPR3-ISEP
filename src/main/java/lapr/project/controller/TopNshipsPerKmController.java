package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TopNshipsPerKmController {

    BSTDynData dataBst = new BSTDynData();
    BSTShip shipBst;

    public TopNshipsPerKmController() {
        shipBst = App.getInstance().getCompany().getBstShips();
    }

    public TopNshipsPerKmController(Company c) {
        shipBst = c.getBstShips();
    }

    public HashMap<Ship, Double> shipsAndKm() {

        double totalKm = 0;
        HashMap<Ship, Double> map = new HashMap<>();

        Iterable<Ship> ships = shipBst.inOrder();
        for (Ship a : ships) {
            dataBst = a.getBstDynData();
            totalKm = dataBst.inorderCalculateDistance();
            map.put(a, totalKm);
            totalKm = 0;
        }
        return map;
    }

    public void printNshipsMostKm(String path) throws IOException {
        String data = "";
        HashMap<Ship, Double> map = shipsAndKm();
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            line = br.readLine();
            if (line != null) {
                int n = Integer.parseInt(line);
                if(n<=0){
                    throw new IllegalArgumentException("Parameter invalid");
                }
                Map<Ship, Double> orderMap = sortByValue(map);
                int i =0;
                for (Map.Entry<Ship, Double> me : orderMap.entrySet()) {
                    while(i<n) {
                        data+="Key = " + me.getKey() + ", Value = " + me.getValue() + "\n";
                        i++;
                    }
                }
                FileOperation.writeToAFile("Output/TopNShips.txt", data);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
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
