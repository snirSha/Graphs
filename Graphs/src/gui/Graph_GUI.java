package gui;

import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import utils.Point3D;
import utils.StdDraw;
/*
 * This class draw graphs using stdDraw
 *
 * @authors Snir and Omer 
 */ 
public class Graph_GUI{


	public Graph_Algo ga;


	/*
	 * Default constructor
	 */
	public Graph_GUI() {
		
		ga = new Graph_Algo();
	}

	/*
	 * Copy constructor using the init function from Graph_Algo class
	 */
	public Graph_GUI(graph g) {	
		
		this.ga = new Graph_Algo();
		ga.init(g);
	}

	/*
	 * Add a node to the drawing using addNode function from DGraph
	 */
	public void addNode(Node a) {
		ga.g.addNode(a);
	}

	/*
	 * Draw the nodes
	 * @param x = the x of the node location (point) 
	 * @param y = the y of the node location (point)
	 * @param abs = the number of the node
	 */
	public void drawNodes() {
		try {
			Collection<node_data> n=ga.g.getV();
			for (node_data a:n) {
				double x=a.getLocation().x();
				double y=a.getLocation().y();
				StdDraw.setPenRadius(0.05);
				StdDraw.setPenColor(StdDraw.BLUE);//nodes in blue
				StdDraw.point(x,y);
				StdDraw.setPenColor(StdDraw.BLACK);
				String abs = a.getKey()+"";
				StdDraw.text(x,y,abs);
				
			}
		}catch(Exception e) {
			System.out.println("No nodes to draw");
		}
	}

	/*
	 * Draw the edges
	 * @param allNodes = A collection of all the nodes
	 * @param allEdgesOfNode = A collection of all the edges that came out of the parameter's node
	 * @param Sx = the x of the source location
	 * @param Sy = the y of the source location
	 * @param Dx = the x of the destination location
	 * @param Dy = the y of the destination location
	 * @param arrowX = the x of the "arrow" point location
	 * @param arrowY = the y of the "arrow" point location
	 * @param te = the string of the weight number
	 */
	public void drawEdges() {
		try {
			Collection<node_data> allNodes=ga.g.getV();
			if(allNodes != null) {
				for(node_data n:allNodes) {
					Collection<edge_data> allEdgesOfNode=ga.g.getE(n.getKey());
					if(allEdgesOfNode != null && allEdgesOfNode.size() > 0) {
						for(edge_data edges:allEdgesOfNode) {
							double Sx = ga.g.getNode(edges.getSrc()).getLocation().x();
							double Sy = ga.g.getNode(edges.getSrc()).getLocation().y();
							double Dx = ga.g.getNode(edges.getDest()).getLocation().x();
							double Dy = ga.g.getNode(edges.getDest()).getLocation().y();

							StdDraw.setPenRadius(0.005);
							StdDraw.setPenColor(StdDraw.ORANGE);//paint the line between the nodes in orange
							StdDraw.line(Sx,Sy,Dx,Dy);

							StdDraw.setPenRadius(0.02);
							StdDraw.setPenColor(StdDraw.RED);

							double arrowX= (Dx*8+Sx)/9;
							double arrowY= (Dy*8+Sy)/9;
							StdDraw.point(arrowX,arrowY);

							String te = edges.getWeight()+"";

							StdDraw.setPenRadius(0.1);
							StdDraw.setPenColor(Color.BLACK);

							double newX= (Dx*4+Sx)/5;
							double newY= (Dy*4+Sy)/5;

							StdDraw.text(newX, newY, te);
						}
					}
				}
			}

		}catch(Exception e) {
			System.out.println("No edges to Draw");
		}
	}


	/*
	 * Remove node from the drawing using removeNode from DGraph class
	 */
	public void removeNode(int x) {
		ga.g.removeNode(x);
	}
	
