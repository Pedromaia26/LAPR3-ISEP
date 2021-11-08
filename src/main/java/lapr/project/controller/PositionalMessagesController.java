package lapr.project.controller;

import lapr.project.model.BSTDynData;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PositionalMessagesController {

    Scanner sc = new Scanner(System.in);
    int mmsi;
    String date, date1, date2;
    Ship selected;
    BSTDynData bstDynData = new BSTDynData();

    public void message (Company c) throws ParseException {
        List<Ship> ships = (List<Ship>) c.getBstShips().inOrder();

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

            sc.nextLine();

            System.out.print("\nSelect the date: ");
            date = sc.nextLine();
            Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);

            System.out.println(selected.getBstDynData().searchSpecificDate(newDate).getBaseDateTime());

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
        }
    }
}
