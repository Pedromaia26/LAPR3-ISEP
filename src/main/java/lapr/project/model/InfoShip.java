package lapr.project.model;

import java.util.Date;
import java.util.List;

public class InfoShip implements Comparable<InfoShip>{

    private final int mmsi;
    private final double averageSpeed;
    private final double travelledDistance;

    public InfoShip(Ship ship, Date date1 , Date date2) {
        this.mmsi = ship.getMmsi();
        this.averageSpeed = calculateAverageSpeed(ship, date1, date2);
        this.travelledDistance = ship.getBstDynData().inorderCalculateDistance(date1, date2);
    }

    public double calculateAverageSpeed(Ship ship, Date date1 , Date date2){
        int size = 0;
        double sumSOG = 0;
        for (ShipDynData sdd: (List<ShipDynData>) ship.getBstDynData().inOrder()){
            if (date1.compareTo(sdd.getBaseDateTime()) <= 0 && date2.compareTo(sdd.getBaseDateTime()) >= 0){
                sumSOG += sdd.getSog();
                size++;
            }
        }
        if (size != 0) return sumSOG/size;
        else return -1;

    }

    public int getMmsi() {
        return mmsi;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    @Override
    public String toString() {
        return "Ship: " +
                "mmsi=" + mmsi +
                "; averageSpeed=" + averageSpeed +
                "; travelledDistance=" + travelledDistance +
                "\n";
    }

    @Override
    public int compareTo(InfoShip o){
        if (this.mmsi > o.getMmsi()) return 1;
        else if (this.mmsi == o.getMmsi()) return 0;
        return -1;
    }
}
