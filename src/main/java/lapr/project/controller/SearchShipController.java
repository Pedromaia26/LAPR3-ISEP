package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchShipController {

    BSTShip shipBST;
    private Ship ship;
    private Company c;

    public SearchShipController(Company c) {
        this.c = c;
        shipBST = c.getBstShips();
    }

    public SearchShipController() {
        this.c = App.getInstance().getCompany();
        shipBST = c.getBstShips();
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

    public Ship IdentifyTheShip(String path) throws IOException {
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
                    default:
                        ship = null;
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
        return ship;
    }

    public double getmaxCog(Iterable<ShipDynData> list){
        double maxCOG = Double.NEGATIVE_INFINITY;
        for (ShipDynData sdd: list){
            if (sdd.getCog() > maxCOG){
                maxCOG = sdd.getCog();
            }
        }
        return maxCOG;
    }

    public double getmaxSog(Iterable<ShipDynData> list){
        double maxSOG = Double.NEGATIVE_INFINITY;
        for (ShipDynData sdd: list){
            if (sdd.getSog() > maxSOG){
                maxSOG = sdd.getSog();
            }
        }
        return maxSOG;
    }

    public float getsumSog(Iterable<ShipDynData> list){
        float sumSOG = 0f;
        for (ShipDynData sdd: list){
            sumSOG += sdd.getSog();
        }
        return sumSOG;
    }

    public float getsumCog(Iterable<ShipDynData> list){
        float sumCOG = 0f;
        for (ShipDynData sdd: list){
            sumCOG += sdd.getCog();
        }
        return sumCOG;
    }

    public void searchDetails(String path) throws IOException {
        Ship ship = IdentifyTheShip(path);
        StringBuilder data = new StringBuilder();
        if (ship != null) {
            data.append("Details of the ship " + String.valueOf(ship.getMmsi()) + ":\n");
            data.append("MMSI: " + ship.getMmsi() + "\n");
            data.append("Name: " + ship.getShipName() + "\n");
            data.append("IMO: " + ship.getImo() + "\n");
            data.append("Call Sign: " + ship.getCallSign() + "\n");
            data.append("Vessel type: " + ship.getVesselType() + "\n");
            data.append("Length: " + ship.getLength() + "m\n");
            data.append("Width: " + ship.getWidth() + "m\n");
            data.append("Draft: " + ship.getDraft() + "\n");
            FileOperation.writeToAFile("Output/ShipDetails.txt", data);
        }
        else {
            data.append("Incorrect input");
            FileOperation.writeToAFile("Output/ShipDetails.txt", data);
        }
    }

    public void makeSummary(String path) throws IOException {
        Ship ship = IdentifyTheShip(path);
        StringBuilder data = new StringBuilder();
        if (ship != null){
            String pattern = "dd/MM/yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            long diff = ship.getBstDynData().arrival().getBaseDateTime().getTime() - ship.getBstDynData().departure().getBaseDateTime().getTime();
            TimeUnit time = TimeUnit.MINUTES;
            long difference = time.convert(diff, TimeUnit.MILLISECONDS);
            float departureLatitude = Float.parseFloat(ship.getBstDynData().departure().getLatitude());
            float departureLongitude = Float.parseFloat(ship.getBstDynData().departure().getLongitude());
            float arrivalLatitude = Float.parseFloat(ship.getBstDynData().arrival().getLatitude());
            float arrivalLongitude = Float.parseFloat(ship.getBstDynData().arrival().getLongitude());
            data.append("Summary of the ship " + String.valueOf(ship.getMmsi()) + ":\n");
            data.append("Vessel Name: " + ship.getVesselType() + "\n");
            data.append("Start Base Date Time: " + simpleDateFormat.format(ship.getBstDynData().departure().getBaseDateTime()) + "\n");
            data.append("End Base Date Time: " + simpleDateFormat.format(ship.getBstDynData().arrival().getBaseDateTime()) + "\n");
            data.append("Total Movement Time: " + difference + " minutes\n");
            data.append("Total Number of Movements: " + (ship.getBstDynData().size()-1) + "\n");
            data.append("Max COG: " + String.format("%.2f", getmaxCog(ship.getBstDynData().inOrder())) + "\n");
            data.append("Max SOG: " + String.format("%.2f", getmaxSog(ship.getBstDynData().inOrder())) + "\n");
            data.append("Mean COG: " + String.format("%.2f", getsumCog(ship.getBstDynData().inOrder())/ship.getBstDynData().size()) + "\n");
            data.append("Mean SOG: " + String.format("%.2f", getmaxSog(ship.getBstDynData().inOrder())/ship.getBstDynData().size()) + "\n");
            data.append("Departure Latitude: " + departureLatitude + "\n");
            data.append("Departure Longitude: " + departureLongitude + "\n");
            data.append("Arrival Latitude: " + arrivalLatitude + "\n");
            data.append("Arrival Longitude: " + arrivalLongitude + "\n");
            data.append("Travelled distance: " + String.format("%.2f", ship.getBstDynData().inorderCalculateDistance(ship.getBstDynData().departure().getBaseDateTime(), ship.getBstDynData().arrival().getBaseDateTime())) + "m\n");
            data.append("Delta distance: " + String.format("%.2f", ship.getBstDynData().travelledDistance(departureLatitude, departureLongitude, arrivalLatitude, arrivalLongitude)) + "m\n");
            FileOperation.writeToAFile("Output/SummaryShipMovements.txt", data);
        }
        else {
            data.append("Incorrect input");
            FileOperation.writeToAFile("Output/SummaryShipMovements.txt", data);
        }

    }

}