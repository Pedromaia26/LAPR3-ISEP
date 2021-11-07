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
    private int length;
    private int width;
    private int capacity;
    private float draft;
    private BSTDynData bstDynData;

    public Ship(String mmsi, String shipName, String imo, String callSign, String vesselType, String length, String width, String draft) {
        if (mmsi.length() != 9) throw new IllegalArgumentException("Invalid mmsi");
        this.mmsi = Integer.parseInt(mmsi);
        this.shipName = shipName;
        if (imo.length() != 10) throw new IllegalArgumentException("Invalid imo: should be imo more 7 characters");
        this.imo = imo;
        this.generators = 0;
        this.genertorPowerOutput = 0;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = Integer.parseInt(length);
        this.width = Integer.parseInt(width);
        this.capacity = 0;
        this.draft = Float.parseFloat(draft);
        bstDynData = new BSTDynData();
    }



    public void setDraft(float draft) {
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

    public int getLength() {
        return length;
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
        return "Ships{" + "mmsi='" + mmsi + '\'' + it + "}\n";
    }
}
