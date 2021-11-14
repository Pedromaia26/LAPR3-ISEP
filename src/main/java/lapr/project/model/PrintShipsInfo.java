package lapr.project.model;

public class PrintShipsInfo {

    private int mmsi;
    private int numberofMovements;
    private double deltadistance;
    private double realdistance;


    public PrintShipsInfo(int mmsi, int numberofMovements, double deltadistance, double realdistance){
        this.mmsi = mmsi;
        this.numberofMovements = numberofMovements;
        this.deltadistance = deltadistance;
        this.realdistance = realdistance;
    }

    public int getNumberofMovements() {
        return numberofMovements;
    }

    public double getRealdistance() {
        return realdistance;
    }

    public int getMmsi() {
        return mmsi;
    }

    public double getDeltadistance() {
        return deltadistance;
    }

    @Override
    public String toString() {
        return "Ship: " +
                "mmsi=" + mmsi +
                "; numberofMovements=" + numberofMovements +
                "; deltadistance=" + deltadistance +
                "; realdistance=" + realdistance +
                "\n";
    }
}
