public class hyperNode {
	String Id; 
	String[] attributes; 
	
	//constructor for hyperNode 
	public hyperNode(String Id, String[] attr) {
		this.Id = Id;
		this.attributes = attr; 
	}
	
	//overwriting the constructor if there is not string[] of attributes 
	public hyperNode(String Id) { 
		this(Id, null);
	}
	
	//method to get the unique String Id associated with each hyperNode 
	public String getId() {
		return Id; 
	}
	
	//method to get string list of attributes if applicable for the node 
	public String[] getAttributes() {
		return attributes; 
	}
	
}
