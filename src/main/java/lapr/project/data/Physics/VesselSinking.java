package lapr.project.data.Physics;

public class VesselSinking {

    private double vesselLenght;
    private double vesselWidth;
    private double tipMass;
    private double bodyMass;
    private double towerMass;
    private double forceWOCargo;
    private double forceWCargo;
    private double pressureWOCargo;
    private double pressureWCargo;
    private double vesselBaseArea;
    private double vesselPlacedMass;
    private int numberOfContainers;
    private final double SEAWATER_DENSITY=1030d;
    private final double draft = 15d;
    private final double containerMass = 500d;
    private final double GRAVITATIONAL_FORCE = 10d;
    private double vesselHeightBeforerSink;
    private double vesselSinking;
    VesselCenterOfMass centerOfMass;

    public VesselSinking(String typeOfVessel, int numberOfContainers) {
        centerOfMass = new VesselCenterOfMass(typeOfVessel);
        tipMass = centerOfMass.getMass(centerOfMass.getTipVolume());
        bodyMass = centerOfMass.getMass(centerOfMass.getBodyVolume());
        towerMass = centerOfMass.getMass(centerOfMass.getTowerVolume());
        vesselLenght = centerOfMass.getVesselLength();
        vesselWidth = centerOfMass.getVesselWidth();
        this.numberOfContainers = numberOfContainers;
        vesselPlacedMass = getVesselPlacedMass();
        forceWOCargo = getForceWithoutCargo();
        vesselBaseArea = getVesselBaseArea();
        pressureWOCargo = getPressureWOCargo();
        forceWCargo = getForceWithCargo();
        pressureWCargo = getPressureWCargo();
        vesselHeightBeforerSink = getVesselHeightBeforerSink();
        vesselSinking = vesselHeightBeforerSink - draft;
    }

    public double getForceWithoutCargo(){
        return (tipMass*2 + bodyMass + towerMass)*GRAVITATIONAL_FORCE*1000;
    }

    public double getForceWithCargo(){
        return (tipMass*2 + bodyMass + towerMass + vesselPlacedMass/10)*GRAVITATIONAL_FORCE*1000;
    }

    public double getVesselPlacedMass(){
        return numberOfContainers*containerMass;
    }

    public double getVesselBaseArea(){
        return vesselLenght*vesselWidth;
    }

    public double getPressureWOCargo(){
        return (forceWOCargo/vesselBaseArea)/1000;
    }

    public double getPressureWCargo(){
        return (forceWCargo/vesselBaseArea)/1000;
    }

    public double getVesselHeightBeforerSink(){
        return (pressureWCargo-pressureWOCargo)/(SEAWATER_DENSITY*GRAVITATIONAL_FORCE) + draft;
    }

    public double getVesselSinking() {
        return vesselSinking;
    }

    public double getSeaWaterDensity() {
        return SEAWATER_DENSITY;
    }

    public double getVesselMass(){
        return vesselPlacedMass;
    }

    public double getDraft() {
        return draft;
    }

    public double getContainerMass() {
        return containerMass;
    }

    public double getGravitationForce() {
        return GRAVITATIONAL_FORCE;
    }

    public double getArea(){
        return vesselBaseArea;
    }

    public double getPressureCargo(){
        return pressureWCargo;
    }

    public double getPressureNoCargo(){
        return pressureWOCargo;
    }

    public double getVesselHeight(){
        return vesselHeightBeforerSink;
    }
}
