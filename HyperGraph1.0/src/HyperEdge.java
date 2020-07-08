import java.util.*;


public class HyperEdge {
	
	//public String Id; 
	private HashSet<HyperNode>tail; 
	private HashSet<HyperNode>head;
	
	//constructor 
	public HyperEdge() {
		this.tail = new HashSet<HyperNode>();
		this.head = new HashSet<HyperNode>();
	}
	
	//method to return the tail set for a hyperEdge 
	public HashSet<HyperNode> getTail() {
		return this.tail; 
	}
	
	//method to return the head set for a hyperEdge 
	public HashSet<HyperNode> getHead() {
		return this.head;
	}
	
	//method to get the head set for a given hyperEdge 
	public void setHead(HashSet<HyperNode>head) {
		this.head = head;
	}
	
	//method to set the tail set for a given hyperEdge 
	public void setTail(HashSet<HyperNode>tail) {
		this.tail = tail; 
	}
	
	//method to add one hyperNode to the tail 
	public void addToTail(HyperNode node) {
		this.tail.add(node);
	}
	
	//method to add one hyperNode to the head 
	public void addToHead(HyperNode node) {
		this.head.add(node); 
	}
	
	//method to remove a hyperNode from the tail
	public void removeFromTail(HyperNode node) {
		this.tail.remove(node); 
	}
	
	//method to remove a hyperNode from the head 
	public void removeFromHead(HyperNode node) {
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
