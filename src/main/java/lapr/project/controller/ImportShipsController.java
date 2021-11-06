package lapr.project.controller;

import lapr.project.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class ImportShipsController {

    public void importFromCSV(String file, Company company){
        String line = "";
        BSTShip shipBST = new BSTShip();
        BSTDynData shipdyndataBST;
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] shipData = line.split(splitBy);
                Ship ship = new Ship(shipData[0], shipData[7], shipData[8], shipData[9], shipData[10], shipData[11], shipData[12], shipData[13]);
                ShipDynData dynamicData = new ShipDynData(shipData[1], shipData[2], shipData[3], shipData[4], shipData[5], shipData[6], shipData[14], shipData[15]);
                if (!(shipBST.find(ship) == null)){
                    if((shipBST.find(ship).getMmsi() == ship.getMmsi())){
                        shipdyndataBST = shipBST.find(ship).getBstDynData();
                        shipdyndataBST.insert(dynamicData);
                        shipBST.find(ship).setBstDynData(shipdyndataBST);
                    }
                    else{
                        shipdyndataBST = new BSTDynData();
                        shipdyndataBST.insert(dynamicData);
                        ship.setBstDynData(shipdyndataBST);
                        shipBST.insert(ship);
                    }
                }
                else{
                    shipdyndataBST = new BSTDynData();
                    shipdyndataBST.insert(dynamicData);
                    ship.setBstDynData(shipdyndataBST);
                    shipBST.insert(ship);
                }
                line = br.readLine();
            }
            //Iterable<Ship> it = shipBST.inOrder();
            //System.out.println(it);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        company.setBstShips(shipBST);
    }
}
