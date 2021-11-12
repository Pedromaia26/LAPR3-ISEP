package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public HashMap<String, ArrayList<InfoShip>> shipsAndKm(Date date1, Date date2) {
        HashMap<String, ArrayList<InfoShip>> map = new HashMap<>();

        Iterable<Ship> ships = shipBst.inOrder();
        for (Ship a : ships) {
            if (map.get(a.getVesselType()) == null){
                map.put(a.getVesselType(), new ArrayList<>());
            }
            map.get(a.getVesselType()).add(new InfoShip(a, date1, date2));
        }
        return map;
    }

    public void printNshipsMostKm(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        Date dateN = null;
        Date dateM = null;
        int n = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        try {
            line = br.readLine();
            if (line != null) {
                n = Integer.parseInt(line);
                if (n <= 0) {
                    throw new IllegalArgumentException("Parameter invalid");
                }
                line = br.readLine();
                String dates[] = line.split(";");
                dateN = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[0]);
                dateM = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[1]);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        HashMap<String, ArrayList<InfoShip>> map = shipsAndKm(dateN, dateM);
        HashMap<String, ArrayList<InfoShip>> orderMap = sortByValue(map);
        int i =0;
        int j = 0;
        for (String key : orderMap.keySet()) {
            i = 0;
            j = 0;
            data.append("Vessel type: " + key + "\n");
            ArrayList<InfoShip> value = orderMap.get(key);
            while(i<n && j<value.size()) {
                if(!value.get(j).toString().contains("-1")){
                    data.append(value.get(j));
                    i++;
                }
                j++;
            }
        }
        FileOperation.writeToAFile("Output/TopNShips.txt", data);
    }

    public static HashMap<String, ArrayList<InfoShip>> sortByValue(HashMap<String, ArrayList<InfoShip>> hm)
    {
        for (String vessel: hm.keySet()){
            Collections.sort(hm.get(vessel));
        }
        return hm;
    }

}
