import java.util.ArrayList;

public class hyperNode {
	String Id; 
	ArrayList<String> attributes; 
	
	//constructor for hyperNode 
	public hyperNode(String Id) {
		this.Id = Id;
		attributes = new ArrayList<String>(); 
	}
	
	//method to get the unique String Id associated with each hyperNode 
	public String getId() {
		return Id; 
	}
	
}
