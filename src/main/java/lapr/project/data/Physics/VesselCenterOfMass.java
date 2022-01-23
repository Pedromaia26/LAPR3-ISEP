package lapr.project.data.Physics;

public class VesselCenterOfMass {

    private final double vesselLength;
    private final double vesselWidth;
    private final double vesselHeight;
    private final double vesselTipLength;
    private final double vesselTipHeight;
    private final double vesselBodyLength;
    private final double vesselBodyHeight;
    private final double vesselTowerLength;
    private final double vesselTowerHeight;
    private final double MATERIAL_DENSITY=7800d;
    private final double centerOfMassX;
    private final double centerOfMassY;

    public VesselCenterOfMass(String typeOfVessel){
        switch(typeOfVessel){
            case "Container Ship":
                vesselLength = 390;
                vesselWidth = 50;
                vesselHeight = 30;
                vesselTipLength = 30;
                vesselTipHeight = 30;
                vesselBodyLength= 330;
                vesselBodyHeight = 30;
                vesselTowerLength = 20;
                vesselTowerHeight = 5;
                centerOfMassX = calculateCenterOfMassX(vesselLength/2);
                break;
            case "Oil Tanker":
                vesselLength = 250;
                vesselWidth = 44;
                vesselHeight = 25;
                vesselTipLength = 25;
                vesselTipHeight = 25;
                vesselBodyLength= 200;
                vesselBodyHeight = 25;
                vesselTowerLength = 20;
                vesselTowerHeight = 5;
                centerOfMassX = calculateCenterOfMassX(20);
                break;
            case "Bulk Carrier":
                vesselLength = 362;
                vesselWidth = 65;
                vesselHeight = 56;
                vesselTipLength = 56;
                vesselTipHeight = 56;
                vesselBodyLength= 250;
                vesselBodyHeight = 56;
                vesselTowerLength = 20;
                vesselTowerHeight = 5;
                centerOfMassX = calculateCenterOfMassX(330);
                break;
            default:
                throw new IllegalArgumentException("The vessel type is not valid");
        }
        centerOfMassY = calculateCenterOfMassY();
    }

    private double calculateCenterOfMassX(double towerP){
        double tipVolume = getTipVolume();
        double bodyVolume = getBodyVolume();
        double towerVolume = getTowerVolume();
        double tipMass = getMass(tipVolume);
        double bodyMass = getMass(bodyVolume);
        double towerMass = getMass(towerVolume);
        double centerOfMassX = (tipMass*(2*vesselHeight/3) + bodyMass*(vesselLength/2) + tipMass*(vesselLength-2*vesselHeight/3) + towerMass*towerP)/(tipMass*2 + bodyMass + towerMass);
        return centerOfMassX;
    }

    private double calculateCenterOfMassY(){
        double tipVolume = getTipVolume();
        double bodyVolume = getBodyVolume();
        double towerVolume = getTowerVolume();
        double tipMass = getMass(tipVolume);
        double bodyMass = getMass(bodyVolume);
        double towerMass = getMass(towerVolume);
        double centerOfMassY = (tipMass*(vesselTipHeight-vesselHeight/3)*2 + bodyMass*(vesselHeight/2) + towerMass*(vesselHeight + vesselTowerHeight/2))/(tipMass*2 + bodyMass + towerMass);
        return centerOfMassY;
    }

    public double getMass(double volume){
        return volume*MATERIAL_DENSITY;
    }

    public double getTipVolume(){
        return vesselTipHeight*vesselHeight;
    }

    public double getBodyVolume(){
        return vesselWidth*vesselBodyLength*vesselHeight;
    }

    public double getTowerVolume(){
        return vesselTowerHeight*vesselTowerLength*vesselWidth;
    }

    public double getCenterOfMassX(){
        return centerOfMassX;
    }

    public double getCenterOfMassY() {
        return centerOfMassY;
    }

    public double getVesselLength() {
        return vesselLength;
    }

    public double getVesselWidth() {
        return vesselWidth;
    }

    public double getVesselHeight() {
        return vesselHeight;
    }

    public double getVesselTipLength() {
        return vesselTipLength;
    }

    public double getVesselTipHeight() {
        return vesselTipHeight;
    }

    public double getVesselBodyLength() {
        return vesselBodyLength;
    }

    public double getVesselBodyHeight() {
        return vesselBodyHeight;
    }

    public double getVesselTowerLength() {
        return vesselTowerLength;
    }

    public double getVesselTowerHeight() {
        return vesselTowerHeight;
    }

    public double getMATERIAL_DENSITY() {
        return MATERIAL_DENSITY;
    }
}
