package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



public class DGraph implements Serializable, graph {

	public HashMap<Integer, node_data> nodes;//1,a    2,b    3,c   4,d    |    data,Node
	private int counterEdges;
	private int changes;//every change in the graph the counter goes up by one
	
	public DGraph() {
		nodes = new HashMap<>();
		counterEdges = 0;
		changes = 0;
	}
	
	public DGraph(DGraph other) {//deep copy
		nodes = new HashMap<>();
		for(node_data n: other.nodes.values()) {
			this.nodes.put(n.getKey(), new Node((Node) n));
		}
		this.counterEdges = other.counterEdges;
		this.changes = other.changes;
	}

	public node_data getNode(int key) {
		return nodes.get(key);
	}

	public edge_data getEdge(int src, int dest) {
		if(nodes.containsKey(src) && nodes.containsKey(dest)) {
			Node s=(Node) nodes.get(src);
			return s.getEdgesOf().get(dest);
		}
		System.out.println("src ot dst doesnt exist");
		return null;
	}

	public void addNode(node_data n) {
		Node no = (Node)n;
		if(!nodes.containsKey(no.getKey())) {
			
			changes++;//adding a node
			nodes.put(n.getKey(), n);
		}
		else {
			System.out.println("Node already in the graph");
		}
	}

	public void connect(int src, int dest, double w) {

		if(nodes.containsKey(src) && nodes.containsKey(dest)) {
			Node n = (Node)nodes.get(src);
			if(!n._edges.containsKey(dest)) {
				Node s=(Node) nodes.get(src);
				Edge e=new Edge(src,dest,w);
				s.addEdge(e);
				changes++;//adding an edge
				counterEdges++;
				//System.out.println(src + " -> " + dest);
			}
		}
		else {
			System.out.println("src or dst doesnt exist");
		}
	}

	public Collection<node_data> getV() {
		return nodes.values();
	}

	public Collection<edge_data> getE(int node_id) {
		Collection<edge_data> list=new ArrayList<edge_data>();
		if(nodes.containsKey(node_id)) {
			Node n=(Node) nodes.get(node_id);
			list.addAll(n._edges.values());
		}
		return list;
	}

	@Override
	public node_data removeNode(int key) {

		if(nodes.containsKey(key)) {

			Node n=(Node) nodes.get(key);//find all the edges that connected to node(key)
			int count=n.getEdgesOf().size();//every edge that we delete the counter goes down by one
			counterEdges-=count;
			n.getEdgesOf().clear();

			for(node_data a:nodes.values()) {//remove all 
				Node tmp=(Node)a;
				if(tmp._edges.containsKey(key)) {
					tmp._edges.remove(key);
					counterEdges--;
				}
			}
			changes++;//removing node
			return nodes.remove(key);

		}else {
			System.out.println("key doesnt exist");
			return null;
		}


	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		
		if(nodes.containsKey(src) && nodes.containsKey(dest)) {
			Node n = (Node) nodes.get(src);
			if(n._edges.containsKey(dest)) {
				Node nSrc=(Node) nodes.get(src);
				counterEdges--;
				return nSrc._edges.remove(dest);
//				HashMap<Integer,Edge> edgesOfSrc=nSrc.getEdgesOf();
//				edge_data ss= edgesOfSrc.remove(dest);
//				if(ss!=null) {
//					counterEdges--;
//					changes++;//removing an edge
//				}
//				return ss;
			}
			else {
				System.out.println("Edge doesnt exist");
			}
		}else {
			System.out.println("src or dest doesnt exist");
		}
		return null;
		
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

	public void reversedGraph() {
		zeroEdgeTag();
		for(node_data a: nodes.values()) {
			Node n = (Node)a;
			for(Object e: n._edges.values().toArray()) {

				Edge ed = (Edge)e;
				if(ed.getTag() == 0) {
					connect(ed.getDest(), ed.getSrc(), ed.getWeight());
					Edge ed1 = (Edge)getEdge(ed.getDest(), ed.getSrc());
					ed1.setTag(1);
					removeEdge(ed.getSrc(), ed.getDest());
				}
			}
		}
	}
	private void zeroEdgeTag() {
		Collection<node_data> nod = nodes.values();
		for(node_data a:nod) {
			Node n = (Node)a;
			Collection<Edge> e = n._edges.values();
			for(edge_data ed: e) {
				ed.setTag(0);
			}
		}
	}

}
