package dataStructure;
import dataStructure.edge_data;

public class Edge implements edge_data{
	
	private int _src;
	private int _dest;
	private double _weight;
	private String _info;
	private int _tag;
	
	public Edge() {
		this._src=0;
		this._dest=0;
		this._weight=0;
		this._info="";
		this._tag=0;
	}

	public Edge(int s,int d,double w) {
		this._src=s;  
		this._dest=d;
		this._weight=w;
		this._info="";
		this._tag=0;
	}
	
	public Edge(int s,int d,double w,String str,int t) {
		this._src=s;  
		this._dest=d;
		this._weight=w;
		this._info=str;
		this._tag=t;
	}
	
	@Override
	public int getSrc() {
		return this._src;
	}

	@Override
	public int getDest() {
		return this._dest;
	}

	@Override
	public double getWeight() {
		return this._weight;
	}

	@Override
	public String getInfo() {
		return this._info;
	}

	@Override
	public void setInfo(String s) {
		this._info=s;
	}

	@Override
	public int getTag() {
		return this._tag;
	}

	@Override
	public void setTag(int t) {
		this._tag=t;		
	}

}
