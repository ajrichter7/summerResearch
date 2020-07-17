import java.io.*;
import java.util.*;

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class HyperGraph {
	public ArrayList<HyperNode>nodes; 
	public ArrayList<HyperEdge>edges; 
	
	/**
	 * Constructor that makes a new HyperGraph 
	 */
	public HyperGraph() {
		nodes = new ArrayList<HyperNode>();
		edges = new ArrayList<HyperEdge>(); 
	}
	
	/**
	 * Returns a list of HyperNode object created by the constructor. 
	 *  
	 * @return nodes
	 */
	public ArrayList<HyperNode> getNodes(){
		return nodes; 
	}
	
	/**
	 * Returns a list of HyperEdge objects created by the constructor. 
	 * 
	 * @return edges
	 */
	public ArrayList<HyperEdge> getEdges(){
		return edges; 
	}
	
	/**
	 * Returns the HyperNode object with the associated String ID, if it is contained in the 
	 * node set. 
	 * 
	 * @param s String ID for a HyperNode 
	 * @return HyperNode object associated with the string or null 
	 */
	public HyperNode gethyperNode(String s) {
		for (HyperNode n : nodes) {
			if (n.getId() == s)
				return n; 
			}
		return null; 
	}
	
	/**
	 * Prints out the string ID associated with each node in the HyperGraph. 
	 */
	public void printNodes() {
		for (HyperNode i : nodes) {
			System.out.println(i.getId());
		}
		System.out.println();
	}
	
	/**
	 * Prints out all of the edges contained in a HyperGraph. 
	 * <p>
	 * Prints out the edges in this format, displaying the head and tail sets for each edge.
	 * <p> HyperEdge@memorylocation 
	 * <p> Tail : list of hyperNodes in the tail (will return string associated with each node)
	 * <p> Head : list of hyperNodes in the head (will return string associated with each node) 
	 */
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
	
	/**
	 * Prints out the edges in the HyperGraph. 
	 * <p> 
	 * Does not print out the tail and head sets for each HyperEdge. The format for 
	 * printing returns: 
	 * 
	 * <p> Edge number : HyperEdge@memorylocation
	 */
	public void printhyperEdgeObjects() {
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Edge " + i + " : " + edges.get(i));
		}
		System.out.println();
	}
	
	/**
	 * Prints out the edges leaving a specific HyperNode given a nodeId associated with a HyperNode. 
	 * <p> 
	 * Prints out all of the HyperEdges where the node is contained in the tail set for a given HyperEdge. 
	 * The format for printing for some HyperNode X with the associated nodeID: 
	 * 
	 * <p> HyperEdges with HyperNode X in the tail
	 * <p> Edge: HyperEdge@memorylocation (or edge number #) contains hyperNode X
	 * 
	 * <p> If there are no HyperEdges with HyperNode X in the tail set, it prints: 
	 * There are no HyperEdges with HyperNode X in the tail. 
	 * 
	 * @param nodeID String associated with a specific HyperNode 
	 */
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
			System.out.println("There are no HyperEdges with HyperNode " + nodeID + " in the tail.");
		}
		System.out.println();
	}
	
	/**
	 * Returns all of the edges that contain the HyperNode with the associated nodeID in the tail. 
	 * 
	 * @param nodeID String associated with a specific HyperNode 
	 * @return Set of HyperEdges that contain the HyperNode in the tail 
	 */
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
	
	/**
	 * Prints out the edges entering a specific HyperNode given a nodeId associated with a HyperNode. 
	 * <p> 
	 * Prints out all of the HyperEdges where the node is contained in the head set for a given HyperEdge. 
	 * The format for printing for some HyperNode X with the associated nodeID: 
	 * 
	 * <p> HyperEdges with HyperNode X in the head
	 * <p> Edge: HyperEdge@memorylocation (or edge number #) contains hyperNode X.
	 * <p>
	 * If there are no HyperEdges with HyperNode X in the head set, it prints: 
	 * There are no HyperEdges with HyperNode X in the head. 
	 * 
	 * @param nodeID String associated with a specific HyperNode 
	 */
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
	
	/**
	 * Returns all of the edges that contain the HyperNode with the associated nodeID in the head. 
	 * 
	 * @param nodeID String associated with a specific HyperNode 
	 * @return Set of HyperEdges that contain the HyperNode in the head 
	 */
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

	/**
	 * Adds a HyperNode to a HyperGraph. 
	 * 
	 * @param Id String associated with the HyperNode 
	 */
	public void addhyperNode(String Id) {
		HyperNode node = new HyperNode(Id); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	
	/**
	 * Adds a HyperNode that has a set of attributes to a HyperGraph. 
	 * 
	 * @param Id String associated with the HyperNode 
	 * @param attributes Map of strings associated with the HyperNode to be added
	 */
	public void addhyperNode(String Id, Map<String, String> attributes) {
		HyperNode node = new HyperNode(Id, attributes); 
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	
	/**
	 * Creates a new HyperEdge that will be added to the HyperGraph method called on. 
	 * 
	 * @param tail HashSet of HyperNodes 
	 * @param head HashSet of HyperNodes 
	 */
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
