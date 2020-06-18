import java.util.*;

public class bObject {
	//B is the set of B-connected nodes given a Source set S 
	protected HashSet<hyperNode> B;
	//R is the list of restricted hyperEdges (hyperEdges that can be reached but not traversed) 
	protected HashSet<hyperEdge> R;
	//X is the list of traversed hyperEdges 
	protected HashSet<hyperEdge> X; 
	
	//constructor for object to return after bVisit
	public bObject() {
		HashSet<hyperNode> B = new HashSet<hyperNode>(); 
		HashSet<hyperEdge> R = new HashSet<hyperEdge>(); 
		HashSet<hyperEdge> X = new HashSet<hyperEdge>(); 
	}
	
	//method to set list of B-connected nodes
	public void setB(HashSet<hyperNode> b) {
		this.B = b; 
	}
	
	//method to set list of hyperEdges reached but not traversed (restricted) 
	public void setR(HashSet<hyperEdge> r) {
		this.R = r; 
	}
	
	//method to set list of traversed hyperEdges 
	public void setX(HashSet<hyperEdge> x) {
		this.X = x; 
	}
	
	//method to return list of B-connected nodes 
	public HashSet<hyperNode> getBset(){
		return this.B; 
	}
	
	//method to return list of restricted hyperEdges 
	public HashSet<hyperEdge> getRset(){
		return this.R; 
	}
	
	//method to return list of traversed hyperEdges
	public HashSet<hyperEdge> getXset(){
		return this.X; 
	}	
	
}
