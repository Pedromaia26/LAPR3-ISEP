package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void testEqualsNull() {
        Edge edge = new Edge("a","b",3f);
        assertNotEquals(null, edge);
    }

    @Test
    void testEqualsSameObject(){
        Edge edge = new Edge("a","b",3f);
        assertTrue(edge.equals(edge));
    }

    @Test
    void testEqualsDifferentClass(){
        Edge edge = new Edge("a","b",3f);
        assertFalse(edge.equals(1));
    }

    @Test
    void testToString(){
        Edge edge = new Edge("a","b",3f);
        String expected = "a -> b\nDistance: 3,00";
        assertEquals(expected, edge.toString());
    }


}