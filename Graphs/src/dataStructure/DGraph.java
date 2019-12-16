package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	
	HashMap<Integer, node_data> hm = new HashMap<>();
	
	public node_data getNode(int key) {
		return hm.get(key);
	}

	public edge_data getEdge(int src, int dest) {
		
		return null;
	}

	public void addNode(node_data n) {
		hm.put(n.getKey(), n);
		
	}

	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		
	}

	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
