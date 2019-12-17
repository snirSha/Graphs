package dataStructure;
import dataStructure.edge_data;

public class Edge implements edge_data{
	
	private Node _src;
	private Node _dest;
	private double _weight;
	private String _info;
	private int _tag;
	
	public Edge() {
		this._src=null;
		this._dest=null;
		this._weight=0;
		this._info="";
		this._tag=0;
	}

	public Edge(Node s,Node d,double w) {
		this._src=new Node(s);  
		this._dest=new Node(d);
		this._weight=w;
		this._info="";
		this._tag=0;
	}
	
	public Edge(Node s,Node d,double w,String str,int t) {
		this._src=new Node(s);  
		this._dest=new Node(d);
		this._weight=w;
		this._info=str;
		this._tag=t;
	}
	
	@Override
	public int getSrc() {
		return this._src.getKey();
	}

	@Override
	public int getDest() {
		return this._dest.getKey();
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
