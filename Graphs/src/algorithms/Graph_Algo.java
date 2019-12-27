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
import java.util.stream.Collectors;

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
		int ans=isConnectedHelper();
		switch(ans) {
		case 0://true
			return true;
		case 1://failed before reverse
			return false;
		case 2://failed after reverse	
			zeroTags();
			g.reversedGraph();
			return false;
		default:
			return false;
		}
	}

	public int isConnectedHelper() {
		if(g.nodeSize()==0)
			return 0;
		else {
			int x=getFirstNode();
			zeroTags();//zero all the tags
			DFS(x,g.nodeSize());//start at node zero and change tags to 1 in every node it's been through
			boolean ans=checkAllTags();//check if all tags are 1
			if(!ans)//if not it is not connected
				return 1;//failed in the first DFS
			zeroTags();//again zero all tags
			g.reversedGraph();
			DFS(x,g.nodeSize());
			if(checkAllTags())
				return 0;//true in all 
			else {
				return 2;//failed after reverse
			}
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

	//helper function 2 - start at first node and find all his neighbors and their neighbors and so on
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

	private int getFirstNode() {
		for(node_data a:g.getV())
			return a.getKey();
		return 0;
	}

	@Override
	public double shortestPathDist(int src, int dst) {
		zeroTags();
		maxValueWeight();
		Node source = (Node)g.getNode(src);
		source.setWeight(0);
		String str="";
		int areWeInLoop=g.nodeSize();
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
				diakstra(e.getDest(),dst,str+","+src,areWeInLoop,theFirstsrc);
				runner.setTag(1);
			}

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
		int areWeInLoop=g.nodeSize();

		ArrayList<node_data> arr=new ArrayList<>();
		diakstra(src,dest,str,areWeInLoop,src);
		if(g.getNode(dest).getWeight()==shortestPathDist(src,dest)) {

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
			if(targets.size()==1) {
				array.add(g.getNode(targets.get(0)));
				return array;
			}

			array.addAll((shortestPath(targets.get(0),targets.get(1))));
			if(targets.size()==2)
				return array;

			List<node_data> tmp=new ArrayList<>();

			for (int i = 1; i < targets.size()-1; i++) {
				int j=i+1;
				tmp.addAll(shortestPath(targets.get(i),targets.get(j)));
				if((tmp!=null) && checkTargetsInAnswer(targets,tmp) && tmp.containsAll(array)) {
					return tmp;
				}
				else if(tmp!=null && checkTargetsInAnswer(targets,array) && array.containsAll(tmp)) {
					return array;
				}
				else if((tmp!=null)){
					tmp.remove(0);
					array.addAll(tmp);
					tmp.clear();
					if(checkTargetsInAnswer(targets,array))
						return array;
				}
			}
		}
		System.out.println("\nThere is no path between the nodes in 'targets'");
		return null;
	}

	private boolean checkTargetsInAnswer(List<Integer> targets,List<node_data> array) {
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
		Collection<node_data> nod=g.getV();
		for(int a=0;a<targets.size();a++) {//check if there is a integer that repeats itself in targets
			for(int b=0;b<targets.size();b++) {
				if(a!=b&&targets.get(a)==targets.get(b))
					return false;
			}
		}
		int count=0;
		for(int i:targets) {
			for(node_data n:nod) {
				if(i==n.getKey())
					count++;
			}
		}
		return (count==targets.size());
	}


	@Override
	public graph copy() {
		DGraph newG = new DGraph((DGraph) g);
		return newG;
	}
}