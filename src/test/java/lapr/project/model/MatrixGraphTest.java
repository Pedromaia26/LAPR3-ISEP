package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;

import lapr.project.controller.App;
import lapr.project.controller.BuildFreightNetworkController;
import lapr.project.controller.ImportCountriesBordersSeadistsController;
import lapr.project.model.Edge;
import lapr.project.model.Graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ISEP
 *
 */
public class MatrixGraphTest {
    final ArrayList<String> co = new ArrayList<>(Arrays.asList( "A", "A", "B", "C", "C", "D", "E", "E"));
    final ArrayList <String> cd = new ArrayList<>(Arrays.asList("B", "C", "D", "D", "E", "A", "D", "E"));
    final ArrayList <Integer> cw = new ArrayList<>(Arrays.asList( 1,  2 ,  3 ,  4 ,  5 ,  6 ,  7 ,  8 ));

    final ArrayList <String> ov = new ArrayList<>(Arrays.asList( "A",  "B",  "C" ,  "D" ,  "E" ));
    MatrixGraph<String, Integer> instance = null;

    @BeforeEach
    public void initializeGraph() {
        instance = new MatrixGraph<>(true) ;
    }

    /**
     * Test of copy constructor of class Graph.
     */
    @Test
    public void testCopyConstructor() {


        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Graph <String,Integer> g = new MatrixGraph<>(instance);
        assertEquals( instance.getClass(), g.getClass(), "The graphs should be from the same class");
        assertEquals(instance, g, "The graphs should have equal contents");
    }

