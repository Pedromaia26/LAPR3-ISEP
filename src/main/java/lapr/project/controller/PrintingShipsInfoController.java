package lapr.project.controller;

import lapr.project.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintingShipsInfoController {

    private Ship ships;
    private BSTShip bstShip;
    private Iterable<Ship> shipsIterable;
    private List<PrintShipsInfo> printShipsInfos;

    public void getShips(){
        this.shipsIterable = bstShip.inOrder();
        printShipsInfos = new ArrayList<>();

        organizeInformation();

        organizeAsc();

        System.out.println(printShipsInfos);

        organizeDesc();

        System.out.println(printShipsInfos);
    }

    public void organizeInformation(){
        for(Ship s : shipsIterable){
            BSTDynData bstDynData = s.getBstDynData();
            int mmsi = s.getMmsi();
            double realDist = bstDynData.inorderCalculateDistance();
            int numberOfMovements = bstDynData.size();
            ShipDynData firstDate = bstDynData.arrival();
            ShipDynData lastDate = bstDynData.departure();
            double deltaDistance = bstDynData.travelledDistance((firstDate.getLatitude(), firstDate.getLongitude(), lastDate.getLatitude(), lastDate.getLongitude());

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
        }

        public void organizeDesc(){
            Collections.sort(printShipsInfos, new Comparator<PrintShipsInfo>() {
                @Override
                public int compare(PrintShipsInfo p1, PrintShipsInfo p2){
                    if(p1.getRealdistance() > p2.getRealdistance()){
                        return 1;
                    }
                    else{
                        if(p1.getRealdistance() < p2.getRealdistance()){
                            return -1;
                        }
                        else{
                            return 0;
                        }
                    }
                }
            });
        }


    }




