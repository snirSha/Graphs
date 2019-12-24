package gui;

import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import java.awt.Color;
import java.util.Collection;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI {
	Graph_Algo ga;
	DGraph g;
	
	public Graph_GUI() {
		ga = new Graph_Algo();
		g = new DGraph();
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

						Point3D arrow = getArrow(Sx,Sy,Dx,Dy);//paint the arrow in red
						StdDraw.setPenRadius(0.02);
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.point(arrow.x(),arrow.y());

						String te = edges.getWeight()+"";

						StdDraw.setPenRadius(0.1);
						StdDraw.setPenColor(Color.BLACK);
						StdDraw.text((Sx+Dx)/2, (Sy+Dy)/2, te);
					}
				}
			}

		}catch(Exception e) {
			System.out.println("No edges to Draw");
		}

	}

	private double getIncline(double x1,double y1,double x2,double y2) {
		if(x1!=x2)
			return (y1-y2)/(x1-x2);
		return Integer.MAX_VALUE;
	}

	private Point3D getArrow(double Sx,double Sy,double Dx,double Dy) {
		double newX,newY;
		double m=getIncline(Sx,Sy,Dx,Dy);
		Point3D ans;
		int dif = 4;
		if(Sx<Dx) {
			newX = Dx-dif;
			newY = m*(newX-Dx)+Dy;
		}
		else if(Sx>Dx) {
			newX = Dx+dif;
			newY = m*(newX-Dx)+Dy;
		}
		else {//Sx==Dx
			if(Dy<Sy) {
				newX=Sx;
				newY=Dy+dif;
			}
			else if(Dy>Sy){
				newX=Sx;
				newY=Dy-dif;
			}			
			else {//Dy==Sy
				newX=Sx;
				newY=Sy;		
			}
		}
		ans=new Point3D(newX,newY);	
		return ans;
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
			if(g.getV().size() != 0) {
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
		Point3D p1=new Point3D(0,50);
		Point3D p2=new Point3D(0,20);
		Point3D p3=new Point3D(-50,15);
		Point3D p4=new Point3D(15,50);
		Point3D p5=new Point3D(10,40);
		Point3D p6=new Point3D(10,30);
		Point3D p7=new Point3D(0,10);
		Point3D p8=new Point3D(-10,2);
		Point3D p9=new Point3D(-50,-1);
		Point3D p10=new Point3D(60,-10);

		Node a0=new Node(p0,0, "", 0);
		Node a=new Node(p1, 0, "", 0);
		Node b=new Node(p2, 0, "", 0);
		Node c=new Node(p3, 0, "", 0);
		Node d=new Node(p4, 0, "", 0);	
		Node e=new Node(p5, 0, "", 0);
		Node f=new Node(p6, 0, "", 0);
		Node j=new Node(p7, 0, "", 0);
		Node h=new Node(p8, 0, "", 0);
		Node i=new Node(p9, 0, "", 0);
		Node k=new Node(p10, 0,"", 0);
		
		gg.addNode(a0);
		gg.addNode(a);
		gg.addNode(b);
		gg.addNode(c);
		gg.addNode(d);
		gg.addNode(e);
		gg.addNode(f);
		gg.addNode(j);
		gg.addNode(h);
		gg.addNode(i);
		gg.addNode(k);


		gg.g.connect(a0.getKey(),a.getKey(),1);
		gg.g.connect(a0.getKey(),b.getKey(), 2);
		gg.g.connect(a0.getKey(),c.getKey(),3);
		gg.g.connect(a.getKey(),d.getKey(), 11);
		gg.g.connect(a.getKey(),e.getKey(),12);
		gg.g.connect(b.getKey(),f.getKey(), 0);
		gg.g.connect(b.getKey(),j.getKey(),1);
		gg.g.connect(c.getKey(),h.getKey(), 4);
		gg.g.connect(c.getKey(), i.getKey(), 5);
		gg.g.connect(d.getKey(), k.getKey(), 14);
		gg.g.connect(e.getKey(),k.getKey(), 0);
		gg.g.connect(f.getKey(),k.getKey(), 2);
		gg.g.connect(j.getKey(),k.getKey(), 15);
		gg.g.connect(h.getKey(),k.getKey(), 20);
		gg.g.connect(i.getKey(),k.getKey(), 35);

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
		Graph_Algo ga = new Graph_Algo();
		ga.init(gg.g);
		System.out.println("shortest path is: "+ga.shortestPathDist(0, 10));
		//gg.drawDGraph();
		
		
		Graph_GUI gg2 = new Graph_GUI();
		gg2.g = (DGraph) ga.copy();
//		System.out.println(ga.isConnected());
//		gg2.g = (DGraph) ga.copy();
		gg2.drawDGraph();
		
	
	}
}


