package gui;

import dataStructure.Node;
import dataStructure.Edge;
import dataStructure.DGraph;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI {
	DGraph g=new DGraph();
	
	public Graph_GUI() {
		StdDraw.setCanvasSize(1000, 1000);
		StdDraw.setXscale(-100,100);
		StdDraw.setYscale(-100,100);
		
	}
	
	public void addNode(Node a) {
		g.addNode(a);
		double x=a.getLocation().x();
		double y=a.getLocation().y();
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(x,y);
	}
	public void addEdge(Node src,Node dest,int w) {	//c055    x1,y1------>x2,y2  m
		g.addNode(src);
		g.addNode(dest);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
	//	double m=getIncline(src.getLocation().ix(),src.getLocation().iy(),dest.getLocation().ix(),dest.getLocation().iy());
	//	StdDraw.setPenColor(StdDraw.RED);
	//	StdDraw.point(dest.getLocation().ix()-1,dest.getLocation().iy()-1);
		
		StdDraw.line(src.getLocation().ix(),src.getLocation().iy(),dest.getLocation().ix(),dest.getLocation().iy());
		
	}
	
	public double getIncline(double x1,double y1,double x2,double y2) {
		if(x1!=x2)
			return (y1-y2)/(x1-x2);
		return Integer.MAX_VALUE;
	}
	
	
	public static void main (String [] args) {
		Graph_GUI gg = new Graph_GUI();
		Point3D p1=new Point3D(80,2);
		Point3D p2=new Point3D(-80,1);
		Node a=new Node(p1, 2.2, "dai", 0);
		Node b=new Node(p2, 2.2, "dai", 0);
		gg.addNode(a);
		gg.addNode(b);
		gg.addEdge(a,b,4);
	}
}


