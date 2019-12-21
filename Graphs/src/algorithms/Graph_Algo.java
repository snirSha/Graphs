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
	graph g;

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
              
            g = (graph)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Object has been deserialized"); 
            System.out.println(this);
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
	              
	            out.writeObject(this); 
	              
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
		zeroTags();
		boolean a=DFS(0);
		if(!a)
			return a;
		reversedGraph();
		zeroTags();
		return DFS(0);
	}
	
	//helper function1 - start at node 0 and find all his neighbors and their neighbors and so on
	private boolean DFS(int i) {
		Collection<node_data> n=g.getV();
		for(node_data a:n) {
			
		}
		
		return false;
	}
	//helper function2 - copy all nodes and then connect the edges backwards
	private void reversedGraph() {
		DGraph reversedG=new DGraph();
		Collection<node_data> n=g.getV();
		for(node_data a:n) {
			reversedG.addNode(a);
			Collection<edge_data> tmp=g.getE(a.getKey());
			for(edge_data e:tmp) {
				reversedG.connect(e.getDest(), e.getSrc(), e.getWeight());
			}
		}
		g=reversedG;
	}
	//helper function3 - zeroing all tags in all the nodes 
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
		// TODO Auto-generated method stub
		return null;
	}

}
