package lapr.project.controller;

import lapr.project.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchShipController {

    BSTShip shipBST = new BSTShip();
    private Ship ship;

    public SearchShipController(BSTShip shipBST) {
        this.shipBST = shipBST;
    }

    public Ship ShipSearchByMmsi(int mmsi){
        Iterable<Ship> ships = shipBST.inOrder();
        for(Ship a : ships ){
            if(a.getMmsi()==mmsi){
                this.ship = a;
                return a;
            }
        }

        return (null);
    }

    public Ship ShipSearchByImo(String imo){
        Iterable<Ship> ships = shipBST.inOrder();
        for(Ship a : ships ){
            if(a.getImo().equals(imo)){
                this.ship = a;
                return a;
            }
        }

        return (null);
    }

    public Ship ShipSearchByCallSign(String callsign){
        Iterable<Ship> ships = shipBST.inOrder();
        for(Ship a : ships ){
            if(a.getCallSign().equals(callsign)){
                this.ship = a;
                return a;
            }
        }

        return (null);
    }

    /*public String getSearchShipData(){
        return SearchShipMapper.toDto(this.ship).toString();
    }*/

    public void searchDeatils(Ship ship) throws IOException {
        FileWriter myWriter = new FileWriter(String.valueOf(ship.getMmsi()) + "details.txt");
        try {
            myWriter.write("Details of the ship " + String.valueOf(ship.getMmsi()) + ":\n");
            myWriter.write("Vessel Name: " + ship.getVesselType() + "\n");
            myWriter.write("Start Base Date Time: " + ship.getBstDynData().departure().getBaseDateTime() + "\n");
            myWriter.write("End Base Date Time: " + ship.getBstDynData().arrival().getBaseDateTime() + "\n");
            long diff = ship.getBstDynData().arrival().getBaseDateTime().getTime() - ship.getBstDynData().departure().getBaseDateTime().getTime();
            TimeUnit time = TimeUnit.HOURS;
            long difference = time.convert(diff, TimeUnit.MILLISECONDS);
            myWriter.write("Total Movement Time: " + difference + "\n");
            myWriter.write("Total Number of Movements: " + ship.getBstDynData().size() + "\n");
            double maxCOG = Double.NEGATIVE_INFINITY;
            double maxSOG = Double.NEGATIVE_INFINITY;
            double sumCOG = 0;
            double sumSOG = 0;
            for (ShipDynData sdd: (List<ShipDynData>) ship.getBstDynData().inOrder()){
                if (sdd.getCog() > maxCOG){
                    maxCOG = sdd.getCog();
                }
                if (sdd.getSog() > maxSOG){
                    maxSOG = sdd.getSog();
                }
                sumCOG += sdd.getCog();
                sumSOG += sdd.getSog();
            }
            myWriter.write("Max COG: " + maxCOG + "\n");
            myWriter.write("Max SOG: " + maxSOG + "\n");
            myWriter.write("Mean COG: " + sumCOG/ship.getBstDynData().size() + "\n");
            myWriter.write("Mean SOG: " + sumSOG/ship.getBstDynData().size() + "\n");
            float departureLatitude = Float.parseFloat(ship.getBstDynData().departure().getLatitude());
            float departureLongitude = Float.parseFloat(ship.getBstDynData().departure().getLongitude());
            float arrivalLatitude = Float.parseFloat(ship.getBstDynData().arrival().getLatitude());
            float arrivalLongitude = Float.parseFloat(ship.getBstDynData().arrival().getLongitude());
            myWriter.write("Departure Latitude: " + departureLatitude + "\n");
            myWriter.write("Departure Longitude: " + departureLongitude + "\n");
            myWriter.write("Arrival Latitude: " + arrivalLatitude + "\n");
            myWriter.write("Arrival Longitude: " + arrivalLongitude + "\n");
            myWriter.write("Travelled distance: " + ship.getBstDynData().inorderCalculateDistance() + "m\n");
            myWriter.write("Delta distance: " + ship.getBstDynData().travelledDistance(departureLatitude, departureLongitude, arrivalLatitude, arrivalLongitude) + "m\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            myWriter.close();
        }
    }

}