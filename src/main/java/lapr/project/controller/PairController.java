package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PairController {

    private double departureDistance, arrivalDistance, latD1, lngD1, latA1, lngA1, latD2, lngD2, latA2, lngA2, dist1, dist2, dist3;
    private Map<Ship, List<Ship>> pair = new LinkedHashMap<>();
    private boolean exists;
    private List<Ship> teste;
    private List<Double> distances = new ArrayList<>();
    private StringBuilder data = new StringBuilder();
    int cont = -1;

    public void pair() throws IOException {
        List<Ship> shipList = (List<Ship>) App.getInstance().getCompany().getBstShips().inOrder();
        for (int i = 0; i < shipList.size() - 1; i++) {
            teste = new ArrayList<>();
            distances = new ArrayList<>();
            cont++;
            if (cont==shipList.size()-2){
                print();
            }
            for (int j = i + 1; j < shipList.size(); j++) {
                latD1 = Double.parseDouble(shipList.get(i).getBstDynData().departure().getLatitude());
                lngD1 = Double.parseDouble(shipList.get(i).getBstDynData().departure().getLongitude());
                latA1 = Double.parseDouble(shipList.get(i).getBstDynData().arrival().getLatitude());
                lngA1 = Double.parseDouble(shipList.get(i).getBstDynData().arrival().getLongitude());

                latD2 = Double.parseDouble(shipList.get(j).getBstDynData().departure().getLatitude());
                lngD2 = Double.parseDouble(shipList.get(j).getBstDynData().departure().getLongitude());
                latA2 = Double.parseDouble(shipList.get(j).getBstDynData().arrival().getLatitude());
                lngA2 = Double.parseDouble(shipList.get(j).getBstDynData().arrival().getLongitude());

                Date date1S1 = shipList.get(i).getBstDynData().departure().getBaseDateTime();
                Date date2S1 = shipList.get(i).getBstDynData().arrival().getBaseDateTime();
                Date date1S2 = shipList.get(j).getBstDynData().departure().getBaseDateTime();
                Date date2S2 = shipList.get(j).getBstDynData().arrival().getBaseDateTime();

                dist1 = shipList.get(i).getBstDynData().inorderCalculateDistance(date1S1, date2S1);
                dist2 = shipList.get(j).getBstDynData().inorderCalculateDistance(date1S2, date2S2);


                int less = checkTravelledDistance(dist1, dist2);

                if (less == 1) {
                    break;
                } else if (less == 2) {
                    continue;
                }

                dist3 = Math.abs(dist2-dist1);

                Ship ship1 = shipList.get(i);
                Ship ship2 = shipList.get(j);


                exists = checkRequirements(ship1, ship2, dist3, latD1, lngD1, latD2, lngD2, latA1, lngA1, latA2, lngA2);

                if (exists)
                    getPairs(ship1);
            }
        }
    }

    public int checkTravelledDistance(double dist1, double dist2) {
        if (dist1 < 10000) {
            return 1;
        }else if (dist2 < 10000) {
            return 2;
        }
        return 0;
    }


    public boolean checkRequirements(Ship ship1, Ship ship2, double dist3, double latD1, double lngD1, double latD2, double lngD2, double latA1, double lngA1, double latA2, double lngA2){
        departureDistance = ship1.getBstDynData().travelledDistance(latD1, lngD1, latD2, lngD2);
        arrivalDistance = ship2.getBstDynData().travelledDistance(latA1, lngA1, latA2, lngA2);
        boolean f = false;

        if (dist1!=dist2 && (departureDistance < 5000000 || arrivalDistance < 5000000)) {

            distances.add(dist3);
            teste.add(ship2);

            // System.out.println("Close departure or arrival: " + shipList.get(i) + " ----- " + shipList.get(j));

            f = true;
        }
        // System.out.println("Distant coordinates between departures/arrivals");

        return f;
    }


    public void getPairs(Ship ship1) throws IOException {

        for (int l = 0; l < distances.size(); l++) {
            for (int j = 1; j < distances.size() - l; j++) {
                if (distances.get(j - 1) < distances.get(j)) {
                    Collections.swap(distances, j - 1, j);
                    Collections.swap(teste, j - 1, j);
                }
            }
        }
        pair.put(ship1, teste);
    }

    public void print() throws IOException {

        data.append("Pairs of ships with routes with close departure/arrival coordinates:\n\n");

        for(Ship key : pair.keySet()) {
            for (Ship value : pair.get(key)) {
                data.append(key + " / " + value + "\n");
            }
        }
        FileOperation.writeToAFile("Output/closeShips.txt", data);
    }
}