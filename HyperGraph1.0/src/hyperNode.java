import java.util.ArrayList;

public class hyperNode extends hyperGraph{
	String Id; 
	ArrayList<String> attributes; 
	
	//constructor for hyperNode 
	public hyperNode(hyperGraph graph, String Id) {
		this.Id = Id;
		attributes = new ArrayList<String>(); 
		graph.nodes.add(this); 
	}
	
	//method to get the unique String Id associated with each hyperNode 
	public String getId() {
		return Id; 
	}
	
}
