package lapr.project.model;

public class Container {


    private final String containerId;
    private final String outerMat;
    private final String middleMat;
    private final String innerMat;
    private String refrigerated;
    private final int x;
    private final int y;
    private final int z;
    private final int temp;
    private final double k1;
    private final double k2;
    private final double k3;
    private final double l1;
    private final double l2;
    private final double l3;
    private final double area;

    public Container(String containerId, int x, int y, int z, int refrigerated, String outerMat, String middleMat, String innerMat,
                     double k1, double k2, double k3,
                     double l1, double l2, double l3, int temp, double area){
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
        this.area = area;

    }

    public double getArea() {
        return area;
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
