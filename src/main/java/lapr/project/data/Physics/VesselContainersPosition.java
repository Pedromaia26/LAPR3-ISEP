package lapr.project.data.Physics;

public class VesselContainersPosition {

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
    private final double containerLenght = 6.06d;
    private final double containerWidth = 2.44d;
    private final double containerHeight = 2.59d;
    private final double containerMass = 298.74d;
    private final double numberOfContainers;
    private final double containersBodyCenterOfMassX = 45d;
    private final double containersBodyCenterOfMassY = 16.5d;
    private final double vesselCenterOfMassX;
    private final double vesselCenterOfMassY;
    VesselCenterOfMass centerOfMass;

    public VesselContainersPosition(int numberOfContainers) {
        centerOfMass = new VesselCenterOfMass("Container Ship");
        vesselLength = centerOfMass.getVesselLength();
        vesselWidth = centerOfMass.getVesselWidth();
        vesselHeight = centerOfMass.getVesselHeight();
        vesselTipLength = centerOfMass.getVesselTipLength();
        vesselTipHeight = centerOfMass.getVesselTipHeight();
        vesselBodyLength = centerOfMass.getVesselBodyLength();
        vesselBodyHeight = centerOfMass.getVesselBodyHeight();
        vesselTowerLength = centerOfMass.getVesselTowerLength();
        vesselTowerHeight = centerOfMass.getVesselTowerHeight();
        this.numberOfContainers = numberOfContainers;
        vesselCenterOfMassX = calculateCenterOfMassX();
        vesselCenterOfMassY = calculateCenterOfMassY();
    }

    public double calculateCenterOfMassX(){
        double tipVolume = centerOfMass.getTipVolume();
        double bodyVolume = centerOfMass.getBodyVolume();
        double towerVolume = centerOfMass.getTowerVolume();
        double tipMass = centerOfMass.getMass(tipVolume);
        double bodyMass = centerOfMass.getMass(bodyVolume);
        double towerMass = centerOfMass.getMass(towerVolume);
        double centerOfMassX = (tipMass*(vesselHeight/3) + bodyMass*(vesselLength/2) + tipMass*(vesselLength-vesselHeight/3) + towerMass*(vesselLength/2) + containerMass*(numberOfContainers/2)*containersBodyCenterOfMassX + containerMass*(numberOfContainers/2)*(vesselLength-containersBodyCenterOfMassX))/(tipMass*2 + bodyMass + towerMass + containerMass*numberOfContainers);
        return centerOfMassX;
    }

    public double calculateCenterOfMassY(){
        double tipVolume = centerOfMass.getTipVolume();
        double bodyVolume = centerOfMass.getBodyVolume();
        double towerVolume = centerOfMass.getTowerVolume();
        double tipMass = centerOfMass.getMass(tipVolume);
        double bodyMass = centerOfMass.getMass(bodyVolume);
        double towerMass = centerOfMass.getMass(towerVolume);
        double centerOfMassY = (tipMass*(vesselTipHeight-vesselHeight/3)*2 + bodyMass*(vesselHeight/2) + towerMass*(vesselHeight + vesselTowerHeight/2) + containerMass*(numberOfContainers/2)*containersBodyCenterOfMassY*2)/(tipMass*2 + bodyMass + towerMass + containerMass*numberOfContainers);
        return centerOfMassY;
    }

    public double getContainerArea(){
        return 2*(containerLenght*containerWidth + containerWidth*containerHeight + containerLenght*containerHeight);
    }

    public double getContainerVolume(){
        return containerLenght*containerWidth*containerHeight;
    }

    public double getCenterOfMassContainerX(){
        return containerLenght/2;
    }

    public double getCenterOfMassContainerY(){
        return containerWidth/2;
    }

    public double getCenterOfMassContainerZ(){
        return containerHeight/2;
    }

    public double getContainerMass(){
        return containerMass;
    }

    public double getContainerLenght() {
        return containerLenght;
    }

    public double getContainerWidth() {
        return containerWidth;
    }

    public double getContainerHeight() {
        return containerHeight;
    }

    public double getVesselCenterOfMassX() {
        return vesselCenterOfMassX;
    }

    public double getVesselCenterOfMassY() {
        return vesselCenterOfMassY;
    }
}
