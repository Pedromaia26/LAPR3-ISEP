package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ship implements Comparable<Ship> {
    private int mmsi;
    private String shipName;
    private String imo;
    private int generators;
    private int genertorPowerOutput;
    private String callSign;
    private String vesselType;
    private int lenght;
    private int width;
    private int capacity;
    private float draft;
    private BSTDynData bstDynData;

    public Ship(String mmsi, String shipName, String imo, String callSign, String vesselType, String lenght, String width, String draft) {
        this.mmsi = Integer.parseInt(mmsi);
        this.shipName = shipName;
        this.imo = imo;
        //this.generators = generators;
        //this.genertorPowerOutput = genertorPowerOutput;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.lenght = Integer.parseInt(lenght);
        this.width = Integer.parseInt(width);
        //this.capacity = capacity;
        this.draft = Float.parseFloat(draft);
        bstDynData = new BSTDynData();
    }



    public void setDraft(int draft) {
        this.draft = draft;
    }

    public int getMmsi() {
        return mmsi;
    }

    public String getShipName() {
        return shipName;
    }

    public String getImo() {
        return imo;
    }

    public int getGenerators() {
        return generators;
    }

    public int getGenertorPowerOutput() {
        return genertorPowerOutput;
    }

    public String getCallSign() {
        return callSign;
    }

    public String getVesselType() {
        return vesselType;
    }

    public int getLenght() {
        return lenght;
    }

    public int getWidth() {
        return width;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getDraft() {
        return draft;
    }

    public BSTDynData getBstDynData() {
        return bstDynData;
    }

    public void setBstDynData(BSTDynData bstDynData) {
        this.bstDynData = bstDynData;
    }

    @Override
    public int compareTo(Ship o) {
        return 0;
    }

    @Override
    public String toString() {
        Iterable<BSTDynData> it = bstDynData.inOrder();
        return "Ships{" + "mmsi='" + mmsi + it + '\'' + "}\n";
    }
}
