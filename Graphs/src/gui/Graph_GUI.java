package gui;

import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI {
	Graph_Algo ga;
	DGraph g;


	public Graph_GUI() {
		ga = new Graph_Algo();
		g = new DGraph();
		ga.init(g);
	}

	public Graph_GUI(graph g) {
		this.g = (DGraph) g;
		ga.init(g);
	}

	public void addNode(Node a) {
		g.addNode(a);
	}

	public void drawNodes() {

		try {
			Collection<node_data> n=g.getV();
			for (node_data a:n) {
				double x=a.getLocation().x();
				double y=a.getLocation().y();
				StdDraw.setPenRadius(0.05);
				StdDraw.setPenColor(StdDraw.BLUE);//nodes in blue
				StdDraw.point(x,y);
				StdDraw.setPenColor(StdDraw.BLACK);
				String abs=a.getKey()+"";
				StdDraw.text(x,y,abs);
			}
		}catch(Exception e) {
			System.out.println("No nodes to draw");
		}

	}

	public void drawEdges() {

		try {

			Collection<node_data> allNodes=g.getV();
			if(allNodes != null) {
				for(node_data n:allNodes) {
					Collection<edge_data> allEdgesOfNode=g.getE(n.getKey());
					for(edge_data edges:allEdgesOfNode) {
						double Sx = g.getNode(edges.getSrc()).getLocation().x();
						double Sy = g.getNode(edges.getSrc()).getLocation().y();
						double Dx = g.getNode(edges.getDest()).getLocation().x();
						double Dy = g.getNode(edges.getDest()).getLocation().y();

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

		}catch(Exception e) {
			System.out.println("No edges to Draw");
		}

	}


	public void removeNode(int x) {
		g.removeNode(x);
	}
	public void removeEdge(int x,int y) {
		g.removeEdge(x,y);
	}

	public void reversedGraph() {
		((DGraph) g).reversedGraph();
	}

	public void drawDGraph() {
		try {
			if(g.getV() != null && g.getV().size() != 0) {
				StdDraw.setCanvasSize(1000, 1000);
				StdDraw.setXscale(-100,100);
				StdDraw.setYscale(-100,100);

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
	public void deleteGraph() {
		StdDraw.clear();

	}

	public static void main (String [] args) {
		Graph_GUI gg = new Graph_GUI();
		Point3D p0=new Point3D(-50,50);
		Point3D p1=new Point3D(50,0);
		Point3D p2=new Point3D(-50,0);
		Point3D p3=new Point3D(50,50);
		//		Point3D p4=new Point3D(0,50);
		//		Point3D p5=new Point3D(10,40);
		//		Point3D p6=new Point3D(10,30);
		//		Point3D p7=new Point3D(0,10);
		//		Point3D p8=new Point3D(-10,2);
		//		Point3D p9=new Point3D(-50,-1);
		//		Point3D p10=new Point3D(60,-10);

		Node a=new Node(p0,0, "", 0);
		Node b=new Node(p1, 0, "", 0);
		Node c=new Node(p2, 0, "warryetj", 0);
		Node d=new Node(p3, 0, "", 0);
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
		//		gg.addNode(d);
		//		gg.addNode(e);
		//		gg.addNode(f);
		//		gg.addNode(j);
		//		gg.addNode(h);
		//		gg.addNode(i);
		//		gg.addNode(k);


		gg.g.connect(a.getKey(),b.getKey(),4);
		gg.g.connect(a.getKey(),d.getKey(), 1);
		gg.g.connect(b.getKey(),c.getKey(),2);
		gg.g.connect(c.getKey(),d.getKey(), 3);
		gg.g.connect(d.getKey(), b.getKey(), 1);
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

		gg.removeEdge(0, 1);

		gg.removeNode(1);
		gg.removeNode(8);
//		gg.removeNode(0);
//		 * */
		//		System.out.println(gg.g.nodeSize());
		//		System.out.println(gg.g.edgeSize());
		//		gg.removeNode(0);
		//		System.out.println(gg.g.edgeSize());
		//		System.out.println(gg.g.nodeSize());
		//		System.out.println(gg.g.edgeSize());
		//gg.removeEdge(0, 2);
		//System.out.println(gg.g.edgeSize());
		//StdDraw.clear();
		//gg.reversedGraph();


		/*
		 * check shortestPathDist and shortestPath
		 */
		gg.drawDGraph();

		gg.ga.init(gg.g);
		System.out.println("shortest path is: "+gg.ga.shortestPathDist(3, 0));
		System.out.println("isConnected: "+gg.ga.isConnected());
		ArrayList<node_data> ans=(ArrayList<node_data>) gg.ga.shortestPath(3,0);
		if(ans!=null) {
			System.out.println("List of nodes: ");
			for(node_data ar:ans) {
				System.out.print(ar.getKey()+",");
			}
		}
		else {
			System.out.println("null");
		}



		/*
		 * check TSP
		 */
				gg.ga.init(gg.g);
				List<Integer> targets=new ArrayList<>();
				targets.add(3);
				targets.add(1);
				targets.add(0);
				List<node_data> answer=gg.ga.TSP(targets);
				System.out.println("\nThe TSP List is:");
				if(answer==null)
					System.out.println("null");
				else {
					for(node_data l:answer)
						System.out.print(l.getKey()+",");
				}

		/*
		 * init,save check
		 */
		//		Graph_GUI gg2 = new Graph_GUI();
		//		gg2.g = (DGraph) gg.ga.copy();
		//		gg2.drawDGraph();
		//		System.out.println(gg2.g.getE(0).size());
		//
		//		gg2.ga.save("testgg2");
		//		Graph_Algo ga3 = new Graph_Algo();
		//		ga3.init("testgg2");
		//		System.out.println(ga3.g.getE(0).size());






	}
}