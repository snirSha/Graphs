import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

class Graph_Test {

	//	public DGraph initConnectedGraph() {
	//		Point3D pArr []= new Point3D[4];
	//		pArr[0] = new Point3D(-5, 5);
	//		pArr[1] = new Point3D(5, 5);
	//		pArr[2] = new Point3D(5, -5);
	//		pArr[3] = new Point3D(-5, -5);
	//		
	//		DGraph g = new DGraph();
	//		Node arr[] = new Node[4];
	//		for(int i = 0; i < arr.length; i++) {
	//			arr[i] = new Node(pArr[i], 0, "", 0);
	//			g.addNode(arr[i]);
	//		}
	//		g.connect(0, 1, 2);
	//		g.connect(1, 2, 3);
	//		g.connect(2, 3, 4);
	//		g.connect(3, 1, 5);
	//		return g;
	//	}


	@Test
	void initTest() {
		Graph_Algo ga = new Graph_Algo();
		DGraph dg = new DGraph();
		ga.init(dg);
		boolean ans = ga.g == dg;
		assertEquals(true, ans);
	}
	/** 
	 * Compute a deep copy of this graph.
	 * @return
	 */
	@Test
	void copyTest() {
		Graph_Algo ga = new Graph_Algo();
		ga.g.addNode(new Node());
		ga.g.addNode(new Node(new Point3D(5, 5), 2, "", 0));
		ga.g.connect(0, 1, 2);
		Graph_Algo ga2 = new Graph_Algo();
		ga2 = (Graph_Algo) ga.copy();
		Collection<edge_data> cl = ga2.g.getE(0);
		for(edge_data e: cl) {
			Edge ed = (Edge)e;
			assertEquals(true, ed.getSrc() == 0 && ed.getDest() == 1);
		}
		//assertEquals(true, true);
	}
	/**
	 * Init a graph from file
	 * @param file_name
	 */
	@Test
	void initAndSaveFromFileTest() {
		Graph_Algo ga = new Graph_Algo();
		ga.g.addNode(new Node());
		ga.g.addNode(new Node(new Point3D(5, 5), 2, "", 0));
		ga.g.connect(4, 5, 2);
		ga.save("JunitTest");
		Graph_Algo ga2 = new Graph_Algo();
		ga2.init("JunitTest");
		Collection<edge_data> cl = ga2.g.getE(0);
		for(edge_data e: cl) {
			Edge ed = (Edge)e;
			System.out.println(ed.getDest());
			assertEquals(true, ed.getSrc() == 4 && ed.getDest() == 5);
		}
	}

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return
	 */
	@Test
	void isConnectedTest() {
//		fail("notTestedYet");
	}
	/**
	 * returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Test
	void shortestPathDistTest() {
		fail("notTestedYet");
	}
	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Test
	void shortestPathTest() {
		fail("notTestedYet");
	}
	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem, 
	 * as you can visit a node more than once, and there is no need to return to source node - 
	 * just a simple path going over all nodes in the list. 
	 * @param targets
	 * @return
	 */
	@Test
	void TSPTest() {
		fail("notTestedYet");
	}

}
