import java.util.*;

public class bObject extends hyperGraph {
	protected ArrayList<hyperNode> B;
	protected ArrayList<hyperEdge> R;
	protected ArrayList<hyperEdge> X; 
	
	public bObject() {
		ArrayList<hyperNode> B = new ArrayList<hyperNode>(); 
		ArrayList<hyperEdge> R = new ArrayList<hyperEdge>(); 
		ArrayList<hyperEdge> X = new ArrayList<hyperEdge>(); 
	}
	
	public void setB(ArrayList<hyperNode> b) {
		this.B = b; 
	}
	
	public void setR(ArrayList<hyperEdge> r) {
		this.R = r; 
	}
	
	public void setX(ArrayList<hyperEdge> x) {
		this.X = x; 
	}
	
	public ArrayList<hyperNode> getBset(){
		return this.B; 
	}
	
	public ArrayList<hyperEdge> getRset(){
		return this.R; 
	}
	
	public ArrayList<hyperEdge> getXset(){
		return this.X; 
	}
	
	public bObject bVisit(hyperGraph graph, ArrayList<String> nodes) {
		bObject results = new bObject(); 
		
		//initialize C[e] <-- counter of reached nodes in e's (where e c E) tail
		Hashtable<hyperEdge, Integer> count = new Hashtable<hyperEdge, Integer>(); 
		for (hyperEdge i : graph.edges) {
			count.put(i, 0);
		}
		
		Queue<hyperNode> Bconnect = new LinkedList<>(); 
		return results;
	}
	
}
