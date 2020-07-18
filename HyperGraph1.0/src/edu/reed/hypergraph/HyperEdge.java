package edu.reed.hypergraph;
import java.util.*;

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class HyperEdge {
	
	private HashSet<HyperNode>tail; 
	private HashSet<HyperNode>head;
	
	/**
	 * Constructor for HyperEdge object. 
	 */
	public HyperEdge() {
		this.tail = new HashSet<HyperNode>();
		this.head = new HashSet<HyperNode>();
	}
	
	/**
	 * Returns the tail set for a HyperEdge. 
	 * @return tail HashSet of HyperNodes 
	 */
	public HashSet<HyperNode> getTail() {
		return this.tail; 
	}
	
	/**
	 * Returns the head set for a HyperEdge. 
	 * @return head HashSet of HyperNodes 
	 */
	public HashSet<HyperNode> getHead() {
		return this.head;
	}
	
	/**
	 * Gets the head set for a given HyperEdge. 
	 * @param head HashSet of HyperNodes
	 */
	//method to get the head set for a given hyperEdge 
	public void setHead(HashSet<HyperNode>head) {
		this.head = head;
	}
	
	/**
	 * Gets the tail set for a given HyperEdge. 
	 * @param tail HashSet of HyperNodes 
	 */
	public void setTail(HashSet<HyperNode>tail) {
		this.tail = tail; 
	}
	
	/**
	 * Adds a HyperNode to the tail set for a HyperEdge. 
	 * @param node HyperNode to add
	 */
	public void addToTail(HyperNode node) {
		this.tail.add(node);
	}
	
	/**
	 * Adds a HyperNode to the head set for a HyperEdge. 
	 * @param node HyperNode to add
	 */
	public void addToHead(HyperNode node) {
		this.head.add(node); 
	}
	
	/**
	 * Removes a HyperNode from the tail set for a HyperEdge. 
	 * @param node HyperNode to remove
	 */
	public void removeFromTail(HyperNode node) {
		this.tail.remove(node); 
	}
	
	/**
	 * Removes a HyperNode from the head set for a HyperEdge. 
	 * @param node HyperNode to remove
	 */
	public void removeFromHead(HyperNode node) {
		this.head.remove(node); 
	}
	
	/**
	 * Returns the size of the tail set.  
	 * @return int size of the tail set
	 */
	public int tailSize() {
		return this.tail.size(); 
	}
	
	/**
	 * Returns the size of the head set.  
	 * @return int size of the head set
	 */
	public int headSize() {
		return this.head.size(); 
	}
	
	/**
	 * Prints out the specific HyperEdge that method is called on. 
	 * <p> 
	 * The format for printing the specific HyperEdge appears as followed: 
	 * <p> HyperEdge@memorylocation 
	 * <p> Tail: tail set of node Ids 
	 * <p> Head: head set of node Ids 
	 */
	public void printHyperEdge() {
		System.out.println(this);
		HashSet<String> tail = new HashSet<String>(); 
		for (HyperNode i : this.tail) {
			tail.add(i.getId());
		}
		System.out.println("Tail : " + tail);
		
		HashSet<String> head = new HashSet<String>(); 
		for(HyperNode i : this.head) {
			head.add(i.getId());
		System.out.println("Head : " + head);
		}
	}

}
