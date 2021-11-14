package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileOperation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintingShipsInfoController {

    private Ship ships;
    StringBuilder data = new StringBuilder();;
    private BSTShip bstShip;
    private Iterable<Ship> shipsIterable;
    private List<PrintShipsInfo> printShipsInfos;

    public PrintingShipsInfoController(Company c) {
        this.bstShip = c.getBstShips();
    }

    public PrintingShipsInfoController() {
        this.bstShip = App.getInstance().getCompany().getBstShips();
    }

    public void getShips() throws IOException {

        this.shipsIterable = bstShip.inOrder();
        printShipsInfos = new ArrayList<>();

        organizeInformation();

        data.append("----Order by number of movements ascending----\n\n");

        organizeAsc();

        printInformation();

        data.append("\n----Order by number of movements descending----\n\n");

        organizeDesc();

        printInformation();

        FileOperation.writeToAFile("Output/listAllShips.txt", data);
    }

    public void organizeInformation(){
        for(Ship s : shipsIterable){
            BSTDynData bstDynData = s.getBstDynData();
            int mmsi = s.getMmsi();
            double realDist = bstDynData.inorderCalculateDistance(bstDynData.departure().getBaseDateTime(), bstDynData.arrival().getBaseDateTime());
            int numberOfMovements = bstDynData.size()-1;
            ShipDynData firstDate = bstDynData.arrival();
            ShipDynData lastDate = bstDynData.departure();
            double deltaDistance = bstDynData.travelledDistance(Float.parseFloat(firstDate.getLatitude()), Float.parseFloat(firstDate.getLongitude()), Float.parseFloat(lastDate.getLatitude()), Float.parseFloat(lastDate.getLongitude()));

            PrintShipsInfo printShipsInf = new PrintShipsInfo(mmsi, numberOfMovements, deltaDistance, realDist);
            printShipsInfos.add(printShipsInf);
        }
    }

    public void organizeAsc(){
        Collections.sort(printShipsInfos, new Comparator<PrintShipsInfo>() {
            @Override
            public int compare(PrintShipsInfo p1, PrintShipsInfo p2){
                if(p1.getNumberofMovements() > p2.getNumberofMovements()){
                    return 1;
                }
                else{
                    if(p1.getNumberofMovements() < p2.getNumberofMovements()){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        //data.append(printShipsInfos);
    }

    public void organizeDesc(){
        Collections.sort(printShipsInfos, new Comparator<PrintShipsInfo>() {
            @Override
            public int compare(PrintShipsInfo p1, PrintShipsInfo p2){
                if(p1.getRealdistance() < p2.getRealdistance()){
                    return 1;
                }
                else{
                    if(p1.getRealdistance() > p2.getRealdistance()){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        //data.append(printShipsInfos);
    }

    public void printInformation(){
        for (PrintShipsInfo info : printShipsInfos){
            data.append("MMSI: " + info.getMmsi() + " - Number of movements: " + info.getNumberofMovements() + " - Travelled distance: " + String.format("%.2f", info.getRealdistance()) + "m - Delta distance: " + String.format("%.2f", info.getDeltadistance()) + "m\n");
        }
    }
}




