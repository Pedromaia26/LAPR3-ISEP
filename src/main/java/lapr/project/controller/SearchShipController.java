package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

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

    public SearchShipController() {
        this.shipBST = App.getInstance().getCompany().getBstShips();
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

    public void IdentifyTheShip(String path) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            line = br.readLine();
            if (line != null) {
                switch (line) {
                    case "mmsi":
                        ship = ShipSearchByMmsi(Integer.parseInt(br.readLine()));
                        break;
                    case "imo":
                        ship = ShipSearchByImo(br.readLine());
                        break;
                    case "call sign":
                        ship = ShipSearchByCallSign(br.readLine());
                        break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void searchDeatils(String path) throws IOException {
        IdentifyTheShip(path);
        String data = "";
        long diff = ship.getBstDynData().arrival().getBaseDateTime().getTime() - ship.getBstDynData().departure().getBaseDateTime().getTime();
        TimeUnit time = TimeUnit.MINUTES;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
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
        float departureLatitude = Float.parseFloat(ship.getBstDynData().departure().getLatitude());
        float departureLongitude = Float.parseFloat(ship.getBstDynData().departure().getLongitude());
        float arrivalLatitude = Float.parseFloat(ship.getBstDynData().arrival().getLatitude());
        float arrivalLongitude = Float.parseFloat(ship.getBstDynData().arrival().getLongitude());
        data+="Details of the ship " + String.valueOf(ship.getMmsi()) + ":\n";
        data+="Vessel Name: " + ship.getVesselType() + "\n";
        data+="Start Base Date Time: " + ship.getBstDynData().departure().getBaseDateTime() + "\n";
        data+="End Base Date Time: " + ship.getBstDynData().arrival().getBaseDateTime() + "\n";
        data+="Total Movement Time: " + difference + " minutes\n";
        data+="Total Number of Movements: " + (ship.getBstDynData().size()-1) + "\n";
        data+="Max COG: " + maxCOG + "\n";
        data+="Max SOG: " + maxSOG + "\n";
        data+="Mean COG: " + sumCOG/ship.getBstDynData().size() + "\n";
        data+="Mean SOG: " + sumSOG/ship.getBstDynData().size() + "\n";
        data+="Departure Latitude: " + departureLatitude + "\n";
        data+="Departure Longitude: " + departureLongitude + "\n";
        data+="Arrival Latitude: " + arrivalLatitude + "\n";
        data+="Arrival Longitude: " + arrivalLongitude + "\n";
        data+="Travelled distance: " + ship.getBstDynData().inorderCalculateDistance() + "m\n";
        data+="Delta distance: " + ship.getBstDynData().travelledDistance(departureLatitude, departureLongitude, arrivalLatitude, arrivalLongitude) + "m\n";
        FileOperation.writeToAFile(String.valueOf("Output/" + ship.getMmsi()) + "details.txt", data);
    }

}