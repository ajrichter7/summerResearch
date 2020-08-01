package edu.reed.hypergraph;
import java.util.*;

/**
 * 
 * @author alexrichter
 * @version 1.0 
 *
 */

public class HyperNode {
	String Id; 
	Map<String, String> attributes; 
	
	/**
	 * Constructor for HyperNode object. 
	 * @param Id String identifier
	 * @param attr Map of String type of attributes
	 */
	public HyperNode(String Id, Map<String, String> attr) {
		this.Id = Id;
		this.attributes = attr; 
	}
	
	/**
	 * Overwrites the HyperNode constructor if there are not any associated attributes. 
	 * @param Id String identifier
	 */
	public HyperNode(String Id) { 
		this(Id, null);
	}
	
	/**
	 * Returns the unique String Id associated with each HyperNode. 
	 * @return Id String identifier
	 */
	public String getId() {
		return Id; 
	}
	
	/**
	 * Returns the Map of attributes if applicable for a HyperNode
	 * @return attributes Map of attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes; 
	}
	
	/**
	 * Returns a the set of values in a HyperNode's attribute map. 
	 * @return values KeySet of String values for Map of attributes 
	 */
	public Set<String> getAttributesValues(){
		return attributes.keySet();
	}
	
	/**
	 * Sets the map of attributes to a new map. 
	 * @param attr Map of attributes
	 */
	public void setAttributes(Map<String, String> attr) {
		this.attributes = attr; 
	}
	
}
