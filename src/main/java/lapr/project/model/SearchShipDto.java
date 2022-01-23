package lapr.project.model;

import java.util.Objects;

public class SearchShipDto {

    private int mmsi;
    private String shipName;
    private String imo;
    private String callSign;
    private String vesselType;
    private int lenght;
    private int width;
    private float draft;


    public SearchShipDto(Ship ship){
        this.mmsi = ship.getMmsi();
        this.shipName = ship.getShipName();
        this.imo = ship.getImo();
        this.callSign = ship.getCallSign();
        this.vesselType = ship.getVesselType();
        this.lenght = ship.getLength();
        this.width = ship.getWidth();
        this.draft = ship.getDraft();

    }

    /**
     * Returns the textual description of parameter category Dto.
     * @return Characteristics of the parameter category Dto
     */
    @Override
    public String toString() {
        return String.format("Mmsi: %s Ship Name: %s Imo: %s Call Sign: %s Vessel Type: %s Lenght: %s Width: %s  Draft: %s",mmsi, shipName, imo, callSign, vesselType, lenght, width, draft);
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getVesselType() {
        return vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getDraft() {
        return draft;
    }

    public void setDraft(float draft) {
        this.draft = draft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchShipDto that = (SearchShipDto) o;
        return mmsi == that.mmsi;
    }
}

