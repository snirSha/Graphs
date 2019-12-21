package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import gui.Graph_GUI;
import utils.StdDraw;


public class DGraph implements graph{

	private static HashMap<Integer, node_data> nodes = new HashMap<>();//1,a    2,b    3,c   4,d    |    data,Node
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
		try{
			if(nodes.containsKey(src) && nodes.containsKey(dest)) {
				Node s=(Node) nodes.get(src);
				Edge e=new Edge(src,dest,w);
				s.addEdge(e);
				changes++;//adding an edge
				counterEdges++;
			}
			else {
				System.out.println("src or dst doesnt exist");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Collection<node_data> getV() {
		return nodes.values();
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
			if(nodes.containsKey(key)) {
					
				Node n=(Node) nodes.get(key);//find all the edges that connected to node(key)
				int count=n.getEdgesOf().size();//every edge that we delete the counter goes down by one
				counterEdges-=count;
				n.getEdgesOf().clear();

				for(node_data a:nodes.values()) {
					Node tmp=(Node)a;
					if(tmp.getEdgesOf().get(key)!=null) {
						tmp.getEdgesOf().remove(key);
					}
				}
				changes++;//removing node
				return nodes.remove(key);
				
			}else {
				System.out.println("key doesnt exist");
				return null;
			}
			
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

	public void reversedGraph1() {
		
		DGraph reversedG=new DGraph();
		Collection<node_data> n=this.getV();
		for(node_data a:n) {
			reversedG.addNode(a);
			Collection<edge_data> tmp=this.getE(a.getKey());
			for(edge_data e:tmp) {
				System.out.println(e.getSrc()+" -> "+e.getDest());

				reversedG.connect(e.getDest(), e.getSrc(), e.getWeight());
			}
		}
	}
}
