import java.util.*;


public class hyperEdge extends hyperGraph{
	
	public String Id; 
	private HashSet<hyperNode>tail; 
	private HashSet<hyperNode>head;
	
	//constructor 
	public hyperEdge() {
		this.tail = new HashSet<hyperNode>();
		this.head = new HashSet<hyperNode>();
	}
	
	//method to return the tail set for a hyperEdge 
	public HashSet<hyperNode> getTail() {
		return this.tail; 
	}
	
	//method to return the head set for a hyperEdge 
	public HashSet<hyperNode> getHead() {
		return this.head;
	}
	
	//method to get the head set for a given hyperEdge 
	public void setHead(HashSet<hyperNode>head) {
		this.head = head;
	}
	
	//method to set the tail set for a given hyperEdge 
	public void setTail(HashSet<hyperNode>tail) {
		this.tail = tail; 
	}

	//method to add a hyperEdge to a hyperGraph
	//Note: I do not use this function, and also don't know if it works for sure 
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
