package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Ship implements Comparable<Ship> {
    private final int mmsi;
    private final String shipName;
    private final String imo;
    private int generators;
    private int genertorPowerOutput;
    private final String callSign;
    private final String vesselType;
    private final int length;
    private final int width;
    private final int capacity;
    private final float draft;
    private BSTDynData bstDynData;

    public Ship(String mmsi, String shipName, String imo, String callSign, String vesselType, String length, String width, String draft) {
        if (mmsi.length() != 9) throw new IllegalArgumentException("Invalid mmsi");
        this.mmsi = Integer.parseInt(mmsi);
        this.shipName = shipName;
        if (imo.length() != 10) throw new IllegalArgumentException("Invalid imo: should be imo more 7 characters");
        this.imo = imo;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = Integer.parseInt(length);
        this.width = Integer.parseInt(width);
        this.capacity = 1000;
        this.draft = Float.parseFloat(draft);
        bstDynData = new BSTDynData();
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

    public float getDraft() {
        return draft;
    }

    public BSTDynData getBstDynData() {
        return bstDynData;
    }

    public int getGenerators() {
        return generators;
    }

    public int getGenertorPowerOutput() {
        return genertorPowerOutput;
    }

    public int getCapacity() {
        return capacity;
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
        return "Ships{" +
                "mmsi='" + mmsi + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return mmsi == ship.mmsi;
    }
}
