package gui;

import dataStructure.Node;

import java.awt.Color;

//import java.util.ArrayList;

import dataStructure.DGraph;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI {
	DGraph g=new DGraph();
//	ArrayList<Node> nodes=new ArrayList<>();
	
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
		StdDraw.setPenColor(StdDraw.BLUE);//nodes in blue
		StdDraw.point(x,y);
	}
	public void addEdge(Node src,Node dest,int w) {	//c055    x1,y1------>x2,y2  m
		double Sx=src.getLocation().x();
		double Sy=src.getLocation().y();
		double Dx=dest.getLocation().x();
		double Dy=dest.getLocation().y();
		
		g.addNode(src);
		g.addNode(dest);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.ORANGE);//paint the line between the nodes in black
		StdDraw.line(Sx,Sy,Dx,Dy);
		
		
		Point3D arrow=getArrow(Sx,Sy,Dx,Dy);//paint the arrow in red
		StdDraw.setPenRadius(0.02);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(arrow.x(),arrow.y());
		
		String te=w+"";

		StdDraw.setPenRadius(0.1);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text((Sx+Dx)/2, (Sy+Dy)/2, te);
	}
	
	private double getIncline(double x1,double y1,double x2,double y2) {
		if(x1!=x2)
			return (y1-y2)/(x1-x2);
		return 0;
	}
	private Point3D getArrow(double Sx,double Sy,double Dx,double Dy) {
		double newX,newY;
		double m=getIncline(Sx,Sy,Dx,Dy);
		Point3D ans;
		if(Sx<Dx) {
			newX = Dx-1;
			newY = m*(newX-Dx)+Dy;
		}
		else if(Sx>Dx) {
			newX = Dx+1;
			newY = m*(newX-Dx)+Dy;
		}
		else {
			if(Dy<0) {
				newX=0;
				newY=Dy+1;
			}
			else {
				newX=0;
				newY=Dy-1;
			}			
		}
		ans=new Point3D(newX,newY);	
		return ans;
	}
	
	
	public static void main (String [] args) {
		Graph_GUI gg = new Graph_GUI();
		Point3D p1=new Point3D(0,0);
		Point3D p2=new Point3D(50,50);
		Point3D p3=new Point3D(-50,-50);
		Point3D p4=new Point3D(-50,50);
		Point3D p5=new Point3D(50,-50);
		Point3D p6=new Point3D(0,50);
		Point3D p7=new Point3D(50,0);
		Point3D p8=new Point3D(0,-50);
		Point3D p9=new Point3D(-50,0);
		
		Node a=new Node(p1, 0, "dai", 0);
		Node b=new Node(p2, 0, "dai", 0);
		Node c=new Node(p3, 0, "dai", 0);
		Node d=new Node(p4, 0, "dai", 0);	
		Node e=new Node(p5, 0, "dai", 0);
		Node f=new Node(p6, 0, "dai", 0);
		Node g=new Node(p7, 0, "dai", 0);
		Node h=new Node(p8, 0, "dai", 0);
		Node i=new Node(p9, 0, "dai", 0);
		
		gg.addNode(a);
		gg.addNode(b);
		gg.addNode(c);
		gg.addNode(d);
		gg.addNode(e);
		gg.addNode(f);
		gg.addNode(g);
		gg.addNode(h);
		gg.addNode(i);
				
		gg.addEdge(a,b,4);
		gg.addEdge(a,c,5);
		gg.addEdge(a,d,7);
		gg.addEdge(a,e,2);
		gg.addEdge(a,f,1);
		gg.addEdge(a,g,9);
		gg.addEdge(a,h,2);
		gg.addEdge(a,i,3);

	}
}


