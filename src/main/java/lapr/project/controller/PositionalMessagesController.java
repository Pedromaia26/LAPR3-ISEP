package lapr.project.controller;

import lapr.project.model.BSTDynData;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynData;
import lapr.project.utils.FileOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PositionalMessagesController {

    int mmsi;
    StringBuilder data = new StringBuilder();
    List<Ship> ships;
    List<ShipDynData> datesShip;
    String dates[];


    public void message (String file) throws ParseException, IOException {

        ships = (List<Ship>) App.getInstance().getCompany().getBstShips().inOrder();

        BufferedReader br = new BufferedReader(new FileReader(file));

        try {

            mmsi = Integer.parseInt(br.readLine());

            String d = br.readLine();
            dates = d.split(";");




            if (dates.length == 1) {
                date(dates, ships);
            } else
                period(dates, ships);
        }catch (IOException e){
            e.printStackTrace();

        }finally {
            br.close();
        }


    }

        /*do{
            dates[position] = d;
            System.out.println("DATA NA POSIÇAO I: " + dates[position]);
            d = br.readLine();
            System.out.println(d);
        }while (d!=null);*/


    public void date(String dates[], List<Ship> ships) throws ParseException, IOException {



        Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[0]);
        for (Ship s : ships) {
            if (mmsi == s.getMmsi()) {
                data.append(s.getBstDynData().searchSpecificDate(newDate));
                System.out.println(data);
            }
        }
        FileOperation.writeToAFile("posMsg.txt", data);
    }

    public void period(String dates[], List<Ship> ships) throws ParseException, IOException {
        Date dateN = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[0]);
        Date dateM = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[1]);

        for (Ship s : ships) {
            if (mmsi == s.getMmsi()) {
                datesShip = s.getBstDynData().searchSpecificDatePeriodcall(dateN, dateM);
                for (ShipDynData l : datesShip) {
                    data.append(l+"\n");
                }
            }
        }
        FileOperation.writeToAFile("posMsg.txt", data);
    }







        /*List<ShipDynData> list = selected.getBstDynData().searchSpecificDatePeriodcall(dateN, dateM);

        for (ShipDynData l : list) {
            System.out.println(l.getBaseDateTime());
        }


        System.out.println("\n------------------------------\n");
        for (Ship s: ships) {
            System.out.println(s);
        }

        System.out.print("\nSelect the ship whose positional messages you want to access (MMSI): ");
        mmsi = sc.nextInt();


        for (Ship s: ships) {
            if (mmsi == s.getMmsi()) {
                System.out.printf("Ship selected: %s\n", s);
                System.out.println(s.getBstDynData());
                selected = s;
            }
        }

        System.out.println("Choose an option:");
        System.out.println("1 - Date");
        System.out.println("2 - Period");

        int opt = sc.nextInt();

        if (opt==1){



        }else if (opt==2) {

            sc.nextLine();

            System.out.print("\nSelect the period: ");
            date1 = sc.nextLine();
            date2 = sc.nextLine();

            Date dateN = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date1);
            Date dateM = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date2);

            List<ShipDynData> list = selected.getBstDynData().searchSpecificDatePeriodcall(dateN, dateM);

            for (ShipDynData l : list) {
                System.out.println(l.getBaseDateTime());
            }
        }*/
}