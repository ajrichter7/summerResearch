import java.util.*;

public class hyperAlgorithms {
	
	//attempting to implement algorithm 1 from the paper 
	public bObject bVisit(hyperGraph graph, HashSet<String> lsofNodes) {
		bObject results = new bObject(); 
		
		//initialize C[e] <-- counter of reached nodes in e's (where e c E) tail
		Hashtable<hyperEdge, Integer> count = new Hashtable<hyperEdge, Integer>(); 
		for (hyperEdge i : graph.edges) {
			count.put(i, 0);
		}
		
		//Should the input for this function by a list of strings, then i find the corresponding 
		//hyperNode objects in graph.nodes or pass a HashSet<hyperNode> ? 
		for (String i : lsofNodes) {
			// should return a list of hyperNode objects? 
		}
		
		//B <- HashSet<String> lsofNodes (or perhaps their corresponding hyperNode objects 
		//X == HashSet<hyperEdge> initialized to 0 
		HashSet<hyperEdge> X = new HashSet<hyperEdge>(); 
		
		//Q <- want a queue of hyperNodes to traverse in the graph which comes from HashSet<String> lsofNodes
		Queue<hyperNode> Q = new LinkedList<hyperNode>(); 
		//test queue methods to see if get error
		
		boolean empty = Q.isEmpty();
		while(!empty) {
			//do algorithm
		}
		
		return results;
	}
	
	public void bRelaxation(hyperGraph graph) {
		
	}

}
