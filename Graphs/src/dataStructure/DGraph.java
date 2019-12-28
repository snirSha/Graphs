package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



public class DGraph implements graph, Serializable {


	private HashMap<Integer, node_data> nodes;//1,a    2,b    3,c   4,d    |    data,Node
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
			if(s.getEdgesOf().get(dest)!=null)
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

		if(nodes.containsKey(src) && nodes.containsKey(dest) && (src!=dest)) {
			Node n = (Node)nodes.get(src);
			if(!n.getEdgesOf().containsKey(dest)) {
				Node s=(Node) nodes.get(src);
				Edge e=new Edge(src,dest,w);
				s.addEdge(e);
				changes++;//adding an edge
				counterEdges++;
			}
		}
		else {
			System.out.println("src or dst doesnt exist OR src equals to dest");
		}
	}

	public Collection<node_data> getV() {
		return nodes.values();
	}

	public Collection<edge_data> getE(int node_id) {
		Collection<edge_data> list=new ArrayList<edge_data>();
		if(nodes.containsKey(node_id)) {
			Node n=(Node) nodes.get(node_id);
			list.addAll(n.getEdgesOf().values());
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
				if(tmp.getEdgesOf().containsKey(key)) {
					tmp.getEdgesOf().remove(key);
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
			if(n.getEdgesOf().containsKey(dest)) {
				Node nSrc=(Node) nodes.get(src);
				counterEdges--;
				changes++;
				return nSrc.getEdgesOf().remove(dest);

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
		int saveTheChanges=changes;
		zeroEdgeTag();
		Collection<node_data> nod=getV();
		for(node_data a: nod) {
			Collection<edge_data> edges=getE(a.getKey());
			for(edge_data e: edges) {
				if(!isBidirectional(e.getSrc(),e.getDest())){
					Edge ed = (Edge)e;
					if(ed.getTag() == 0) {
						connect(ed.getDest(), ed.getSrc(), ed.getWeight());
						Edge ed1 = (Edge)getEdge(ed.getDest(), ed.getSrc());
						ed1.setTag(1);
						removeEdge(ed.getSrc(), ed.getDest());
					}
				}
				else {//if the edge is bidirectional -> change only the weight between the edges
					Edge e1=(Edge)e;
					Node nDst=(Node)getNode(e.getDest());
					Edge e2=nDst.getEdgesOf().get(e.getSrc());
					if(e1.getTag()==0 && e2.getTag()==0) {
						double tmp=e1.getWeight();
						e1.setWeight(e2.getWeight());
						e2.setWeight(tmp);
						e1.setTag(1);
					}
				}
			}
		}
		changes=saveTheChanges+1;
	}

	private boolean isBidirectional(int src,int dst) {
		Node nDst=(Node)getNode(dst);
		if(nDst.getEdgesOf().containsKey(src))
			return true;
		else
			return false;
	}

	private void zeroEdgeTag() {
		Collection<node_data> nod = nodes.values();
		for(node_data a:nod) {
			Node n = (Node)a;
			Collection<Edge> e = n.getEdgesOf().values();
			for(edge_data ed: e) {
				ed.setTag(0);
			}
		}
	}

}