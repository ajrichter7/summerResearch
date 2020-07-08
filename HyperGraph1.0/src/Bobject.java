import java.util.*;

public class Bobject {
	//B is the set of B-connected nodes given a Source set S 
	protected HashSet<HyperNode> B;
	//R is the list of restricted hyperEdges (hyperEdges that can be reached but not traversed) 
	protected HashSet<HyperEdge> R;
	//X is the list of traversed hyperEdges 
	protected HashSet<HyperEdge> X; 
	
	//constructor for object to return after bVisit
	public Bobject() {
		HashSet<HyperNode> B = new HashSet<HyperNode>(); 
		HashSet<HyperEdge> R = new HashSet<HyperEdge>(); 
		HashSet<HyperEdge> X = new HashSet<HyperEdge>(); 
	}
	
	//method to set list of B-connected nodes
	public void setB(HashSet<HyperNode> b) {
		this.B = b; 
	}
	
	//method to set list of hyperEdges reached but not traversed (restricted) 
	public void setR(HashSet<HyperEdge> r) {
		this.R = r; 
	}
	
	//method to set list of traversed hyperEdges 
	public void setX(HashSet<HyperEdge> x) {
		this.X = x; 
	}
	
	//method to return list of B-connected nodes 
	public HashSet<HyperNode> getBset(){
		return this.B; 
	}
	
	//method to return list of restricted hyperEdges 
	public HashSet<HyperEdge> getRset(){
		return this.R; 
	}
	
	//method to return list of traversed hyperEdges
	public HashSet<HyperEdge> getXset(){
		return this.X; 
	}	
	
}
