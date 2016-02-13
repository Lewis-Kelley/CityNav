import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * 
 * Class for constructing XML file for storage of map information
 * 
 * @author blankesa. Created Feb 6, 2016.
 */
public class XMLFileInput {

	public static void main(String[] args) throws Exception {

		HashMap<String, Node> nodes = new HashMap<String, Node>();
		Node n1 = new Node("Connecticut Avenue", new Coordinate(17, 0), 100);
		Node n2 = new Node("Vermont Avenue", new Coordinate(24, -3), 40);
		Node n3 = new Node("Oriental Avenue", new Coordinate(50, 12), 30);
		Node n4 = new Node("Baltic Avenue", new Coordinate(77, -5), 70);
		Node n5 = new Node("Mediterranean Avenue", new Coordinate(89, 6), 0);
		Node n6 = new Node("St. Charles Place", new Coordinate(-5, 18), 71);
		Node n7 = new Node("States Avenue", new Coordinate(12, 35), 90);
		Node n8 = new Node("Virginia Avenue", new Coordinate(2, 43), 150);
		Node n9 = new Node("St. James Place", new Coordinate(-9, 68), 110);
		Node n10 = new Node("Tennesee Avenue", new Coordinate(0, 72), 60);
		Node n11 = new Node("New York Avenue", new Coordinate(11, 90), 31);
		Node n12 = new Node("Kentucky Avenue", new Coordinate(16, 105), 80);
		Node n13 = new Node("Indiana Avenue", new Coordinate(31, 96), 41);
		Node n14 = new Node("Illinois Avenue", new Coordinate(42, 111), 130);
		Node n15 = new Node("Atlantic Avenue", new Coordinate(67, 83), 20);
		Node n16 = new Node("Ventnor Avenue", new Coordinate(78, 99), 42);
		Node n17 = new Node("Marvin Gardens", new Coordinate(99, 101), 72);
		Node n18 = new Node("Pacific Avenue", new Coordinate(113, 87), 81);
		Node n19 = new Node("North Carolina Avenue", new Coordinate(100, 69),
				120);
		Node n20 = new Node("Pennsylvania Avenue", new Coordinate(84, 50), 43);
		Node n21 = new Node("Park Place", new Coordinate(96, 31), 73);
		Node n22 = new Node("Boardwalk", new Coordinate(102, 14), 160);
		Node r1 = new Node("Reading Railroad", new Coordinate(62, 30), -50);
		Node r2 = new Node("Pennsylvania Railroad", new Coordinate(33, 51), -51);
		Node r3 = new Node("B&O Railroad", new Coordinate(53, 72), -52);
		Node r4 = new Node("Short Line", new Coordinate(68, 42), -53);
		Path p1t2 = new Path(n1, n2, 15, 6);
		Path p1t3 = new Path(n1, n3, 44, 16);
		Path p2t3 = new Path(n2, n3, 35, 11);
		Path p2tr1 = new Path(n2, r1, 51, 12);
		Path p3t4 = new Path(n3, n4, 50, 18);
		Path p4t5 = new Path(n4, n5, 18, 5);
		Path p4tr1 = new Path(n4, r1, 40, 13);
		Path p1t6 = new Path(n1, n6, 40, 12.5);
		Path p5t22 = new Path(n5, n22, 31, 15);
		Path p6t7 = new Path(n6, n7, 30, 11);
		Path p7t8 = new Path(n7, n8, 22, 5);
		Path p6t8 = new Path(n6, n8, 50, 14);
		Path p7tr2 = new Path(n7, r2, 49, 12);
		Path p8t9 = new Path(n8, n9, 41, 17);
		Path p9t10 = new Path(n9, n10, 33, 9);
		Path p9t11 = new Path(n9, n11, 69, 22);
		Path p10tr2 = new Path(n9, r2, 70, 18);
		Path r1tr2 = new Path(r1, r2, 60, 10);
		Path p11t12 = new Path(n11,n12, 51, 16);
		Path p12t13 = new Path(n12,n13, 24, 8);
		Path p13t14 = new Path(n13,n14,26,9);
		Path p12t14 = new Path(n12,n14, 38, 13);
		Path p13tr3 = new Path(n13, r3, 45, 16);
		Path p14t15 = new Path(n14,n15,50,17);
		Path p15t16 = new Path(n15,n16,41,18);
		Path p16t17 = new Path(n16,n17,38,14);
		Path p17t18 = new Path(n17, n18, 44, 20);
		Path p16tr3 = new Path(n16,r3,44,19);
		Path r1tr3 = new Path(r1, r3, 62, 7);
		Path r2tr3 = new Path(r2,r3,55,8);
		Path p18t19 = new Path(n18, n19, 27, 6);
		Path p19t20 = new Path(n19, n20, 31, 9);
		Path p20t21 = new Path(n20,n21, 44, 12);
		Path p21t22 = new Path(n21,n22, 35, 7);
		Path p18t20 = new Path(n18,n20,50,12);
		Path p19tr4 = new Path(n19,r4, 43, 10);
		Path p21r4 = new Path(n21,r4,41,9);
		Path r1r4 = new Path (r1,r4, 63,8);
		Path r2r4 = new Path(r2,r4,70,11);
		Path r3r4 = new Path(r3,r4, 58, 9);
		nodes.put(n1.getName(), n1);
		nodes.put(n2.getName(), n2);
		nodes.put(n3.getName(), n3);
		nodes.put(n4.getName(), n4);
		nodes.put(n5.getName(), n5);
		nodes.put(n6.getName(), n6);
		nodes.put(n7.getName(), n7);
		nodes.put(n8.getName(), n8);
		nodes.put(n9.getName(), n9);
		nodes.put(n10.getName(), n10);
		nodes.put(n11.getName(), n11);
		nodes.put(n12.getName(), n12);
		nodes.put(n13.getName(), n13);
		nodes.put(n14.getName(), n14);
		nodes.put(n15.getName(), n15);
		nodes.put(n16.getName(), n16);
		nodes.put(n17.getName(), n17);
		nodes.put(n18.getName(), n18);
		nodes.put(n19.getName(), n19);
		nodes.put(n20.getName(), n20);
		nodes.put(n21.getName(), n21);
		nodes.put(n22.getName(), n22);
		nodes.put(r1.getName(), r1);
		nodes.put(r2.getName(), r2);
		nodes.put(r3.getName(), r3);
		nodes.put(r4.getName(), r4);

		FileOutputStream fos = new FileOutputStream("Cities.xml");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		XMLEncoder xmlEncoder = new XMLEncoder(bos);
		xmlEncoder.writeObject(nodes);
		xmlEncoder.close();

	}

}