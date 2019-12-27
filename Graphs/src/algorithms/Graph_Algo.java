package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dataStructure.DGraph;
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
	public DGraph g;

	public Graph_Algo() {
		this.g = new DGraph();
	}

	@Override
	public void init(graph g) {
		this.g = (DGraph) g;		
	}

	@Override
	public void init(String file_name) {
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file);
			this.g = (DGraph)in.readObject(); 
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
			out.writeObject(this.g);
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
		boolean ans=isConnectedHelper();
		zeroTags();
		((DGraph) g).reversedGraph();
		return ans;
	}
	public boolean isConnectedHelper() {
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
	public double shortestPathDist(int src, int dst) {
		zeroTags();
		maxValueWeight();
		Node source = (Node)g.getNode(src);
		source.setWeight(0);
		String str="";
		int areWeInLoop = g.nodeSize();
		diakstra(src,dst,str,areWeInLoop,src);
		if(g.getNode(dst).getWeight()!=Double.MAX_VALUE)
			return g.getNode(dst).getWeight();
		else {
			System.out.println("There is no path between src and dst");
			return -1;
		}
	}

	public void diakstra(int src,int dst,String str,int areWeInLoop, int theFirstsrc) {
		Node runner=(Node) g.getNode(src);
		if(src==theFirstsrc)
			areWeInLoop--;
		if((runner.getTag()==1 && dst==src )||(areWeInLoop<0)) {
			return;
		}

		Collection<edge_data> edges=g.getE(src);
		for(edge_data e:edges) {
			double newWeight=runner.getWeight()+e.getWeight();
			double oldWeight=g.getNode(e.getDest()).getWeight();
			if(newWeight<oldWeight) {
				g.getNode(e.getDest()).setWeight(newWeight);
				g.getNode(e.getDest()).setInfo(str+","+src);
			}
			runner.setTag(1);
			diakstra(e.getDest(),dst,str+","+src,areWeInLoop,theFirstsrc);
		}
	}


	private void maxValueWeight() {//helper function to shortestPathDist
		Collection<node_data> nodes = g.getV();
		for(node_data a: nodes)
			a.setWeight(Double.MAX_VALUE);
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		String str="";
		int k;
		zeroTags();
		maxValueWeight();
		Node source = (Node)g.getNode(src);
		source.setWeight(0);
		int areWeInLoop = g.nodeSize();

		ArrayList<node_data> arr=new ArrayList<>();

		diakstra(src,dest,str,areWeInLoop,src);
		if(g.getNode(dest).getWeight()!=Double.MAX_VALUE) {

			String ans =g.getNode(dest).getInfo();
			ans=ans.substring(1);
			String[] strArray=ans.split(",");
			for (int i = 0; i < strArray.length; i++) {
				k=Integer.parseInt(strArray[i]);
				node_data tmp=g.getNode(k);
				arr.add(tmp);
			}
			arr.add(g.getNode(dest));
			return arr;
		}
		else {
			System.out.println("There is no path between src and dst");
			return null;
		}
	}
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if((!targets.isEmpty()) && (targets.size()<=g.nodeSize()) && (checkTargetsInGraph(targets)) && isConnected()) {

			List<node_data> array=new ArrayList<>();

			for(Integer i:targets) {
				for(Integer j:targets) {
					if(i!=j) {
						array=shortestPath(i,j);
						if((array!=null) && checkEquals(targets,array))
							return array;
					}
				}
			}
		}
		return null;
	}
	private boolean checkEquals(List<Integer> targets,List<node_data> array) {
		int counter=0;
		for(Integer i:targets) {
			for(node_data n:array) {
				if(i==n.getKey()) {
					counter++;
					break;
				}
			}
		}
		return (counter==targets.size());
	}

	private boolean checkTargetsInGraph(List<Integer> targets) {
		int count=0;
		Collection<node_data> nod=g.getV();
		for(Integer i:targets) {
			for(node_data n:nod) {
				if(i==n.getKey())
					count++;
			}
		}
		return (count==targets.size());
	}


	@Override
	public graph copy() {
		DGraph newG = new DGraph(g);
		return newG;
	}
}