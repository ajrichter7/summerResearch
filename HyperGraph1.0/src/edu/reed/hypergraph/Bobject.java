package edu.reed.hypergraph;
import java.util.*;

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class Bobject {
	protected HashSet<HyperNode> B;
	protected HashSet<HyperEdge> R;
	protected HashSet<HyperEdge> X; 
	
	/**
	 * Constructor for object to return after BVisit.
	 * 
	 * B is the set of B-connected nodes given a Source set S.
	 * R is the list of restricted HyperEdges (HyperEdges that can be reached but not traversed).
	 * X is the list of traversed HyperEdges 
	 */
	public Bobject() {
		HashSet<HyperNode> B = new HashSet<HyperNode>(); 
		HashSet<HyperEdge> R = new HashSet<HyperEdge>(); 
		HashSet<HyperEdge> X = new HashSet<HyperEdge>(); 
	}
	
	/**
	 * Sets a list of B-connected nodes. 
	 * @param b HashSet of HyperNodes
	 */
	public void setB(HashSet<HyperNode> b) {
		this.B = b; 
	}
	
	/**
	 * Sets a list of restricted HyperEdges. 
	 * 
	 * HyperEdges that have been reached but not traversed. 
	 * @param r HashSet of HyperEdges
	 */
	public void setR(HashSet<HyperEdge> r) {
		this.R = r; 
	}
	
	/**
	 * Sets list of traversed HyperEdges.
	 *  
	 * @param x HashSet of HyperEdes 
	 */
	public void setX(HashSet<HyperEdge> x) {
		this.X = x; 
	}
	
	/**
	 * Returns list of B-connected nodes 
	 * @return HashSet of HyperNodes 
	 */
	//method to return list of B-connected nodes 
	public HashSet<HyperNode> getBset(){
		return this.B; 
	}
	
	/**
	 * Returns a list of restricted HyperEdges. 
	 * @return HashSet of HyperEdges 
	 */
	//method to return list of restricted hyperEdges 
	public HashSet<HyperEdge> getRset(){
		return this.R; 
	}
	
	/**
	 * Returns a list of traversed HyperEdges. 
	 * @return HashSet of HyperEdges 
	 */
	public HashSet<HyperEdge> getXset(){
		return this.X; 
	}	
	
}
