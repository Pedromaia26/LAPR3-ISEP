package lapr.project.controller;

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




        /*System.out.print("\nSelect the date: ");
        sc.nextLine();
        date = sc.nextLine();
        Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);

        for (Ship s: ships) {
            for (ShipDynData d: (List<ShipDynData>) s.getBstDynData().inOrder()){
                if (newDate.equals(d.getBaseDateTime())){
                    System.out.println(d);
                }
            }
        }*/


        System.out.print("\nSelect the period: ");
        sc.nextLine();
        date1 = sc.nextLine();
        date2 = sc.nextLine();

        Date dateN = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date1);
        Date dateM = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date2);

        for (ShipDynData d: (List<ShipDynData>) selected.getBstDynData().inOrder()){
            if (dateN.before(d.getBaseDateTime()) && dateM.after(d.getBaseDateTime())){
                    System.out.println(d);
            }
        }


    }
}
