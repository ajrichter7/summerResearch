import java.io.*;
import java.util.*;

public class HyperGraph {
	public ArrayList<HyperNode>nodes; 
	public ArrayList<HyperEdge>edges; 
	
	//constructor 
	public HyperGraph() {
		nodes = new ArrayList<HyperNode>();
		edges = new ArrayList<HyperEdge>(); 
	}
	
	//method to get the list of hyperNodes in the hyperGraph
	public ArrayList<HyperNode> getNodes(){
		return nodes; 
	}
	
	//method to get the list of hyperEdges in the hyperGraph
	public ArrayList<HyperEdge> getEdges(){
		return edges; 
	}
	
	// method used to get the hyperNode object if ID in a subset of nodes 
	public HyperNode gethyperNode(String s) {
		for (HyperNode n : nodes) {
			if (n.getId() == s)
				return n; 
			}
		return null; 
	}
	
	//method to print out the ID (or string associated with) each node in the hyperGraph
	public void printNodes() {
		for (HyperNode i : nodes) {
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
		for (HyperEdge i : edges) {
			System.out.println(i);
			HashSet<String> tail = new HashSet<String>();
			HashSet<String> head = new HashSet<String>();
			for (HyperNode j : i.getTail()) {
				tail.add(j.getId());
			}
			for (HyperNode j : i.getHead()) {
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
			HyperEdge e = edges.get(i);
			for (HyperNode j : e.getTail()) {
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
	public HashSet<HyperEdge> getOutgoingEdges(String nodeID){
		HashSet<HyperEdge> outEdges = new HashSet<HyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			HyperEdge e = edges.get(i);
			for (HyperNode j : e.getTail()) {
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
			HyperEdge e = edges.get(i);
			for (HyperNode j : e.getHead()) {
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
	public HashSet<HyperEdge> getIncomingEdges(String nodeID){
		HashSet<HyperEdge> inEdges = new HashSet<HyperEdge>();
		for (int i = 0 ; i < edges.size(); i ++) {
			HyperEdge e = edges.get(i);
			for (HyperNode j : e.getHead()) {
				if (j.getId() == nodeID) {
					inEdges.add(e);
				}
			}
		}
		return inEdges; 		
	}

	
	//method to add a hyperNode object to a hyperGraph 
	public void addhyperNode(String Id) {
		HyperNode node = new HyperNode(Id); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	//method to add a hyperNode object to a hyperGraph with attributes
	public void addhyperNode(String Id, Map<String, String> attributes) {
		HyperNode node = new HyperNode(Id, attributes); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	
	//method to add new hyperEdge object to a hyperGraph 
	public void addhyperEdge(HashSet<HyperNode> tail, HashSet<HyperNode> head){
		HyperEdge edge = new HyperEdge();
		edge.setHead(head);
		edge.setTail(tail);
		edges.add(edge); 
		for (HyperNode i : tail) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
		for (HyperNode i : head) {
			if (!nodes.contains(i)) {
				nodes.add(i);
			}
		}
	}
	
}
