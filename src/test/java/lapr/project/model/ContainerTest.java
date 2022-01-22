package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container c = new Container("1025637", 1, 1, 1, 1, "iron", "wood", "iron", 80.4, 0.11, 80.4, 3, 2, 3, 7);

    @Test
    void getTemp() {
        int actual = c.getTemp();
        int expected = 7;

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getContainerId() {
        String actual = c.getContainerId();
        String expected = "1025637";

        Assert.assertEquals(expected, actual);

    }

    @Test
    void getX() {
        int actual = c.getX();
        int expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getY() {
        int actual = c.getY();
        int expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getZ() {
        int actual = c.getZ();
        int expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getK1() {
        double actual = c.getK1();
        double expected = 80.4;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void getK2() {
        double actual = c.getK2();
        double expected = 0.11;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void getK3() {
        double actual = c.getK3();
        double expected = 80.4;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void getL1() {
        double actual = c.getL1();
        double expected = 3;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void getL2() {
        double actual = c.getL2();
        double expected = 2;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void getL3() {
        double actual = c.getL3();
        double expected = 3;

        Assert.assertEquals(expected, actual, 0);
    }
}