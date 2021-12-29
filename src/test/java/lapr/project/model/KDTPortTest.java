package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KDTPortTest {

    Port port = new Port("29002","Liverpool","Europe","United Kingdom","53.46666660","-3.033333330");
    KDTPort tree = new KDTPort();

    @Test
    void root() {
        tree.insert(port, 53.46666667, -3.033333333);
        assertEquals(port, tree.root());
    }

    @Test
    void isEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    void isNotEmpty() {
        tree.insert(port, 53.46666667, -3.033333333);
        assertFalse(tree.isEmpty());
    }

    @Test
    void insert() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333335");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333331");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333335);
        tree.insert(port4, 53.46666669, -3.033333331);
        tree.insert(port5, 53.46666662, -3.033333332);
    }

    @Test
    void findNearestNeighbourFirstPort() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333335");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333331");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333335);
        tree.insert(port4, 53.46666669, -3.033333331);
        tree.insert(port5, 53.46666662, -3.033333332);
        assertEquals(port, tree.findNearestNeighbour(53.46666660, -3.033333330));
    }

    @Test
    void findNearestNeighbourLastPort() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333335");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333331");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333335);
        tree.insert(port4, 53.46666669, -3.033333331);
        tree.insert(port5, 53.46666662, -3.033333332);
        assertEquals(port5, tree.findNearestNeighbour(53.46666662, -3.033333332));
    }

    @Test
    void findNearestNeighbourLastPort2() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333331");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333335");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333331);
        tree.insert(port4, 53.46666669, -3.033333335);
        tree.insert(port5, 53.46666662, -3.033333332);
        assertEquals(port4, tree.findNearestNeighbour(53.46666669, -3.033333335));
    }

    @Test
    void findNearestNeighbourNotCordsEquals() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333331");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333335");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333331);
        tree.insert(port4, 53.46666669, -3.033333335);
        tree.insert(port5, 53.46666662, -3.033333332);
        assertEquals(port1, tree.findNearestNeighbour(53.46666665, -3.033333335));
    }

    @Test
    void inOrder() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333331");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333335");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        tree.insert(port, 53.46666660, -3.033333330);
        tree.insert(port1, 53.46666665, -3.033333333);
        tree.insert(port2, 53.46666666, -3.033333334);
        tree.insert(port3, 53.46666668, -3.033333331);
        tree.insert(port4, 53.46666669, -3.033333335);
        tree.insert(port5, 53.46666662, -3.033333332);
        Iterable<Port> list = tree.inOrder();
        List<Port> expected = new ArrayList<>();
        expected.add(port);
        expected.add(port2);
        expected.add(port4);
        expected.add(port1);
        expected.add(port5);
        expected.add(port3);
        assertEquals(expected, list);
    }

    @Test
    void buildTree() {
        Port port1 = new Port("29001","Liverpool","Europe","United Kingdom","53.46666665","-3.033333333");
        Port port2 = new Port("29003","Liverpool","Europe","United Kingdom","53.46666666","-3.033333334");
        Port port3 = new Port("29004","Liverpool","Europe","United Kingdom","53.46666668","-3.033333331");
        Port port4 = new Port("29005","Liverpool","Europe","United Kingdom","53.46666669","-3.033333335");
        Port port5 = new Port("29006","Liverpool","Europe","United Kingdom","53.46666662","-3.033333332");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port1.getLatitude(), port1.getLongitude()));
        tree.buildTree(lista);
    }
}