package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	public graph g;


	@Override
	public void init(graph g) {
		this.g = g;		
	}

	@Override
	public void init(String file_name) {
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file);
			g = (DGraph)in.readObject(); 
			in.close(); 
			file.close(); 
			System.out.println("Object has been deserialized");
		} 

		catch(IOException | ClassNotFoundException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 
	}

	@Override
	public void save(String file_name) {	
		try
		{
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(g);
			out.close(); 
			file.close(); 

			System.out.println("Object has been serialized"); 
		}   
		catch(IOException ex) 
		{
			System.out.println("IOException is caught"); 
		}
	}

	@Override
	public boolean isConnected() {
		if(g.nodeSize()==0)
			return true;
		else {
			zeroTags();//zero all the tags
			DFS(0,g.nodeSize());//start at node zero and change tags to 1 in every node it's been through
			boolean ans=checkAllTags();//check if all tags are 1
			if(!ans)//if not it is not connected
				return ans;
			zeroTags();//again zero all tags
			((DGraph) g).reversedGraph();
			DFS(0,g.nodeSize());
			return checkAllTags();
		}
	}

	private boolean checkAllTags() {//the 4'th helper, check if all tags are 1
		Collection<node_data> nod=g.getV();
		for(node_data a:nod) {
			if(a.getTag()!=1)
				return false;
		}
		return true;
	}

	//helper function 2 - start at node 0 and find all his neighbors and their neighbors and so on
	private void DFS(int i,int counter) {
		if(g.getNode(i)==null || counter==0)
			return;
		g.getNode(i).setTag(1);
		counter--;
		System.out.println(i);
		Collection<edge_data> edgesOf = g.getE(i);
		for(edge_data e:edgesOf) 
			DFS(e.getDest(),counter);			
	}


	//helper function 1 - zeroing all tags in all the nodes 
	private void zeroTags() {
		Collection<node_data> n=g.getV();
		for(node_data a: n) {
			a.setTag(0);
		}		
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		String filename = "copyGraph.txt";
		save(filename);
		Graph_Algo newG = new Graph_Algo();
		newG.init(filename);
		return newG.g;
	}

}
