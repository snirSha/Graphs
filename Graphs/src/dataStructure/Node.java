package dataStructure;
import java.io.Serializable;
import java.util.HashMap;

import dataStructure.node_data;
import utils.Point3D;

public class Node implements node_data, Serializable{

	private static int count = 0;
	public HashMap<Integer, Edge> _edges = new HashMap<>(); //b.key,a->b     c.key,b->c     d.key,c->d     a.key,d->a  |  Edge.dest.key,Edge
	private int _data;
	private Point3D _p;
	private double _weight;
	private String _info;
	private int _tag;
	
	
	public Node() {
		this._data = count;
		count++;
		this._p=null;
		this._weight=0;
		this._tag=0;
		this._info="";
	}
	
	public Node(Point3D p, double w, String s, int t) {
		this._data = count;
		count++;
		this._p=new Point3D(p);
		this._weight=w;
		this._info=s;
		this._tag=t;
	}
	public Node (Node other) {//deep copy
		count++;
		this._edges = new HashMap<>();
		for(Edge e: other.getEdgesOf().values()) {
			this._edges.put(e.getDest(), new Edge(e));
		}
		_data = other._data;
		_p = other._p;
		_weight = other._weight;
		_info = other._info;
		_tag = other._tag;
	}
	
	
	public void addEdge(Edge e) {
		if(this.getKey()==e.getSrc()) {
			_edges.put(e.getDest(), e);
		}
		else
			System.out.println("Wrong edge!!!");
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
		if(p!=null)
			this._p=new Point3D(p);
		else
			System.out.println("There is no location!!!");
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
		return _edges;
	}
}
