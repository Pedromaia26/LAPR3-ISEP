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
    StringBuilder data;
    List<Ship> ships;
    List<ShipDynData> datesShip;
    String dates[];
    Company company;

    public PositionalMessagesController(){
        data = new StringBuilder();
        company = App.getInstance().getCompany();
    }

    public PositionalMessagesController(Company company){
        data = new StringBuilder();
        this.company = company;
    }

    public void message (String file) throws ParseException, IOException {

        ships = (List<Ship>) company.getBstShips().inOrder();

        BufferedReader br = new BufferedReader(new FileReader(file));

        try {
            mmsi = Integer.parseInt(br.readLine());
            String d = br.readLine();
            dates = d.split(";");

            if (dates.length == 1) {
                date(dates, ships);
            } else if (dates.length==2) {
                period(dates, ships);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            br.close();
        }
    }


    public void date(String dates[], List<Ship> ships) throws ParseException, IOException {
        Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[0]);
        for (Ship s : ships) {

            if (mmsi == s.getMmsi()) {
                data.append("Positional value of the ship " + s.getMmsi() + ":\n");
                data.append(s.getBstDynData().searchSpecificDate(newDate));
            }
        }
        FileOperation.writeToAFile("Output/posMsg.txt", data);
    }

    public void period(String dates[], List<Ship> ships) throws ParseException, IOException {
        Date dateN = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[0]);
        Date dateM = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dates[1]);
        for (Ship s : ships) {
            if (mmsi == s.getMmsi()) {
                data.append("Positional values of the ship " + s.getMmsi() + ":\n");
                datesShip = s.getBstDynData().searchSpecificDatePeriodcall(dateN, dateM);
                for (ShipDynData l : datesShip) {
                    data.append(l+"\n");

                }
            }
        }
        FileOperation.writeToAFile("Output/posMsg.txt", data);
    }
}