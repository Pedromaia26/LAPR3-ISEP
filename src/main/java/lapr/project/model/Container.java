package lapr.project.model;

public class Container {


    private String containerId, outerMat, middleMat, innerMat, refrigerated;
    private int x, y, z, temp;
    private double k1, k2, k3, l1, l2, l3;

    public Container(String containerId, int x, int y, int z, int refrigerated, String outerMat, String middleMat, String innerMat,
                     double k1, double k2, double k3,
                     double l1, double l2, double l3, int temp){
        this.containerId = containerId;
        this.x = x;
        this.y = y;
        this.z = z;
        if (refrigerated == 1) {
            this.refrigerated = "Refrigerated";
        }else if (refrigerated == 0){
            this.refrigerated = "Not Refrigerated";
        }
        this.outerMat = outerMat;
        this.middleMat = middleMat;
        this.innerMat = innerMat;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.temp = temp;

    }

    public int getTemp() {
        return temp;
    }

    public String getContainerId() {
        return containerId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public double getK1() {
        return k1;
    }

    public double getK2() {
        return k2;
    }

    public double getK3() {
        return k3;
    }

    public double getL1() {
        return l1;
    }

    public double getL2() {
        return l2;
    }

    public double getL3() {
        return l3;
    }

}
