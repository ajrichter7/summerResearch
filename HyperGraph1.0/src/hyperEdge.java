import java.util.*;


public class hyperEdge {
	
	//public String Id; 
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
	
	//method to add one hyperNode to the tail 
	public void addToTail(hyperNode node) {
		this.tail.add(node);
	}
	
	//method to add one hyperNode to the head 
	public void addToHead(hyperNode node) {
		this.head.add(node); 
	}
	
	//method to remove a hyperNode from the tail
	public void removeFromTail(hyperNode node) {
		this.tail.remove(node); 
	}
	
	//method to remove a hyperNode from the head 
	public void removeFromHead(hyperNode node) {
		this.head.remove(node); 
	}
	
	//method to return the size of the tail set 
	public int tailSize() {
		return this.tail.size(); 
	}
	
	//method to return the size of the head set 
	public int headSize() {
		return this.head.size(); 
	}
	
	//method to print out specific hyperEdge that method is called on 
	public void printHyperEdge() {
		System.out.println(this);
		HashSet<String> tail = new HashSet<String>(); 
		for (hyperNode i : this.tail) {
			tail.add(i.getId());
		}
		System.out.println("Tail : " + tail);
		
		HashSet<String> head = new HashSet<String>(); 
		for(hyperNode i : this.head) {
			head.add(i.getId());
		System.out.println("Head : " + head);
		}
	}

}
