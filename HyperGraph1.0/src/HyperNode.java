import java.util.*;

public class HyperNode {
	String Id; 
	Map<String, String> attributes; 
	
	//constructor for hyperNode 
	public HyperNode(String Id, Map<String, String> attr) {
		this.Id = Id;
		this.attributes = attr; 
	}
	
	//overwriting the constructor if there is not string[] of attributes 
	public HyperNode(String Id) { 
		this(Id, null);
	}
	
	//method to get the unique String Id associated with each hyperNode 
	public String getId() {
		return Id; 
	}
	
	//method to get string list of attributes if applicable for the node 
	public Map<String, String> getAttributes() {
		return attributes; 
	}
	
	public Set<String> getAttributesValues(){
		return attributes.keySet();
	}
	
}
