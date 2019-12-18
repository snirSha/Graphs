package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class DGraph implements graph{
	
	private HashMap<Integer, node_data> nodes = new HashMap<>();//1,a    2,b    3,c   4,d    |    data,Node
	private int counterEdges=0;
	private int changes=0;//every change in the graph the counter goes up by one
	
	
	public node_data getNode(int key) {
		return nodes.get(key);
	}

	public edge_data getEdge(int src, int dest) {//????????????
		Node s=(Node) nodes.get(src);
		if(s.getEdgesOf().get(dest)!=null)
			return s.getEdgesOf().get(dest);
		else 
			return null;
	}

	public void addNode(node_data n) {
		changes++;//adding a node
		nodes.put(n.getKey(), n);
	}

	public void connect(int src, int dest, double w) {
		changes++;//adding an edge
		counterEdges++;
		Node s=(Node) nodes.get(src);
		Node d=(Node) nodes.get(dest);
		Edge e=new Edge(s,d,w);
		s.addEdge(e);
	}

	public Collection<node_data> getV() {
		ArrayList<node_data> list=new ArrayList<node_data>();  
		list.addAll(nodes.values());
		return list;
	}

	public Collection<edge_data> getE(int node_id) {
		Node n=(Node) nodes.get(node_id);
		HashMap<Integer,Edge> ee=n.getEdgesOf();
		ArrayList<edge_data> list=new ArrayList<edge_data>();
		list.addAll(ee.values());
		
		return list;
	}

	@Override
	public node_data removeNode(int key) {
		changes--;//removing node
		Node n=(Node) nodes.get(key);//find all the edges that connected to node(key)
		int count=n.getEdgesOf().size();//every edge that we delete the counter goes down by one
		counterEdges-=count;
		n.getEdgesOf().clear();
		
		
		for(node_data a:nodes.values()) {
			Node tmp=(Node)a;
			if(tmp.getEdgesOf().get(key)!=null) {
				tmp.getEdgesOf().remove(key);
				counterEdges--;
			}
		}
		
		return nodes.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		changes--;//removing an edge
		Node n=(Node) nodes.get(src);
		HashMap<Integer,Edge> edgesOfSrc=n.getEdgesOf();
		edge_data ss= edgesOfSrc.remove(dest);
		if(ss!=null) {
			counterEdges--;
			return ss;
		}
		else {
			return null;
		}
	}

	@Override
	public int nodeSize() {
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		return counterEdges;
	}

	@Override
	public int getMC() {
		return changes;
	}

}
