import java.io.*;
import java.util.*;

public class hyperGraph {
	public ArrayList<hyperNode>nodes; 
	public ArrayList<hyperEdge>edges; 
	
	//constructor 
	public hyperGraph() {
		nodes = new ArrayList<hyperNode>();
		edges = new ArrayList<hyperEdge>(); 
	}
	
	//method to get the list of hyperNodes in the hyperGraph
	public ArrayList<hyperNode> getNodes(){
		return nodes; 
	}
	
	//method to get the list of hyperEdges in the hyperGraph
	public ArrayList<hyperEdge> getEdges(){
		return edges; 
	}
	
	// method used to get the hyperNode object if ID in a subset of nodes 
	public hyperNode gethyperNode(String s) {
		for (hyperNode n : nodes) {
			if (n.getId() == s)
				return n; 
			}
		return null; 
	}
	
	//method to print out the ID (or string associated with) each node in the hyperGraph
	public void printNodes() {
		for (hyperNode i : nodes) {
			System.out.println(i.getId());
		}
		System.out.println();
	}
	
	//method to print out ALL of the edges in the graph 
	//		format
	//	-------------
	//hyperEdge@memorylocation
	//Tail : list of hyperNodes in the tail (will return string associated with each node) 
	//Head : list of hyperNodes in the head (will return string associated with each node) 
	public void printEdges() {
		for (hyperEdge i : edges) {
			System.out.println(i);
			HashSet<String> tail = new HashSet<String>();
			HashSet<String> head = new HashSet<String>();
			for (hyperNode j : i.getTail()) {
				tail.add(j.getId());
			}
			for (hyperNode j : i.getHead()) {
				head.add(j.getId()); 
			}
			System.out.println("Tail : " + tail);
			System.out.println("Head : " + head);
			System.out.println();
		}
	}
	
	//method to print out edge objects in the class 
	//		format
	//	-------------
	//Edge # : hyperEdge@memorylocation
	public void printhyperEdgeObjects() {
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Edge " + i + " : " + edges.get(i));
		}
		System.out.println();
	}
	
	//method to print out the edges leaving a specific hyperNode (ie will print out all the hyperEdges where node c tail)
	//		format
	//	-------------
	// "hyperEdges with hyperNode X in the tail"
	//Edge: hyperEdge@memorylocation (or edge number #) contains hyperNode X. 
	// 
	// Note: if no hyperEdges contain X in the tail set, then it will print:
	// "There are no hyperEdges with hyperNode X in the tail."
	public void printOutgoingEdges(String nodeID) {
		System.out.println("hyperEdges with hyperNode " + nodeID + " in the tail.");
		int count = 0; 
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getTail()) {
				if (j.getId() == nodeID) {
					System.out.println("Edge: " + e + " (or edge number " + i + ") contains hyperNode " + nodeID);
					count ++; 
				}
			}
		}if (count == 0) {
			System.out.println("There are no hyperEdges with hyperNode " + nodeID + " in the tail.");
		}
		System.out.println();
	}
	
	//method to return all of the edges that contain hyperNode X in the tail as a HashSet<hyperEdge> 
	public HashSet<hyperEdge> getOutgoingEdges(String nodeID){
		HashSet<hyperEdge> outEdges = new HashSet<hyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getTail()) {
				if (j.getId() == nodeID) {
					outEdges.add(e);
				}
			}
		}
		return outEdges; 
	}
	
	//method to print out the edges entering a specific hyperNode (ie will print out all the hyperEdges where node c head)
	//		format
	//	-------------
	// "hyperEdges with hyperNode X in the head"
	//Edge: hyperEdge@memorylocation (or edge number #) contains hyperNode X. 
	// 
	// Note: if no hyperEdges contain X in the head set, then it will print:
	// "There are no hyperEdges with hyperNode X in the head."	
	public void printIncomingEdges(String nodeID) {
		System.out.println("hyperEdges with hyperNode " + nodeID + " in the head.");
		int count = 0; 
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getHead()) {
				if (j.getId() == nodeID) {
					System.out.println("Edge: " + e + " (or edge number " + i + ") contains hyperNode " + nodeID);
					count ++; 
				}
			}
		}if (count == 0) {
			System.out.println("There are no hyperEdges with hyperNode " + nodeID + " in the head.");
		}
		System.out.println();
	}
	
	//method to return all of the edges that contain hyperNode X in the head as a HashSet<hyperEdge> 
	public HashSet<hyperEdge> getIncomingEdges(String nodeID){
		HashSet<hyperEdge> inEdges = new HashSet<hyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			hyperEdge e = edges.get(i);
			for (hyperNode j : e.getHead()) {
				if (j.getId() == nodeID) {
					inEdges.add(e);
				}
			}
		}
		return inEdges; 		
	}
	
	//method to build the hyperGraph outlined in Figure 8.
	public void buildhyperGraph() {
		List<String> ls = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R");
		for (String i : ls) {
			addhyperNode(i);  
		}
		
		HashSet<hyperNode> tail = new HashSet<hyperNode>(); 
		HashSet<hyperNode> head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(0));
		head.add(nodes.get(2));
		addhyperEdge(tail, head); 
		
		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(0));
		tail.add(nodes.get(1));
		head.add(nodes.get(3));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(1));
		head.add(nodes.get(4));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(1));
		head.add(nodes.get(5));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(4));
		tail.add(nodes.get(5));
		head.add(nodes.get(8));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(3));
		tail.add(nodes.get(6));
		head.add(nodes.get(11));
		addhyperEdge(tail, head);  

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(3));
		tail.add(nodes.get(7));
		head.add(nodes.get(12));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(8));
		tail.add(nodes.get(13));
		head.add(nodes.get(14));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(12));
		tail.add(nodes.get(13));
		head.add(nodes.get(16));
		addhyperEdge(tail, head); 

		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(11));
		tail.add(nodes.get(9));
		tail.add(nodes.get(10));
		head.add(nodes.get(15));
		addhyperEdge(tail, head); 
		
		tail = new HashSet<hyperNode>(); 
		head = new HashSet<hyperNode>();
		
		tail.add(nodes.get(15));
		tail.add(nodes.get(16));
		head.add(nodes.get(17));
		addhyperEdge(tail, head); 
	}
	
	//method to add a hyperNode object to a hyperGraph 
	public void addhyperNode(String Id) {
		hyperNode node = new hyperNode(Id); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	//method to add a hyperNode object to a hyperGraph with attributes
	public void addhyperNode(String Id, String[] attributes) {
		hyperNode node = new hyperNode(Id, attributes); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	
	//method to add new hyperEdge object to a hyperGraph 
	public void addhyperEdge(HashSet<hyperNode> tail, HashSet<hyperNode> head){
		hyperEdge edge = new hyperEdge();
		edge.setHead(head);
		edge.setTail(tail);
		edges.add(edge); 
		for (hyperNode i : tail) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
		for (hyperNode i : head) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
	}
	
}
