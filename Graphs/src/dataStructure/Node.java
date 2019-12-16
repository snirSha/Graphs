package dataStructure;
import dataStructure.node_data;
import utils.Point3D;

public class Node implements node_data{
	
	private int _data;
	private Point3D _p;
	private double _weight;
	private String _info;
	private int _tag;
	
	
	public Node() {
		this._data=0;
		_p=null;
		_weight=0;
		_tag=0;
		_info="";
	}
	
	public Node(int d,Point3D p, double w, String s, int t) {
		this._data=d;
		this._p=new Point3D(_p);
		this._weight=w;
		this._info=s;
		this._tag=t;
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

}
