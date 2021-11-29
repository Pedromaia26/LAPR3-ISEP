package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NearestPortController {

    private Ship ship;
    private Company company;
    private List<Ship> shipList;
    private String callSign;
    private String date;
    private KDTPort kdtPort;
    private Port nearestPort;
    private BSTShip bstShip;

    public NearestPortController(Company company){
        this.company = company;
        this.bstShip = company.getBstShips();
    }

    public NearestPortController(){
        this.company = App.getInstance().getCompany();
        this.bstShip = company.getBstShips();
    }

    public void getClosestPort (String file) throws ParseException, IOException {

        shipList = (List<Ship>) company.getBstShips().inOrder();
        kdtPort = company.getKdtPorts();


        BufferedReader br = new BufferedReader(new FileReader(file));

        try {

            callSign = br.readLine();
            ship = bstShip.searchShipByCallSign(callSign);
            date = br.readLine();
            Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);


            ShipDynData pos = ship.getBstDynData().searchSpecificDate(newDate);
            double lat = Double.parseDouble(pos.getLatitude());
            double lng = Double.parseDouble(pos.getLongitude());
            nearestPort = kdtPort.findNearestNeighbour(lat, lng);

            FileOperation.writeToAFileNearestPort("Output/nearestPort.txt", nearestPort);


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            br.close();
        }
    }

}