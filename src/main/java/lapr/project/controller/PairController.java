package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;

import java.util.*;

public class PairController {

    public void returnPair (){

        List<Ship> shipList = (List<Ship>) App.getInstance().getCompany().getBstShips().inOrder();
        Map<Ship, List<Ship>> pair = new LinkedHashMap<>();
        double departureDistance, arrivalDistance, latD1, lngD1, latA1, lngA1, latD2, lngD2, latA2, lngA2, dist1, dist2, dist3;

        for (int i = 0; i < shipList.size()-1; i++) {

            boolean exists = false;
            List<Ship> teste = new ArrayList<>();
            List<Double> distances = new ArrayList<>();

            for (int j = i+1; j < shipList.size() ; j++) {

                System.out.println("------------");
                System.out.println(shipList.get(i).getMmsi()  + "/" + shipList.get(j).getMmsi());



                latD1 = Double.parseDouble(shipList.get(i).getBstDynData().departure().getLatitude());
                lngD1 = Double.parseDouble(shipList.get(i).getBstDynData().departure().getLongitude());
                latA1 = Double.parseDouble(shipList.get(i).getBstDynData().arrival().getLatitude());
                lngA1 = Double.parseDouble(shipList.get(i).getBstDynData().arrival().getLongitude());

                latD2 = Double.parseDouble(shipList.get(j).getBstDynData().departure().getLatitude());
                lngD2 = Double.parseDouble(shipList.get(j).getBstDynData().departure().getLongitude());
                latA2 = Double.parseDouble(shipList.get(j).getBstDynData().arrival().getLatitude());
                lngA2 = Double.parseDouble(shipList.get(j).getBstDynData().arrival().getLongitude());


                dist1 = shipList.get(i).getBstDynData().inorderCalculateDistance();
                System.out.printf("Travelled distance between departure and arrival (Ship1): %.2f m\n", dist1);

                if (dist1 < 10000){
                    System.out.println("Ship1 travelled less than 10 km.");
                    break;
                }

                dist2 = shipList.get(j).getBstDynData().inorderCalculateDistance();
                System.out.printf("Travelled distance between departure and arrival (Ship2): %.2f m\n", dist2);

                if (dist2 < 10000){
                    System.out.println("Ship2 travelled less than 10 km.");
                    continue;
                }


                dist3 = Math.abs(dist2-dist1);


                departureDistance = shipList.get(i).getBstDynData().travelledDistance(latD1, lngD1, latD2, lngD2);
                arrivalDistance = shipList.get(i).getBstDynData().travelledDistance(latA1, lngA1, latA2, lngA2);

                System.out.printf("Distance between ships departure: %.2f m\n", Math.abs(departureDistance));
                System.out.printf("Distance between ships arrival: %.2f m\n", Math.abs(arrivalDistance));

                if (dist1!=dist2 && (departureDistance < 5000 || arrivalDistance < 5000)) {

                    distances.add(dist3);
                    teste.add(shipList.get(j));

                    System.out.println("Close departure or arrival: " + shipList.get(i) + " ----- " + shipList.get(j));

                    exists = true;

                }else
                    System.out.println("Distant coordinates between departures/arrivals");

            }

            if (exists == true){
                for(int l=0; l < distances.size(); l++){
                    for(int j=1; j < distances.size()-l; j++){
                        if(distances.get(j-1) < distances.get(j)){
                            Collections.swap(distances, j-1, j);
                            Collections.swap(teste, j-1, j);
                        }
                    }
                }
                pair.put(shipList.get(i), teste);
            }


            if (i==shipList.size()-2 && teste!=null){
                System.out.println("\nPairs of ships with routes with close departure/arrival coordinates:\n");


                for(Ship key : pair.keySet()) {
                    for (Ship value : pair.get(key)) {
                        System.out.println(key + " / " + value);
                    }
                }
            }
        }
    }
}
