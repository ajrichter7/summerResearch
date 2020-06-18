import java.util.*;

public class bObject extends hyperGraph {
	//B is the set of B-connected nodes given a Source set S 
	protected ArrayList<hyperNode> B;
	//R is the list of restricted hyperEdges (hyperEdges that can be reached but not traversed) 
	protected ArrayList<hyperEdge> R;
	//X is the list of traversed hyperEdges 
	protected ArrayList<hyperEdge> X; 
	
	//constructor for object to return after bVisit
	public bObject() {
		ArrayList<hyperNode> B = new ArrayList<hyperNode>(); 
		ArrayList<hyperEdge> R = new ArrayList<hyperEdge>(); 
		ArrayList<hyperEdge> X = new ArrayList<hyperEdge>(); 
	}
	
	//method to set list of B-connected nodes
	public void setB(ArrayList<hyperNode> b) {
		this.B = b; 
	}
	
	//method to set list of hyperEdges reached but not traversed (restricted) 
	public void setR(ArrayList<hyperEdge> r) {
		this.R = r; 
	}
	
	//method to set list of traversed hyperEdges 
	public void setX(ArrayList<hyperEdge> x) {
		this.X = x; 
	}
	
	//method to return list of B-connected nodes 
	public ArrayList<hyperNode> getBset(){
		return this.B; 
	}
	
	//method to return list of restricted hyperEdges 
	public ArrayList<hyperEdge> getRset(){
		return this.R; 
	}
	
	//method to return list of traversed hyperEdges
	public ArrayList<hyperEdge> getXset(){
		return this.X; 
	}	
	
}