	/*
	 * Remove edge from the drawing using removeEdge from DGraph class 
	 */
	public void removeEdge(int x,int y) {
		ga.g.removeEdge(x,y);
	}

	/*
	 * Reverse the graph using reverseGraph in DGraph class
	 */
	public void reversedGraph() {
		ga.g.reversedGraph();
	}

	/*
	 * This function open a window and calls to drawNode and drawEdge
	 */
	public void drawDGraph() {
		try {
			if(ga.g.getV() != null) {
				StdDraw.setGui(this);
				
				
				
//				StdDraw.setCanvasSize(1000, 1000);
//				StdDraw.setXscale(-100,100);
//				StdDraw.setYscale(-100,100);
				setPageSize();
				drawEdges();
				drawNodes();
			}
			else {
				System.out.println("Nothing to draw");
			}
		}catch(Exception e){
			System.out.println("Nothing to draw");
		}
	}
	
	private void setPageSize() {
		double xMax = 0;
		double xMin = 0;
		double yMax = 0;
		double yMin = 0;
		for(node_data nd: ga.g.getV()) {
			Node n = (Node)nd;
			if(n.getLocation().x() > xMax) xMax = n.getLocation().x();
			else if (n.getLocation().x() < xMin) xMin = n.getLocation().x();
			if(n.getLocation().y() > yMax) yMax = n.getLocation().y();
			else if (n.getLocation().y() < yMin) yMin = n.getLocation().y();
		}
		
		int xCanvas = 5 * (int)(Math.abs(xMax) + Math.abs(xMin));
		int yCanvas = 5 * (int)(Math.abs(yMax) + Math.abs(yMin));
		
		StdDraw.setCanvasSize(xCanvas , yCanvas );
		StdDraw.setXscale(2 * xMin, 2 * xMax);
		StdDraw.setYscale(2 * yMin,2 * yMax);
		
	}

	/*
	 * Delete the graph in the drawing
	 */
	public void deleteGraph() {
		StdDraw.clear();
		ga.g = new DGraph();
	}
	
