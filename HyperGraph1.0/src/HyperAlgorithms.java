import java.util.*; 

public class HyperAlgorithms {

	//implementation of algorithm 1 from the paper 
	public static Bobject bVisit(HyperGraph graph, HashSet<HyperNode> lsofNodes) {
		Bobject results = new Bobject(); 

		//initialize C[e] <-- counter of reached nodes in e's (where e c E) tail
		Hashtable<HyperEdge, Integer> count = new Hashtable<HyperEdge, Integer>(); 
		for (HyperEdge i : graph.edges) {
			count.put(i, 0);
		}
		
		//B <- HashSet<hyperNodes> B-connected nodes 
		HashSet<HyperNode> B = new HashSet<HyperNode>(lsofNodes); 

		//X <- HashSet<hyperEdge> initialized to 0 
		HashSet<HyperEdge> X = new HashSet<HyperEdge>(); 

		//Q <- want a queue of hyperNodes to traverse in the graph which comes from HashSet<String> lsofNodes
		Queue<HyperNode> Q = new LinkedList<HyperNode>(); 
		
		//adding hyperNodes in Source set to Q to be traversed 
		for (HyperNode i : lsofNodes) {
			Q.add(i); 
		}
		
		//loop for the hyperNodes in the Q 
		while(!Q.isEmpty()) {
			//remove some hyperNode from the Q (poll will remove the one at the head) 
			HyperNode n = Q.poll(); 
			//get a list of hyperEdges were n is contained in the tail set 
			HashSet<HyperEdge> e = graph.getOutgoingEdges(n.getId()); 
			//for each hyperEdge where n is in the tail set do
			for (HyperEdge i : e) {
				//update the counter for the specific edge by 1
				count.put(i, count.get(i) + 1);
				//if the number of times an edge is seen is equal to the tail size of the edge do
				if (count.get(i) == i.tailSize()){
					//add nodes in head to Q if not contained in B 
					for (HyperNode nodeinHead : i.getHead()) {
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
		HashSet<HyperEdge> R = new HashSet<HyperEdge>(); 

		//if hyperEdge was reached but not traversed add to R 
		for (HyperEdge e : graph.edges) {
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

	public static Hashtable<HyperNode, Integer> bRelaxation(HyperGraph graph, HashSet<HyperNode> lsofNodes) {
		Bobject results = bVisit(graph, lsofNodes);
		//new dictionary for distances 
		Hashtable<HyperNode, Integer> dist = new Hashtable<HyperNode, Integer>(); 
		Integer INFINITY = Integer.MAX_VALUE;
		
		//dist[n] = 0 if n is contained in B0 else infinity for all n contained in the hyperGraph
		for (HyperNode n : graph.nodes) {
			if (results.getBset().contains(n)) 
				dist.put(n, 0);
			else 
				dist.put(n, INFINITY);
			
		}
		
		//new dictionary for whether or not a hyperEdge has been seen 
		Hashtable<HyperEdge, Boolean> seen = new Hashtable<HyperEdge, Boolean>(); 
		
		//TODO: consider a hashset<hyperEdge>
		
		//seen[e] = true if e is contained in X0 otherwise initialized to false 
		for (HyperEdge e : graph.edges) {
			if (results.getXset().contains(e)) 
				seen.put(e, true);
			else 
				seen.put(e, false); 
			
		}
		
		//initialize k to 1 
		int k = 1; 
		
		HashSet<HyperEdge> prevR = results.getRset(); 	
		
		HashSet<HyperNode> kBset = new HashSet<HyperNode>(); 
		HashSet<HyperEdge> kRset = new HashSet<HyperEdge>(); 
		
		//while there exists an edge that we have not seen do 
		while (seen.containsValue(false) && prevRhelp(prevR, seen)) {
			
			//initialize two empty HashSets to store new values from bVisit algorithm 
			kBset = new HashSet<HyperNode>(); 
			kRset = new HashSet<HyperEdge>(); 
			
			//for all of the edges in the previous set of restricted edges do 
			for (HyperEdge e : prevR) {
				
				//if the edge has not been seen do 
				if (seen.get(e) == false) {
					seen.replace(e, true);
					
					//call bVisit on the head set of the edge 
					Bobject results2 = bVisit(graph, e.getHead());
					
					//save the values from last call to bVisit 
					kBset.addAll(results2.getBset());
					kRset.addAll(results2.getRset());
					
					//for all the edges in the X set (ie set of traversed hyperEdges) change value in seen to true
					for (HyperEdge edge : results2.getXset()) {
						seen.replace(edge, true); 
					}
				}
			}
			//if n is bConnected change the distance to k 
			for (HyperNode n : kBset) {
				if(dist.get(n) == INFINITY) {
					dist.replace(n, k);
				}
			}
			
			//update k 
			k = k + 1; 
			
			//update prevR such that it is now the restricted set from the last call to bVisit 
			prevR = new HashSet<HyperEdge>(kRset); 
		}
		
		//return the dictionary of distances to reach each node from the source set 
		return dist; 
	
	}
	
	public static boolean prevRhelp(HashSet<HyperEdge> rSet, Hashtable<HyperEdge, Boolean> seen) {
		for (HyperEdge e : rSet) {
			if(seen.get(e) == false)
				return true; 
		}return false; 
	}
}
	

