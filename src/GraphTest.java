import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testSearch() throws Exception {
        Graph g = new Graph();
        HashMap<String, Node> nodes = g.getNodes();

        Stack<Node> path = g.search("B&O Railroad", "Reading Railroad", false);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Reading Railroad"), path.pop());
        assertEquals(nodes.get("B&O Railroad"), path.pop());
        assertEquals(0, path.size());

        path = g.search("Kentucky Avenue", "Baltic Avenue", false);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Baltic Avenue"), path.pop());
        assertEquals(nodes.get("Reading Railroad"), path.pop());
        assertEquals(nodes.get("B&O Railroad"), path.pop());
        assertEquals(nodes.get("Indiana Avenue"), path.pop());
        assertEquals(nodes.get("Kentucky Avenue"), path.pop());
        assertEquals(0, path.size());

        path = g.search("Baltic Avenue", "Baltic Avenue", false);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Baltic Avenue"), path.pop());
        assertEquals(0, path.size());

        path = g.search("B&O Railroad", "Reading Railroad", true);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Reading Railroad"), path.pop());
        assertEquals(nodes.get("B&O Railroad"), path.pop());
        assertEquals(0, path.size());

        path = g.search("Kentucky Avenue", "Baltic Avenue", true);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Baltic Avenue"), path.pop());
        assertEquals(nodes.get("Reading Railroad"), path.pop());
        assertEquals(nodes.get("B&O Railroad"), path.pop());
        assertEquals(nodes.get("Indiana Avenue"), path.pop());
        assertEquals(nodes.get("Kentucky Avenue"), path.pop());
        assertEquals(0, path.size());

        path = g.search("Baltic Avenue", "Baltic Avenue", true);
        assertNotEquals(null, path);

        assertEquals(nodes.get("Baltic Avenue"), path.pop());
        assertEquals(0, path.size());
    }

    @Test
    public void testMostInteresting() {
        Graph g = new Graph();
        Node[] mostInt = g.mostInteresting(5);

        assertNotEquals(null, mostInt);
        for(int i = 1; i < mostInt.length; i++)
            if(mostInt[i].getInterest() < mostInt[i - 1].getInterest())
                fail(mostInt[i].getInterest() + " < " + mostInt[i - 1].getInterest());

                    
        mostInt = g.mostInteresting(30);

        assertNotEquals(null, mostInt);
        for(int i = 1; i < mostInt.length; i++)
            if(mostInt[i].getInterest() < mostInt[i - 1].getInterest())
                fail(mostInt[i].getInterest() + " < " + mostInt[i - 1].getInterest());
	}

	@Test
	public void testPlanner() {
        Graph g = new Graph();

        for(Stack<Node> p : g.planner(false, 200, "Short Line")) {
            for(Node n : p) {
                System.out.println(n);
            }
            System.out.println("------");
        }

        for(Stack<Node> p : g.planner(false, 500, "B&O Railroad")) {
            for(Node n : p) {
                System.out.println(n);
            }
            System.out.println("------");
        }
	}
}
