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
		return s.getEdgesOf().get(dest);
	}

	public void addNode(node_data n) {
		changes++;//adding a node
		nodes.put(n.getKey(), n);
	}

	public void connect(int src, int dest, double w) {
		changes++;//adding an edge
		counterEdges++;
		try{
			Node s=(Node) nodes.get(src);
			Node d=(Node) nodes.get(dest);
			Edge e=new Edge(s,d,w);
			s.addEdge(e);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public Collection<node_data> getV() {
		Collection<node_data> list=new ArrayList<node_data>();  
		list.addAll(nodes.values());
		return list;
	}

	public Collection<edge_data> getE(int node_id) {
		Node n=(Node) nodes.get(node_id);
		HashMap<Integer,Edge> ee=n.getEdgesOf();
		Collection<edge_data> list=new ArrayList<edge_data>();
		list.addAll(ee.values());

		return list;
	}

	@Override
	public node_data removeNode(int key) {
		try {
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
		changes++;//removing node
		return nodes.remove(key);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		
		Node n=(Node) nodes.get(src);
		HashMap<Integer,Edge> edgesOfSrc=n.getEdgesOf();
		edge_data ss= edgesOfSrc.remove(dest);
		if(ss!=null) {
			counterEdges--;
			changes++;//removing an edge
		}
		return ss;
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
