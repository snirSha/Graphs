package dataStructure;
import java.util.HashMap;

import dataStructure.node_data;
import utils.Point3D;

public class Node implements node_data{

	private HashMap<Integer, Edge> edgesOf= new HashMap<>(); //b.key,a->b     c.key,b->c     d.key,c->d     a.key,d->a  |  Edge.dest.key,Edge
	
	private int _data=0;
	private Point3D _p;
	private double _weight;
	private String _info;
	private int _tag;
	
	
	public Node() {
		this._p=null;
		this._weight=0;
		this._tag=0;
		this._info="";
	}
	
	public Node(Point3D p, double w, String s, int t) {
		this._data++;
		this._p=new Point3D(p);
		this._weight=w;
		this._info=s;
		this._tag=t;
	}
	
	public Node(Node other) {
		this._data=other._data;
		this._p=other._p;
		this._weight=other._weight;
		this._tag=other._tag;
		this._info=other._info;
	}
	
	public void addEdge(Edge e) {
		if(this.getKey()==e.getSrc())
			edgesOf.put(e.getDest(), e);
		else
			System.out.println("Wrong value!!!");
	}
	
	@Override
	public int getKey() {
		return _data;
	}

	@Override
	public Point3D getLocation() {
		return _p;
	}

	@Override
	public void setLocation(Point3D p) {
		this._p=new Point3D(p);
	}

	@Override
	public double getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(double w) {
		this._weight=w;
	}

	@Override
	public String getInfo() {
		return _info;
	}

	@Override
	public void setInfo(String s) {
		this._info=s;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int t) {
		this._tag=t;
	}
	
	public HashMap<Integer,Edge> getEdgesOf(){
		return edgesOf;
	}
}