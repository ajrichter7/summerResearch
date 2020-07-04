public class hyperNode {
	String Id; 
	String[] attributes; 
	
	//constructor for hyperNode 
	public hyperNode(String Id, String[] attr) {
		this.Id = Id;
		this.attributes = attr; 
	}
	
	public hyperNode(String Id) { 
		this(Id, null);
	}
	
	//method to get the unique String Id associated with each hyperNode 
	public String getId() {
		return Id; 
	}
	
	public String[] getAttributes() {
		return attributes; 
	}
	
}