	public static void main (String [] args) {
		Graph_GUI gg = new Graph_GUI();
		Point3D p0=new Point3D(-50,50);
		Point3D p1=new Point3D(50,50);
		Point3D p2=new Point3D(0,0);
		Point3D p3=new Point3D(0,-50);
		//		Point3D p4=new Point3D(0,50);
		//		Point3D p5=new Point3D(10,40);
		//		Point3D p6=new Point3D(10,30);
		//		Point3D p7=new Point3D(0,10);
		//		Point3D p8=new Point3D(-10,2);
		//		Point3D p9=new Point3D(-50,-1);
		//		Point3D p10=new Point3D(60,-10);

		Node a=new Node(0,p0 ,0, "", 0);
		Node b=new Node(1,p1, 0, "sad", 0);
		Node c=new Node(2,p2, 0, "warryetj", 0);
		Node d=new Node(3,p3, 0, "", 0);
		Node e=new Node(4, new Point3D(100, 100), 0, "", 0);
		//		Node d=new Node(p4, 0, "", 0);	
		//		Node e=new Node(p5, 0, "", 0);
		//		Node f=new Node(p6, 0, "", 0);
		//		Node j=new Node(p7, 0, "", 0);
		//		Node h=new Node(p8, 0, "", 0);
		//		Node i=new Node(p9, 0, "", 0);
		//		Node k=new Node(p10, 0,"", 0);

		gg.addNode(a);
		gg.addNode(b);
		gg.addNode(c);
		gg.addNode(d);
		//gg.addNode(e);
		//		gg.addNode(d);
		//		gg.addNode(e);
		//		gg.addNode(f);
		//		gg.addNode(j);
		//		gg.addNode(h);
		//		gg.addNode(i);
		//		gg.addNode(k);


		//gg.ga.g.connect(a.getKey(),b.getKey(),4);
		gg.ga.g.connect(c.getKey(),a.getKey(), 1);
		gg.ga.g.connect(c.getKey(),d.getKey(),2);
		gg.ga.g.connect(c.getKey(),b.getKey(), 3);
		gg.ga.g.connect(a.getKey(), c.getKey(), 2.5);
		gg.ga.g.connect(b.getKey(), c.getKey(), 1.5);
		gg.ga.g.connect(d.getKey(), c.getKey(), 3);
		//gg.ga.g.connect(b.getKey(), a.getKey(), 4);		

		//	gg.drawDGraph();
		//		gg.g.connect(a.getKey(),e.getKey(),12);
		//		gg.g.connect(b.getKey(),f.getKey(), 0);
		//		gg.g.connect(b.getKey(),j.getKey(),1);
		//		gg.g.connect(c.getKey(),h.getKey(), 4);
		//		gg.g.connect(c.getKey(), i.getKey(), 5);
		//		gg.g.connect(d.getKey(), k.getKey(), 14);
		//		gg.g.connect(e.getKey(),k.getKey(), 0);
		//		gg.g.connect(f.getKey(),k.getKey(), 2);
		//		gg.g.connect(j.getKey(),k.getKey(), 15);
		//		gg.g.connect(h.getKey(),k.getKey(), 20);
		//		gg.g.connect(i.getKey(),k.getKey(), 35);

		/*
		 * check remove
		 */
		//gg.removeEdge(0, 1);




		//		System.out.println(gg.g.nodeSize());
		//		System.out.println(gg.g.edgeSize());
		//		gg.removeNode(0);
		//		System.out.println(gg.g.edgeSize());
		//		System.out.println(gg.g.nodeSize());
		//		System.out.println(gg.g.edgeSize());
		//gg.removeEdge(0, 2);
		//System.out.println(gg.g.edgeSize());
		//StdDraw.clear();


		/*
		 * check shortestPathDist and shortestPath
		 */

//		System.out.println("shortest path is (weight): "+gg.ga.shortestPathDist(2,0));
//		
//		System.out.println("isConnected: "+gg.ga.isConnected());
//
//		ArrayList<node_data> ans=(ArrayList<node_data>) gg.ga.shortestPath(2,0);
//		if(ans!=null) {
//			System.out.println("List of nodes (shortestPath): ");
//			for(node_data ar:ans) {
//				System.out.print(ar.getKey()+",");
//			}
//		}
//		else {
//			System.out.println("null");
//		}
//
//
//		/*
//		 * check TSP
//		 */
//
//		List<Integer> targets=new ArrayList<>();
//		targets.add(0);
//		targets.add(3);
//		targets.add(1);
//	
//		List<node_data> answer=gg.ga.TSP(targets);
//		System.out.println("\nThe TSP List is:");
//		if(answer==null)
//			System.out.println("null");
//		else {
//			for(node_data l:answer)
//				System.out.print(l.getKey()+",");
//		}
		
//		Graph_GUI ggg = new Graph_GUI();
//		Node aa = new Node(4,new Point3D(-40, -40), 0, "", 0);
//		Node bb = new Node(5,new Point3D(30, 40), 0, "", 0);
//		gg.addNode(aa);
//		gg.addNode(bb);
//		gg.ga.g.connect(aa.getKey(), bb.getKey(), 5);
		
		
//		ggg.ga.g.connect(aa.getKey(), bb.getKey(), 3);
		

		/*
		 * init,save check
		 */
		//				Graph_GUI oneWaySquare = new Graph_GUI();
		//				oneWaySquare.g = (DGraph) gg.ga.copy();
		//				
		//				oneWaySquare.ga.save("oneWaySquare.txt");
		//				
		//				Graph_GUI ga3 = new Graph_GUI();
		//				
		//				ga3.ga.init("oneWaySquare.txt");
		//				
		//				ga3.drawDGraph();
		
		gg.drawDGraph();

	}

}