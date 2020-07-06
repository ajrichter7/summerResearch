import java.util.*; 

public class hyperAlgorithms {

	//implementation of algorithm 1 from the paper 
	public static bObject bVisit(hyperGraph graph, HashSet<hyperNode> lsofNodes) {
		bObject results = new bObject(); 

		//initialize C[e] <-- counter of reached nodes in e's (where e c E) tail
		Hashtable<hyperEdge, Integer> count = new Hashtable<hyperEdge, Integer>(); 
		for (hyperEdge i : graph.edges) {
			count.put(i, 0);
		}
		
		//B <- HashSet<hyperNodes> B-connected nodes 
		HashSet<hyperNode> B = new HashSet<hyperNode>(lsofNodes); 

		//X <- HashSet<hyperEdge> initialized to 0 
		HashSet<hyperEdge> X = new HashSet<hyperEdge>(); 

		//Q <- want a queue of hyperNodes to traverse in the graph which comes from HashSet<String> lsofNodes
		Queue<hyperNode> Q = new LinkedList<hyperNode>(); 
		
		//adding hyperNodes in Source set to Q to be traversed 
		for (hyperNode i : lsofNodes) {
			Q.add(i); 
		}
		
		//loop for the hyperNodes in the Q 
		while(!Q.isEmpty()) {
			//remove some hyperNode from the Q (poll will remove the one at the head) 
			hyperNode n = Q.poll(); 
			//get a list of hyperEdges were n is contained in the tail set 
			HashSet<hyperEdge> e = graph.getOutgoingEdges(n.getId()); 
			//for each hyperEdge where n is in the tail set do
			for (hyperEdge i : e) {
				//update the counter for the specific edge by 1
				count.put(i, count.get(i) + 1);
				//if the number of times an edge is seen is equal to the tail size of the edge do
				if (count.get(i) == i.tailSize()){
					//add nodes in head to Q if not contained in B 
					for (hyperNode nodeinHead : i.getHead()) {
						if(!B.contains(nodeinHead)) {
							Q.add(nodeinHead);
						}
					}
					//add all nodes in head to B 
					B.addAll(i.getHead()); 
					//add i to set of traversed hyperEdges 
					X.add(i);
				}
			}
		}
		
		//create a new HashSet<hyperEdge> of restricted hyperEdges 
		HashSet<hyperEdge> R = new HashSet<hyperEdge>(); 

		//if hyperEdge was reached but not traversed add to R 
		for (hyperEdge e : graph.edges) {
			if(count.get(e) >= 1 && count.get(e) < e.tailSize()) {
				R.add(e); 
			}
		}

		results.setB(B);
		results.setX(X);
		results.setR(R);
		
		//return B, X, R
		return results; 
	}

	public static Hashtable<hyperNode, Integer> bRelaxation(hyperGraph graph, HashSet<hyperNode> lsofNodes) {
		bObject results = bVisit(graph, lsofNodes);
		//new dictionary for distances 
		Hashtable<hyperNode, Integer> dist = new Hashtable<hyperNode, Integer>(); 
		Integer myInf = Integer.MAX_VALUE;
		
		//dist[n] = 0 if n is contained in B0 else infinity for all n contained in the hyperGraph
		for (hyperNode n : graph.nodes) {
			if (results.getBset().contains(n)) 
				dist.put(n, 0);
			else 
				dist.put(n, myInf);
			
		}
		
		//new dictionary for whether or not a hyperEdge has been seen 
		Hashtable<hyperEdge, Boolean> seen = new Hashtable<hyperEdge, Boolean>(); 
		
		//TODO: consider a hashset<hyperEdge>
		
		//seen[e] = true if e is contained in X0 otherwise initialized to false 
		for (hyperEdge e : graph.edges) {
			if (results.getXset().contains(e)) 
				seen.put(e, true);
			else 
				seen.put(e, false); 
			
		}
		
		//initialize k to 1 
		int k = 1; 
		
		HashSet<hyperEdge> prevR = results.getRset(); 	
		
		HashSet<hyperNode> kBset = new HashSet<hyperNode>(); 
		HashSet<hyperEdge> kRset = new HashSet<hyperEdge>(); 
		
		//while there exists an edge that we have not seen do 
		while (seen.containsValue(false) && prevRhelp(prevR, seen)) {
			
			//initialize two empty HashSets to store new values from bVisit algorithm 
			kBset = new HashSet<hyperNode>(); 
			kRset = new HashSet<hyperEdge>(); 
			
			//for all of the edges in the previous set of restricted edges do 
			for (hyperEdge e : prevR) {
				
				//if the edge has not been seen do 
				if (seen.get(e) == false) {
					seen.replace(e, true);
					
					//call bVisit on the head set of the edge 
					bObject results2 = bVisit(graph, e.getHead());
					
					//save the values from last call to bVisit 
					kBset.addAll(results2.getBset());
					kRset.addAll(results2.getRset());
					
					//for all the edges in the X set (ie set of traversed hyperEdges) change value in seen to true
					for (hyperEdge edge : results2.getXset()) {
						seen.replace(edge, true); 
					}
				}
			}
			//if n is bConnected change the distance to k 
			for (hyperNode n : kBset) {
				if(dist.get(n) == myInf) {
					dist.replace(n, k);
				}
			}
			
			//update k 
			k = k + 1; 
			
			//update prevR such that it is now the restricted set from the last call to bVisit 
			prevR = new HashSet<hyperEdge>(kRset); 
		}
		
		//return the dictionary of distances to reach each node from the source set 
		return dist; 
	
	}
	
	//helper method for the while loop presented in BRelaxation
	public static boolean prevRhelp(HashSet<hyperEdge> rSet, Hashtable<hyperEdge, Boolean> seen) {
		for (hyperEdge e : rSet) {
			if(seen.get(e) == false)
				return true; 
		}return false; 
	}
}
	

