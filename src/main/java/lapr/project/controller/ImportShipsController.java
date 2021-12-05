package lapr.project.controller;

import lapr.project.data.DatabaseOperations;
import lapr.project.model.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ImportShipsController {

    private Company company;
    public ImportShipsController(Company company){
        this.company = company;
    }

    public ImportShipsController(){
        this.company = App.getInstance().getCompany();
    }

    public void importFromCSV(String file) throws IOException {
        String line = "";
        BSTShip shipBST = company.getBstShips();
        BSTDynData shipdyndataBST;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String splitBy = ",";
        try {
            br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] shipData = line.split(splitBy);
                Ship ship = new Ship(shipData[0], shipData[7], shipData[8], shipData[9], shipData[10], shipData[11], shipData[12], shipData[13]);
                ShipDynData dynamicData = new ShipDynData(shipData[1], shipData[2], shipData[3], shipData[4], shipData[5], shipData[6], shipData[14], shipData[15]);
                if (shipBST.find(ship) == null) {
                    shipdyndataBST = new BSTDynData();
                    shipdyndataBST.insert(dynamicData);
                    ship.setBstDynData(shipdyndataBST);
                    shipBST.insert(ship);
                }else {
                    shipdyndataBST = shipBST.find(ship).getBstDynData();
                    shipdyndataBST.insert(dynamicData);
                    shipBST.find(ship).setBstDynData(shipdyndataBST);
                }
                line = br.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
        company.setBstShips(shipBST);
    }

    public void insertIntoDatabase(){
        DatabaseOperations databaseOperations = new DatabaseOperations();
        for(Ship ship : (List<Ship>) App.getInstance().getCompany().getBstShips().inOrder()){
            databaseOperations.saveShip(App.getInstance().getDatabaseConnection(), ship);
            for (ShipDynData data : (List<ShipDynData>)ship.getBstDynData().inOrder()){
                databaseOperations.saveShipDynData(App.getInstance().getDatabaseConnection(), data, String.valueOf(ship.getMmsi()));
            }
        }
    }
}
