import static org.junit.Assert.*;
import java.util.Stack;
import java.util.HashMap;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testSearch() {
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

    // @Test
    public void testMostInteresting() {
        fail("Not yet implemented");
	}

	// @Test
	public void testPlanner() {
		fail("Not yet implemented");
	}

}