    /**
     * Test of isDirected method, of class Graph.
     */
    @Test
    public void testIsDirected() {


        assertTrue( instance.isDirected(), "result should be true");
        instance = new MatrixGraph<>(false);
        assertFalse(instance.isDirected(), "result should be false");
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {

        assertEquals(0, instance.numVertices(), "result should be zero");
        instance.addVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.addVertex("B");
        assertEquals(2, instance.numVertices(), "result should be two");
        instance.removeVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.removeVertex("B");
        assertEquals(0, instance.numVertices(), "result should be zero");
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {


        assertEquals(0, instance.vertices().size(), "vertices should be empty");

        instance.addVertex("A");
        instance.addVertex("B");

        Collection<String> cs = instance.vertices();
        assertEquals(2, cs.size(), "Must have 2 vertices");
        cs.removeAll(Arrays.asList("A","B"));
        assertEquals(0, cs.size(), "Vertices should be A and B");

        instance.removeVertex("A");

        cs = instance.vertices();
        assertEquals(1, cs.size(), "Must have 1 vertice1");
        cs.removeAll(Arrays.asList("B"));
        assertEquals(0, cs.size(), "Vertice should be B");

        instance.removeVertex("B");
        cs = instance.vertices();
        assertEquals(0, cs.size(), "Must not have any vertice");
    }
    /**
     * Test of validVertex method, of class Graph.
     */
    @Test
    public void testValidVertex() {


        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (String v : co)
            assertTrue(instance.validVertex(v), "vertices should exist");


        assertFalse(instance.validVertex("Z"), "vertice should not exist");
    }
    /**
     * Test of key method, of class Graph.
     */
    @Test
    public void testKey() {


        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < ov.size(); i++)
            assertEquals(i, instance.key(ov.get(i)), "vertices should exist");

        assertEquals(-1, instance.key("Z"), "vertice should not exist");
    }
    /**
     * Test of testAdjVertices method, of class Graph.
     */
    @Test
    public void testAdjVertices() {


        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection <String> cs = instance.adjVertices("A");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("B") || s.equals("C"));
        assertEquals(0, cs.size(), "Adjacents should be B and C");

        cs = instance.adjVertices("B");
        assertEquals(1, cs.size(), "Num adjacents should be 1");
        cs.removeIf(s -> s.equals("D"));
        assertEquals(0, cs.size(), "Adjacents should be S");

        cs = instance.adjVertices("E");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("D") || s.equals("E"));
        assertEquals(0, cs.size(), "Adjacents should be D and E");
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {


        assertEquals(0, instance.numEdges(), "result should be zero");

        instance.addEdge("A","B",1);
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.addEdge("A","C",2);
        assertEquals(2, instance.numEdges(), "result should be two");

        instance.removeEdge("A","B");
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.removeEdge("A","C");
        assertEquals(0, instance.numEdges(), "result should be zero");
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {


        assertEquals(0, instance.edges().size(), "edges should be empty");

        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection <Edge<String,Integer>> ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 8 edges");
        for (int i = 0; i <co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getDistance().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A","B");
        ced = instance.edges();
        assertEquals(7, ced.size(), "Must have 7 edges");
        for (int i = 1; i <co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getDistance().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("E","E");
        ced = instance.edges();
        assertEquals(6, ced.size(), "Must have 6 edges");
        for (int i = 1; i < co.size()-1; i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getDistance().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A","C"); instance.removeEdge("B","D");
        instance.removeEdge("C","D"); instance.removeEdge("C","E");
        instance.removeEdge("D","A"); instance.removeEdge("E","D");

        assertEquals(0, instance.edges().size(), "edges should be empty");
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i <co.size(); i++)
            assertEquals(cw.get(i), instance.edge(co.get(i),cd.get(i)).getDistance(), "edge between "+co.get(i)+" - "+cd.get(i)+" should be "+cw.get(i));

        assertNull(instance.edge("A","E"), "edge should be null");
        assertNull(instance.edge("D","B"), "edge should be null");
        instance.removeEdge("D","A");
        assertNull(instance.edge("D","A"), "edge should be null");
    }

    /**
     * Test of getEdge by key method, of class Graph.
     */
    @Test
    public void testGetEdgeByKey() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i <co.size(); i++)
            assertEquals(cw.get(i), instance.edge(instance.key(co.get(i)),instance.key(cd.get(i))).getDistance(), "edge between "+co.get(i)+" - "+cd.get(i)+" should be "+cw.get(i));

        assertNull(instance.edge(instance.key("A"),instance.key("E")), "edge should be null");
        assertNull(instance.edge(instance.key("D"),instance.key("B")), "edge should be null");
        instance.removeEdge("D","A");
        assertNull(instance.edge(instance.key("D"),instance.key("A")), "edge should be null");
    }


    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.outDegree("G"), "degree should be -1");
        assertEquals(2, instance.outDegree("A"), "degree should be 2");
        assertEquals(1, instance.outDegree("B"), "degree should be 1");
        assertEquals(2, instance.outDegree("E"), "degree should be 2");
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.inDegree("G"), "degree should be -1");
        assertEquals(1, instance.inDegree("A"), "degree should be 1");
        assertEquals(3, instance.inDegree("D"), "degree should be 3");
        assertEquals(2, instance.inDegree("E"), "degree should be 2");
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection <Edge<String,Integer>> coe = instance.outgoingEdges("C");
        assertEquals(2, coe.size(), "Outgoing edges of vert C should be 2");
        coe.removeIf(e -> e.getDistance()==4 || e.getDistance()==5);
        assertEquals(0, coe.size(), "Outgoing edges of vert C should be 4 and 5");

        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be 2");
        coe.removeIf(e -> e.getDistance() == 7 || e.getDistance() == 8);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7 and 8");

        instance.removeEdge("E","E");

        coe = instance.outgoingEdges("E");
        assertEquals(1, coe.size(), "Outgoing edges of vert E should be 1");
        coe.removeIf(e -> e.getDistance() == 7);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7");

        instance.removeEdge("E","D");

        coe = instance.outgoingEdges("E");
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be empty");
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection <Edge<String,Integer>> cie = instance.incomingEdges("D");
        assertEquals(3, cie.size(), "Incoming edges of vert C should be 3");
        cie.removeIf(e -> e.getDistance() == 3 || e.getDistance() == 4 || e.getDistance() == 7);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 3, 4 and 7");

        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert E should be 2");
        cie.removeIf(e -> e.getDistance() == 5 || e.getDistance() == 8);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5 and 8");

        instance.removeEdge("E","E");

        cie = instance.incomingEdges("E");
        assertEquals(1, cie.size(), "Incoming edges of vert E should be 1");
        cie.removeIf(e -> e.getDistance() == 5);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5");

        instance.removeEdge("C","E");

        cie = instance.incomingEdges("E");
        assertEquals(0, cie.size(), "Incoming edges of vert C should be empty");
    }


    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));


        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");
        instance.removeVertex("A");
        assertEquals(4, instance.numVertices(), "Num vertices should be 4");
        assertEquals(5, instance.numEdges(), "Num vertices should be 5");
        instance.removeVertex("B");
        assertEquals(3, instance.numVertices(), "Num vertices should be 3");
        assertEquals(4, instance.numEdges(), "Num vertices should be 4");
        instance.removeVertex("C");
        assertEquals(2, instance.numVertices(), "Num vertices should be 2");
        assertEquals(2, instance.numEdges(), "Num vertices should be 2");
        instance.removeVertex("D");
        assertEquals(1, instance.numVertices(), "Num vertices should be 1");
        assertEquals(1, instance.numEdges(), "Num vertices should be 1");
        instance.removeVertex("E");
        assertEquals(0, instance.numVertices(), "Num vertices should be 0");
        assertEquals(0, instance.numEdges(), "Num vertices should be 0");
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {


        assertEquals(0, instance.numEdges(), "Num edges should be 0");

        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(5, instance.numVertices(), "Num edges should be 5");
        assertEquals(8, instance.numEdges(), "Num edges should be 8");

        for (int i = 0; i <co.size() - 1; i++) {
            instance.removeEdge(co.get(i),cd.get(i));
            Collection <Edge< String, Integer>> ced = instance.edges();
            int expected = co.size() - i - 1;
            assertEquals(expected, ced.size(), "Expected size is "+expected);
            for (int j = i + 1; j < co.size(); j++) {
                int finalJ = j;
                ced.removeIf(e -> e.getVOrig().equals(co.get(finalJ)) && e.getVDest().equals(cd.get(finalJ)) && e.getDistance().equals(cw.get(finalJ)));
            }
            assertEquals(0, ced.size(),"Expected size is 0");
        }
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");

        Graph<String,Integer> instClone = instance.clone();

        for (int i = 0; i <co.size(); i++) {
            Edge<String, Integer> ec = instClone.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getDistance());
        }

        for (String v : co)
            instClone.removeVertex(v);

        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");
        assertEquals(0, instClone.numVertices(), "Num vertices should be 0");
        assertEquals(0, instClone.numEdges(), "Num vertices should be 0");
    }

    @Test
    public void testEquals() {


        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        MatrixGraph<String,Integer> otherInst =new MatrixGraph<>(true) ;
        for (int i = 0; i <co.size(); i++)
            otherInst.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeVertex("A");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeVertex("A");

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeEdge("C", "E");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeEdge("C", "E");

        assertEquals(instance, otherInst, "Graphs should be equal");
    }

    @Test
    public void testUnDirectedGraph() {
        instance = new MatrixGraph<>(false);

        for (int i = 0; i <co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getDistance());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getDistance());
        }

        instance.removeEdge(co.get(0), cd.get(0));

        for (int i = 1; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getDistance());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getDistance());
        }
    }

    @Test
    void startGraph(){
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> list = new ArrayList<>();
        list.add(new GraphElement(country1));
        list.add(new GraphElement(country2));
        list.add(new GraphElement(country3));
        list.add(new GraphElement(country4));
        list.add(new GraphElement(country5));
        Object [][] m = {{1,1,0,0,0}, {1,0,1,0,0},{1,1,1,0,0},{0,0,1,0,0},{0,1,1,0,0}};
        MatrixGraph matrix = new MatrixGraph(true, list, m);
    }

    @Test
    void toStringTest(){
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> list = new ArrayList<>();
        list.add(new GraphElement(country1));
        list.add(new GraphElement(country2));
        list.add(new GraphElement(country3));
        list.add(new GraphElement(country4));
        list.add(new GraphElement(country5));
        Object [][] m = {{10f,7f,5f,1f,1f}, {1f,1f,1f,1f,1f},{1f,1f,1f,1f,1f},{1f,1f,1f,1f,1f},{1f,1f,1f,1f,1f}};
        MatrixGraph matrix = new MatrixGraph(true, list, m);
        String expected = "\nMatrix:\n" +
                "   |  0  |  1  |  2  |  3  |  4 \n" +
                " 0 |     |  X  |  X  |  X  |  X  \n" +
                " 1 |  X  |     |  X  |  X  |  X  \n" +
                " 2 |  X  |  X  |     |  X  |  X  \n" +
                " 3 |  X  |  X  |  X  |     |  X  \n" +
                " 4 |  X  |  X  |  X  |  X  |     \n\nEdges:\n" +
                "From 0 to 1-> GraphElement{designation='Lisboa', country='Portugal'} -> GraphElement{designation='Madrid', country='Espanha'}\n" +
                "Distance: 7,00\n" +
                "From 0 to 2-> GraphElement{designation='Lisboa', country='Portugal'} -> GraphElement{designation='Paris', country='Franca'}\n" +
                "Distance: 5,00\n" +
                "From 0 to 3-> GraphElement{designation='Lisboa', country='Portugal'} -> GraphElement{designation='Berna', country='Suica'}\n" +
                "Distance: 1,00\n" +
                "From 0 to 4-> GraphElement{designation='Lisboa', country='Portugal'} -> GraphElement{designation='Berlim', country='Alemanha'}\n" +
                "Distance: 1,00\n" +
                "From 1 to 0-> GraphElement{designation='Madrid', country='Espanha'} -> GraphElement{designation='Lisboa', country='Portugal'}\n" +
                "Distance: 1,00\n" +
                "From 1 to 2-> GraphElement{designation='Madrid', country='Espanha'} -> GraphElement{designation='Paris', country='Franca'}\n" +
                "Distance: 1,00\n" +
                "From 1 to 3-> GraphElement{designation='Madrid', country='Espanha'} -> GraphElement{designation='Berna', country='Suica'}\n" +
                "Distance: 1,00\n" +
                "From 1 to 4-> GraphElement{designation='Madrid', country='Espanha'} -> GraphElement{designation='Berlim', country='Alemanha'}\n" +
                "Distance: 1,00\n" +
                "From 2 to 0-> GraphElement{designation='Paris', country='Franca'} -> GraphElement{designation='Lisboa', country='Portugal'}\n" +
                "Distance: 1,00\n" +
                "From 2 to 1-> GraphElement{designation='Paris', country='Franca'} -> GraphElement{designation='Madrid', country='Espanha'}\n" +
                "Distance: 1,00\n" +
                "From 2 to 3-> GraphElement{designation='Paris', country='Franca'} -> GraphElement{designation='Berna', country='Suica'}\n" +
                "Distance: 1,00\n" +
                "From 2 to 4-> GraphElement{designation='Paris', country='Franca'} -> GraphElement{designation='Berlim', country='Alemanha'}\n" +
                "Distance: 1,00\n" +
                "From 3 to 0-> GraphElement{designation='Berna', country='Suica'} -> GraphElement{designation='Lisboa', country='Portugal'}\n" +
                "Distance: 1,00\n" +
                "From 3 to 1-> GraphElement{designation='Berna', country='Suica'} -> GraphElement{designation='Madrid', country='Espanha'}\n" +
                "Distance: 1,00\n" +
                "From 3 to 2-> GraphElement{designation='Berna', country='Suica'} -> GraphElement{designation='Paris', country='Franca'}\n" +
                "Distance: 1,00\n" +
                "From 3 to 4-> GraphElement{designation='Berna', country='Suica'} -> GraphElement{designation='Berlim', country='Alemanha'}\n" +
                "Distance: 1,00\n" +
                "From 4 to 0-> GraphElement{designation='Berlim', country='Alemanha'} -> GraphElement{designation='Lisboa', country='Portugal'}\n" +
                "Distance: 1,00\n" +
                "From 4 to 1-> GraphElement{designation='Berlim', country='Alemanha'} -> GraphElement{designation='Madrid', country='Espanha'}\n" +
                "Distance: 1,00\n" +
                "From 4 to 2-> GraphElement{designation='Berlim', country='Alemanha'} -> GraphElement{designation='Paris', country='Franca'}\n" +
                "Distance: 1,00\n" +
                "From 4 to 3-> GraphElement{designation='Berlim', country='Alemanha'} -> GraphElement{designation='Berna', country='Suica'}\n" +
                "Distance: 1,00\n" +
                "\n";
        assertEquals(expected, matrix.toString().replace(".", ","));
    }

    @Test
    void transitiveClosure() throws IOException {
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Spain", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "France", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Switzerland", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Germany", 0.5f, "Berlim", 35, 25);
        Country country6 = new Country("America", "CL", "CHL", "Chile", 16.8f, "Santiago", -33.45f, -70.666667f);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        listGraph.add(new GraphElement(country6));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d, 2d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        ImportCountriesBordersSeadistsController icontroller = new ImportCountriesBordersSeadistsController();
        icontroller.importFromDatabaseCountries();
        icontroller.importFromDatabaseBorders();
        icontroller.importFromDatabaseSeadists();
        matrix.transitiveClosure(matrix);
    }


    @Test
    void dijkstra() {
        List<Integer> list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        list = matrix.dijkstra(matrix, "Paris", "Lisboa", 3);
        list2.add(2);
        list2.add(0);
        assertEquals(list, list2);
    }

    @Test
    void dijkstraPathNotFound() {
        List<Integer> list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        list = matrix.dijkstra(matrix, "Paris", "Lisboa", 2);
        assertEquals(list, list2);
    }

    @Test
    void dijkstraLandPath() {
        List<Integer> list;
        List<Integer> list2 = new ArrayList<>();
        Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        ArrayList<GraphElement> listGraph = new ArrayList<>();
        listGraph.add(new GraphElement(country1));
        listGraph.add(new GraphElement(country2));
        listGraph.add(new GraphElement(country3));
        listGraph.add(new GraphElement(country4));
        listGraph.add(new GraphElement(country5));
        Object[][] m = {{10d, 7d, 5d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}, {1d, 1d, 1d, 1d, 1d}};
        MatrixGraph matrix = new MatrixGraph(true, listGraph, m);
        list = matrix.dijkstra(matrix, "Paris", "Lisboa", 1);
        list2.add(2);
        list2.add(0);
        assertEquals(list, list2);
    }
}