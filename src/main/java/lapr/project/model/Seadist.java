package lapr.project.model;

public class Seadist {

    private String fromCountryName;
    private int fromPortId;
    private String fromPortName;
    private String toCountryName;
    private int toPortId;
    private String toPortName;
    private int seaDistance;

    public Seadist(String fromCountry, int fromPortId, String fromPortName, String toCountryName, int toPortId, String toPortString, int seaDistance) {
        this.fromCountryName = fromCountry;
        this.fromPortId = fromPortId;
        this.fromPortName = fromPortName;
        this.toCountryName = toCountryName;
        this.toPortId = toPortId;
        this.toPortName = toPortString;
        this.seaDistance = seaDistance;
    }

    public String getFromCountryName() {
        return fromCountryName;
    }

    public int getFromPortId() {
        return fromPortId;
    }

    public String getFromPortName() {
        return fromPortName;
    }

    public String getToCountryName() {
        return toCountryName;
    }

    public int getToPortId() {
        return toPortId;
    }

    public String getToPortName() {
        return toPortName;
    }

    public int getSeaDistance() {
        return seaDistance;
    }
}
