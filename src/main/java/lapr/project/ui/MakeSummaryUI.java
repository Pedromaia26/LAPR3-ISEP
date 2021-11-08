package lapr.project.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.controller.ImportShipsController;
import lapr.project.controller.SearchShipController;
import lapr.project.model.BSTShip;
import lapr.project.model.Ship;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class MakeSummaryUI {

    public static void main(String[] args) throws IOException, SQLException{
        Scanner ler = new Scanner(System.in);
        SearchShipController createSearchShipController = new SearchShipController();

        System.out.println("Select the parameter you want to search --> 1 - MMSI || 2 - IMO || 3 - Call Sign");
        int a = ler.nextInt();
        if(a==1){
            System.out.println("Insert the ship MMSI:");
            int mmsi = ler.nextInt();
            createSearchShipController.searchDeatils(createSearchShipController.ShipSearchByMmsi(mmsi));
        }
        if(a==2){
            System.out.println("Insert the ship IMO:");
            String imo = ler.next();
            createSearchShipController.searchDeatils(createSearchShipController.ShipSearchByImo(imo));
        }
        if(a==3){
            System.out.println("Insert the ship Call Sign:");
            String callsign = ler.next();
            createSearchShipController.searchDeatils(createSearchShipController.ShipSearchByCallSign(callsign));
        }
    }
}